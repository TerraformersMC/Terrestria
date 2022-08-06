package com.terraformersmc.terrestria.biomeperimeters;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.terraformersmc.terrestria.Terrestria;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntMaps;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ReferenceMap;
import it.unimi.dsi.fastutil.ints.Int2ReferenceMaps;
import it.unimi.dsi.fastutil.ints.Int2ReferenceOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

/**
 * BiomePerimeters
 * <p/>
 * This class builds a bidirectional hashed list of the points (voxels) on the perimeter of a biome instance.
 * The points are used to provide an estimate of how far "in-biome" a given biome voxel is.  This can allow
 * biome generation (f.e. surface builders) to blend with surrounding biomes or generate context-sensitive
 * terrain heights within a particular biome.
 * <p/>
 * Note:  In order to achieve acceptable performance in-game, BiomePerimeters makes heavy use of caching and
 * also accepts certain compromises with respect to the accuracy of the perimeter distance values.  It is safe
 * to call getPerimeterDistance() for every individual block column during generation, but on the other hand,
 * minor discontinuities and variations may occasionally occur in the distance values.
 */
public class BiomePerimeters {
	private static final Hashtable<Biome, BiomePerimeters> instances = new Hashtable<>(4);

	private final Biome biome;
	private final int cardinalHorizon;
	private final int ordinalHorizon;
	private final int checkDistance;

	public static final int MAX_HORIZON = 256;
	private static final long COMPACTION_TIMER_TICKS = 300;

	private final LoadingCache<ChunkPos, CacheRecord> caches =
			CacheBuilder.newBuilder()
					.maximumSize(4096)
					.expireAfterAccess(30, TimeUnit.SECONDS)
					.weakValues()
					.build(new CacheLoader<>() {
						@Override
						public @NotNull CacheRecord load(@NotNull ChunkPos key) {
							return new CacheRecord();
						}
					});

	private static final int MAX_THREAD_LOCAL_CACHE_SIZE = 128;
	private final ThreadLocal<Object2ObjectLinkedOpenHashMap<ChunkPos, CacheRecord>> threadLocalCaches =
			ThreadLocal.withInitial(Object2ObjectLinkedOpenHashMap::new);

	private CacheRecord getCache(Object2ObjectLinkedOpenHashMap<ChunkPos, CacheRecord> threadLocal, ChunkPos pos) {
		final CacheRecord cached = threadLocal.getAndMoveToFirst(pos);

		if (cached != null) {
			return cached;
		}

		final CacheRecord newOne = caches.getUnchecked(pos);
		threadLocal.putAndMoveToFirst(pos, newOne);
		if (threadLocal.size() > MAX_THREAD_LOCAL_CACHE_SIZE) {
			threadLocal.removeLast();
		}

		return newOne;
	}

	BiomePerimeters(Biome biome) {
		this(biome, 64);
	}

	/**
	 * new BiomePerimeters()
	 * <p/>
	 * Construct a new BiomePerimeters instance for the given Biome.
	 *
	 * @param biome   Biome - The Biome for which this BiomePerimeters will compute perimeter distances.
	 * @param horizon int - The maximum distance to search for perimeter points for this Biome.
	 */
	BiomePerimeters(Biome biome, int horizon) {
		if (horizon < 1 || horizon > MAX_HORIZON) {
			Terrestria.LOGGER.debug("BiomePerimeters horizon must be in the range [1,{}]: {}", MAX_HORIZON, horizon);
			horizon = MAX_HORIZON;
		}

		this.biome = biome;
		this.cardinalHorizon = horizon;
		this.ordinalHorizon = (int) (horizon / Math.sqrt(2));
		this.checkDistance = (int) (horizon * 0.89f);
	}

	/**
	 * BiomePerimeteres.getPerimeterDistance()
	 * <p/>
	 * Call this method when you need to know how far in-biome a block column is.  The returned int will give the
	 * distance to the perimeter if it is less than the instance's configured horizon, and a value greater than or
	 * equal to the configured horizon if it is not.
	 *
	 * @param biomeAccess BiomeAccess - Biome access used to determine whether neighboring voxels are in-biome.
	 * @param pos         BlockPos - The voxel being evaluated for perimeter distance; the Y value is used for biome checks.
	 * @return int - The perimeter distance value resolved for the target voxel.
	 */
	public int getPerimeterDistance(BiomeAccess biomeAccess, BlockPos pos) {
		float minimum = cardinalHorizon + 1;
		BlockPos iterPos;
		int horizon;
		int dx;
		int dz;

		final var threadLocalCache = threadLocalCaches.get();

		// If we are on the perimeter, avoid some difficult "edge" cases (har har) by short-circuiting.
		for (EightWayDirection direction : EightWayDirection.values()) {
			if (!checkBiome(biomeAccess, pos.add(direction.method_42015(), 0, direction.method_42016()), threadLocalCache)) {
				return 0;
			}
		}

		// Check the cache for an authoritative distance value and return that if it's already known.
		{
			final CacheRecord cache = getCache(threadLocalCache, new ChunkPos(pos));
			final int idx = CacheRecord.getIndex(pos);
			if (cache.biomeCache.containsKey(idx)) {
				int cached = cache.biomeCache.get(idx);
				if (cached > 0) {
					return cached;
				}
			}
		}

		// Try to find our closest perimeter point.
		for (EightWayDirection direction : EightWayDirection.values()) {
			horizon = (direction.ordinal() % 2 == 0) ? cardinalHorizon : ordinalHorizon;
			dx = direction.method_42015();
			dz = direction.method_42016();

			for (int radius = 0; radius < horizon; radius++) {
				iterPos = pos.add(dx * radius, 0, dz * radius);
				final CacheRecord cache = getCache(threadLocalCache, new ChunkPos(iterPos));
				if (cache.perimeters.containsKey(CacheRecord.getIndex(iterPos)) || !checkBiome(biomeAccess, iterPos.add(dx, 0, dz), threadLocalCache)) {
					int localMinimum = this.checkPerimeter(biomeAccess, pos, iterPos, direction, threadLocalCache);
					if (localMinimum >= 0) {
						minimum = Math.min(minimum, localMinimum);
						break;
					} else {
						// Power on through a small biome inclusion we found.
						for (++radius; radius < horizon; radius++) {
							if (checkBiome(biomeAccess, pos.add(dx * radius, 0, dz * radius), threadLocalCache)) {
								++radius;
								break;
							}
						}
					}
				}
			}
		}

		// Ensure we're about to return a sane value.  Cache the value.
		return rationalizeDistance(pos, minimum, threadLocalCache);
	}

	private int checkPerimeter(BiomeAccess biomeAccess, BlockPos centerPos, BlockPos perimeterPos, EightWayDirection direction, Object2ObjectLinkedOpenHashMap<ChunkPos, CacheRecord> threadLocalCache) {
		BiomePerimeterPoint current;
		EightWayDirection orientation;
		double minimum;
		int localCheckDistance;

		// The point we reached the perimeter might not be known yet, so special-case it in.
		final CacheRecord perimeterPosCache = getCache(threadLocalCache, new ChunkPos(perimeterPos));
		final int perimeterPosIndex = CacheRecord.getIndex(perimeterPos);
		current = perimeterPosCache.perimeters.computeIfAbsent(perimeterPosIndex, k -> BiomePerimeterPoint.of(perimeterPos));

		minimum = current.getDistance(centerPos);
		localCheckDistance = Math.min(checkDistance, (int) (minimum * minimum) - 1);

		// left-hand
		orientation = direction;
		for (int check = 0; check < localCheckDistance; check++) {
			if (current.left != null) {
				orientation = getEightWayRelation(current.left.pos, current.pos);
			} else {
				orientation = getEightWayClockwiseRotation(orientation, 5);
				for (int rotation = 2; rotation < 8; rotation++) {
					orientation = getEightWayClockwiseRotation(orientation, 1);
					if (!checkBiome(biomeAccess, current.pos.add(orientation.method_42015(), 0, orientation.method_42016()), threadLocalCache)) {
						orientation = getEightWayClockwiseRotation(orientation, -1);
						BlockPos prospect = current.pos.add(orientation.method_42015(), 0, orientation.method_42016());
						BiomePerimeterPoint prospectPoint = getCache(threadLocalCache, new ChunkPos(prospect)).perimeters.get(CacheRecord.getIndex(prospect));
						if (prospectPoint != null) {
							prospectPoint.setRight(current);
							current.setLeft(prospectPoint);
						} else {
							current.setLeft(BiomePerimeterPoint.leftOf(prospect, current));
							getCache(threadLocalCache, new ChunkPos(current.left.pos)).perimeters.put(CacheRecord.getIndex(current.left.pos), current.left);
						}
						break;
					}
				}
			}

			// Special handling for inclusions of other biomes is here on the LHS.
			if (current.left == null) {
				// Dead end encountered to the left.
				break;
			} else if (perimeterPos.compareTo(current.left.pos) == 0) {
				// Handle loops caused by inclusions of other biomes.
				if (check < 16) {
					// Ignore inclusions of less than four biome pixels in size.
					return -1;
				} else {
					// Treat larger inclusions as a perimeter.
					return (int) minimum;
				}
			} else {
				// Advance.
				current = current.left;
				minimum = Math.min(minimum, current.getDistance(centerPos));
			}
		}

		// right-hand
		current = perimeterPosCache.perimeters.get(perimeterPosIndex);
		orientation = direction;
		for (int check = 0; check < localCheckDistance; check++) {
			if (current.right != null) {
				orientation = getEightWayRelation(current.right.pos, current.pos);
			} else {
				orientation = getEightWayClockwiseRotation(orientation, 3);
				for (int rotation = 2; rotation < 8; rotation++) {
					orientation = getEightWayClockwiseRotation(orientation, -1);
					if (!checkBiome(biomeAccess, current.pos.add(orientation.method_42015(), 0, orientation.method_42016()), threadLocalCache)) {
						orientation = getEightWayClockwiseRotation(orientation, 1);
						BlockPos prospect = current.pos.add(orientation.method_42015(), 0, orientation.method_42016());
						BiomePerimeterPoint prospectPoint = getCache(threadLocalCache, new ChunkPos(prospect)).perimeters.get(CacheRecord.getIndex(prospect));
						if (prospectPoint != null) {
							// Detect when we are passing through the same path we passed through on the left.
							if (current.equals(prospectPoint.right)) {
								// Trim single-wide channels where left == right will cause loops.
								prospectPoint.right = null;
							} else {
								prospectPoint.setLeft(current);
							}
							current.setRight(prospectPoint);
						} else {
							current.setRight(BiomePerimeterPoint.rightOf(prospect, current));
							getCache(threadLocalCache, new ChunkPos(current.right.pos)).perimeters.put(CacheRecord.getIndex(current.right.pos), current.right);
						}
						break;
					}
				}
			}

			// Check for loops and dead ends; advance.
			if (current.right == null || perimeterPos.compareTo(current.right.pos) == 0) {
				// Loops are already handled by the LHS.  This branch generally just handles dead ends.
				break;
			} else {
				current = current.right;
				minimum = Math.min(minimum, current.getDistance(centerPos));
			}
		}

		return (int) minimum;
	}

	private EightWayDirection getEightWayClockwiseRotation(EightWayDirection direction, int increment) {
		assert (increment >= -8);
		return EightWayDirection.values()[(direction.ordinal() + increment + 8) % 8];
	}

	private EightWayDirection getEightWayRelation(BlockPos posA, BlockPos posB) {
		BlockPos diff = posA.subtract(posB);
		if (diff.getX() < 0) {
			if (diff.getZ() < 0) {
				return EightWayDirection.NORTH_WEST;
			} else if (diff.getZ() > 0) {
				return EightWayDirection.SOUTH_WEST;
			} else {
				return EightWayDirection.WEST;
			}
		} else if (diff.getX() > 0) {
			if (diff.getZ() < 0) {
				return EightWayDirection.NORTH_EAST;
			} else if (diff.getZ() > 0) {
				return EightWayDirection.SOUTH_EAST;
			} else {
				return EightWayDirection.EAST;
			}
		} else {
			return diff.getZ() < 0 ? EightWayDirection.NORTH : EightWayDirection.SOUTH;
		}
	}

	private boolean checkBiome(BiomeAccess biomeAccess, BlockPos pos, Object2ObjectLinkedOpenHashMap<ChunkPos, CacheRecord> threadLocalCache) {
		/* Distance values in biomeCache:
		 * -1:  special value indicating pos is in-biome but the perimeter distance is unknown
		 *  0:  value indicating pos is not in-biome
		 * >0:  pos is in-biome and the value indicates the distance to the perimeter
		 */
		return (getCache(threadLocalCache, new ChunkPos(pos)).biomeCache.computeIfAbsent(CacheRecord.getIndex(pos), (key) -> biomeAccess.getBiome(pos).value().equals(biome) ? -1 : 0) != 0);
	}

	private int rationalizeDistance(BlockPos pos, float proposed, Object2ObjectLinkedOpenHashMap<ChunkPos, CacheRecord> threadLocalCache) {
		int distance;
		float lower = 0;
		float upper = MAX_HORIZON;

		for (EightWayDirection direction : EightWayDirection.values()) {
			final BlockPos neighborPos = pos.add(direction.method_42015(), 0, direction.method_42016());
			int neighbor = getCache(threadLocalCache, new ChunkPos(neighborPos)).biomeCache.getOrDefault(CacheRecord.getIndex(neighborPos), -1);

			if (neighbor > 0) {
				// TODO: Leaving the lower bound out gives better results for Calderas ... could use some thought.
				//lower = Math.max(lower, neighbor - 2.72f);
				upper = Math.min(upper, neighbor + 2.72f);
			}
		}

		distance = Math.round(MathHelper.clamp(proposed, lower, upper));
		getCache(threadLocalCache, new ChunkPos(pos)).biomeCache.put(CacheRecord.getIndex(pos), distance);

		return distance;
	}

	/**
	 * BiomePerimeters.getOrCreateInstance()
	 * <p/>
	 * Each Biome must have a separate instance of BiomePerimeters.  An instance of BiomePerimeters
	 * will be created if one does not already exist, and subsequently the same instance will always
	 * be returned for the given Biome during the same game session.
	 *
	 * @param biome   - The Biome for which we are maybe-adding and fetching the BiomePerimeters instance
	 * @param horizon - Max distance to check for biome edge; range [1 - 256]
	 */
	public static synchronized @NotNull BiomePerimeters getOrCreateInstance(@NotNull Biome biome, int horizon) {
		return instances.computeIfAbsent(biome, (key) -> new BiomePerimeters(key, horizon));
	}


	/**
	 * BiomePerimeters.BiomePerimeterPoint
	 * <p/>
	 * This class essentially defines a mutable record we use to store data about the perimeter of a Biome.
	 * A future implementation of BiomePerimeters may expose BiomePerimeterPoints in some manner if useful.
	 */
	public static final class BiomePerimeterPoint implements Cloneable {
		final BlockPos pos;
		BiomePerimeterPoint left;
		BiomePerimeterPoint right;

		public BiomePerimeterPoint(@NotNull BlockPos pos, @Nullable BiomePerimeterPoint left, @Nullable BiomePerimeterPoint right) {
			this.pos = pos.toImmutable();
			this.left = left;
			this.right = right;
		}

		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		public static BiomePerimeterPoint of(@NotNull BlockPos pos) {
			return new BiomePerimeterPoint(pos, null, null);
		}

		public static BiomePerimeterPoint rightOf(@NotNull BlockPos pos, BiomePerimeterPoint left) {
			return new BiomePerimeterPoint(pos, left, null);
		}

		public static BiomePerimeterPoint leftOf(@NotNull BlockPos pos, BiomePerimeterPoint right) {
			return new BiomePerimeterPoint(pos, null, right);
		}

		public static BiomePerimeterPoint of(@NotNull BlockPos pos, BiomePerimeterPoint left, BiomePerimeterPoint right) {
			return new BiomePerimeterPoint(pos, left, right);
		}

		public void setLeft(@NotNull BiomePerimeterPoint left) {
			assert (this.left == null);
			this.left = left;
		}

		public void setRight(@NotNull BiomePerimeterPoint right) {
			assert (this.right == null);
			this.right = right;
		}

		public @NotNull BlockPos getPos() {
			return pos.mutableCopy();
		}

		public @Nullable BiomePerimeterPoint getLeft() {
			try {
				return (BiomePerimeterPoint) left.clone();
			} catch (CloneNotSupportedException e) {
				return null;
			}
		}

		public @Nullable BiomePerimeterPoint getRight() {
			try {
				return (BiomePerimeterPoint) right.clone();
			} catch (CloneNotSupportedException e) {
				return null;
			}
		}

		/**
		 * BiomePerimeters.BiomePerimeterPoint.getDistance()
		 * <p/>
		 * Calculate the straight-line distance between a biome perimeter point and another position.
		 *
		 * @param pos BlockPos - The position to calculate the distance to
		 * @return double - The distance "as the crow flies" between this BiomePerimeterPoint and pos
		 */
		public double getDistance(@NotNull BlockPos pos) {
			return Math.sqrt(this.pos.getSquaredDistance(pos));
		}

		/**
		 * BiomePerimeters.BiomePerimeterPoint.getTaxicab()
		 * <p/>
		 * Calculate the taxi-cab (or "Manhattan") distance between a biome perimeter point and another position.
		 * This is the sum of the difference between the X and Z coordinates of the two points.
		 *
		 * @param pos Vec3i - The position to calculate the taxi-cab distance to
		 * @return int - The distance "as a taxi drives" between this BiomePerimeterPoint and pos
		 */
		public int getTaxicab(@NotNull Vec3i pos) {
			return this.pos.getManhattanDistance(pos);
		}
	}


	private static final class CacheRecord {
		private final Int2ReferenceMap<BiomePerimeterPoint> perimeters = Int2ReferenceMaps.synchronize(new Int2ReferenceOpenHashMap<>(16 * 16 * 4), this);
		private final Int2IntMap biomeCache = Int2IntMaps.synchronize(new Int2IntOpenHashMap(16 * 16 * 4), this);

		public void clear() {
			this.perimeters.clear();
			this.biomeCache.clear();
		}

		public static int getIndex(int x, int y, int z) {
			return (x & 0b1111) | ((z & 0b1111) << 4) | (((short) y) << 8);
		}

		public static int getIndex(BlockPos pos) {
			return getIndex(pos.getX(), pos.getY(), pos.getZ());
		}
	}
}
