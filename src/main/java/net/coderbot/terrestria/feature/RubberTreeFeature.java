package net.coderbot.terrestria.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
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

// TODO: These should be properly spawned and implemented. For the future.
public class RubberTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private TreeDefinition.Basic tree;

	public RubberTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify);

		this.tree = tree;
	}

	public RubberTreeFeature sapling() {
		return new RubberTreeFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		// Total trunk height
		int height = rand.nextInt(4) + 12;

		if(origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		if(!isNaturalDirtOrGrass(world, below)) {
			return false;
		}

		if(!checkForObstructions(world, origin, height)) {
			return false;
		}

		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);
		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);
		growBranches(blocks, world, new BlockPos.Mutable(origin), height, rand, boundingBox);

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for(int dY = 0; dY < height; dY++) {
			for(int dZ = -1; dZ <= 1; dZ++) {
				for(int dX = -1; dX <= 1; dX++) {
					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);
					
					if(!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int z = pos.getZ();

		for(int i = 0; i < height; i++) {
			pos.set(x, pos.getY(), z);
			setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
			pos.setOffset(Direction.UP);
		}
	}

	private void growBranches(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, Random random, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for(int branch = 0; branch < 16; branch++) {
			int baseY = random.nextInt(height - 2) + 4;

			float length = random.nextFloat() * 7 + 2;
			float angle = random.nextFloat() * (float)Math.PI * 2;

			int offsetX = (int)(MathHelper.cos(angle) * length);
			int offsetZ = (int)(MathHelper.sin(angle) * length);

			int moveX = offsetX > 0 ? 1 : -1;
			int moveZ = offsetZ > 0 ? 1 : -1;

			int absX = Math.abs(offsetX);
			int absZ = Math.abs(offsetZ);

			int movedX = 0;
			int movedZ = 0;

			float stepX = 1.0F / absX;
			float stepZ = 1.0F / absZ;

			for(int movement = 0; movement < absX + absZ; movement++) {
				if(Math.abs(movedX * stepX) < Math.abs(movedZ * stepZ) && Math.abs(movedX) < absX) {
					movedX += moveX;
				} else {
					movedZ += moveZ;
				}

				int offsetY = (int)(Math.sqrt(movedX*movedX + movedZ * movedZ) * 0.4);

				if(movedX > 2 || movedX < -2 || movedZ > 2 || movedZ < -2) {
					continue;
				}

				pos.set(x + movedX, y + baseY + offsetY, z + movedZ);

				if(!canTreeReplace(world, pos)) {
					break;
				}

				setBlockState(blocks, world, pos, tree.getLog(), boundingBox);

				for(Direction direction: Direction.values()) {
					pos.set(x + movedX, y + baseY + offsetY, z + movedZ);
					pos.setOffset(direction);

					if(AbstractTreeFeature.isAirOrLeaves(world, pos)) {
						setBlockState(blocks, world, pos, tree.getLeaves(), boundingBox);
					}
				}
			}
		}
	}
}
