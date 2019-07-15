package net.coderbot.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import io.github.terraformersmc.terraform.block.QuarterLogBlock;
import io.github.terraformersmc.terraform.util.Shapes;
import net.coderbot.terrestria.feature.TreeDefinition;
import net.coderbot.terrestria.feature.trees.components.Roots;
import net.minecraft.block.Blocks;
import net.minecraft.block.SeagrassBlock;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class CanopyTreeFeatureMega extends AbstractTreeFeature<DefaultFeatureConfig> implements Roots {
	private TreeDefinition.Mega tree;

	public CanopyTreeFeatureMega(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Mega tree) {
		super(function, notify);

		this.tree = tree;
	}

	protected static boolean canTreeReplace(TestableWorld world, BlockPos pos) {
		return AbstractTreeFeature.canTreeReplace(world, pos) || world.testBlockState(pos,
				state -> state.getFluidState().getFluid().matches(FluidTags.WATER) || state.getBlock() instanceof SeagrassBlock
		);
	}

	public CanopyTreeFeatureMega sapling() {
		return new CanopyTreeFeatureMega(DefaultFeatureConfig::deserialize, true, tree);
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		// Total trunk height
		int height = getHeight(rand);

		// How much "bare trunk" there will be. (2-3)
		int bareTrunkHeight = getBareTrunkHeight(rand);

		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		int down = 0;
		while (world.testBlockState(below, state -> state.getFluidState().getFluid().matches(FluidTags.WATER) || state.getBlock() instanceof SeagrassBlock)) {
			below = below.down();
			down++;
		}

		height += down * 3 / 2;
		bareTrunkHeight += down * 3 / 2;

		origin = below.up();

		for (int dZ = 0; dZ < 2; dZ++) {
			for (int dX = 0; dX < 2; dX++) {
				below = origin.add(dX, -1, dZ);

				if (!isNaturalDirtOrGrass(world, below)) {
					return false;
				}
			}
		}

		if (!checkForObstructions(world, origin, height, bareTrunkHeight)) {
			return false;
		}

		for (int dZ = 0; dZ < 2; dZ++) {
			for (int dX = 0; dX < 2; dX++) {
				setBlockState(blocks, world, origin.add(dX, -1, dZ), Blocks.DIRT.getDefaultState(), boundingBox);
			}
		}

		growTrunk(blocks, world, new BlockPos.Mutable(origin), height / 2, boundingBox);

		BlockPos.Mutable pos = new BlockPos.Mutable(origin).setOffset(Direction.UP, bareTrunkHeight);
		growBranches(blocks, world, pos, height - bareTrunkHeight, height / 2 - bareTrunkHeight, rand, boundingBox);

		pos.set(origin).setOffset(Direction.DOWN, 2);
		growRoots(blocks, world, pos, bareTrunkHeight + 2, rand, boundingBox);

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int bareTrunkHeight) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for (int dY = 0; dY < bareTrunkHeight; dY++) {
			for (int dZ = 0; dZ < 2; dZ++) {
				for (int dX = 0; dX < 2; dX++) {

					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		for (int dY = bareTrunkHeight; dY < height + 1; dY++) {
			for (int dZ = -7; dZ < 8; dZ++) {
				for (int dX = -7; dX < 8; dX++) {
					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int i = 0; i < height; i++) {
			pos.set(x, y + i, z);
			setBlockState(blocks, world, pos, tree.getLogQuarter().with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.NORTHWEST), boundingBox);

			pos.set(x + 1, y + i, z);
			setBlockState(blocks, world, pos, tree.getLogQuarter().with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.NORTHEAST), boundingBox);

			pos.set(x, y + i, z + 1);
			setBlockState(blocks, world, pos, tree.getLogQuarter().with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.SOUTHWEST), boundingBox);

			pos.set(x + 1, y + i, z + 1);
			setBlockState(blocks, world, pos, tree.getLogQuarter().with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.SOUTHEAST), boundingBox);
		}
	}

	private void growBranches(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, int trunkHeight, Random rand, MutableIntBoundingBox boundingBox) {
		int branches = rand.nextInt(10) + 15;

		int[] offset = new int[3];
		BlockPos origin = pos.toImmutable();

		for (int branch = 0; branch < branches; branch++) {
			int startOffsetY = rand.nextInt(height);

			offset[Shapes.X] = MathHelper.clamp(rand.nextInt(13) - 6, -5, 5);
			offset[Shapes.Y] = rand.nextInt(height - 3) + trunkHeight / 2;
			offset[Shapes.Z] = MathHelper.clamp(rand.nextInt(13) - 6, -5, 5);

			int alignX = offset[Shapes.X] > 0 ? 1 : 0;
			int alignZ = offset[Shapes.Z] > 0 ? 1 : 0;

			pos.set(origin);
			pos.add(alignX, startOffsetY, alignZ);

			Shapes.line(pos, offset, position -> {
				if (!world.testBlockState(position, candidate -> candidate.getBlock() instanceof QuarterLogBlock)) {
					setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
				}
			});

			int maxRadius = Math.min(
					Math.min(
							Math.min(8 - offset[Shapes.X], 8 - offset[Shapes.Z]),
							Math.min(8 + offset[Shapes.X] - 1, 8 + offset[Shapes.Z] - 1)
					),
					4
			);

			int x = pos.getX();
			int z = pos.getZ();
			int radius = maxRadius == 4 ? 3 + rand.nextInt(2) : 2 + rand.nextInt(maxRadius - 1);

			pos.setOffset(Direction.DOWN);

			for (int i = -1; i <= 1; i++) {
				pos.set(x, pos.getY(), z);

				int layerRadius = (int) Math.floor(radius * Math.cos(i * Math.PI / 6));

				Shapes.circle(pos, layerRadius, position -> {
					if (AbstractTreeFeature.isAirOrLeaves(world, pos)) {
						setBlockState(blocks, world, pos, tree.getLeaves(), boundingBox);
					}
				});

				pos.setOffset(Direction.UP);
			}
		}
	}

	public void growRoots(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int baseTrunkHeight, Random rand, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(blocks, world, pos.set(x - 1, y, z + rand.nextInt(2)), baseTrunkHeight, rand, boundingBox);
		tryGrowRoot(blocks, world, pos.set(x + 2, y, z + rand.nextInt(2)), baseTrunkHeight, rand, boundingBox);
		tryGrowRoot(blocks, world, pos.set(x + rand.nextInt(2), y, z - 1), baseTrunkHeight, rand, boundingBox);
		tryGrowRoot(blocks, world, pos.set(x + rand.nextInt(2), y, z + 2), baseTrunkHeight, rand, boundingBox);
	}

	public void tryGrowRoot(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable bottom, int baseTrunkHeight, Random rand, MutableIntBoundingBox boundingBox) {
		if (rand.nextInt(5) == 0) {
			return;
		}

		int height = baseTrunkHeight + rand.nextInt(2) - 3;

		for (int i = 0; i < height; i++) {
			if (canTreeReplace(world, bottom) || AbstractTreeFeature.isReplaceablePlant(world, bottom) || world.testBlockState(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				setBlockState(blocks, world, bottom, tree.getBark(), boundingBox);
			}

			bottom.setOffset(Direction.UP);
		}
	}

	public int getHeight(Random rand) {
		return rand.nextInt(8) + 19;
	}

	public int getBareTrunkHeight(Random rand) {
		return 2 + rand.nextInt(2);
	}
}
