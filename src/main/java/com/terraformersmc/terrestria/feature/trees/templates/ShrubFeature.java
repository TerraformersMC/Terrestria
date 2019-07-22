package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import io.github.terraformersmc.terraform.block.LeafPileBlock;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class ShrubFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private TreeDefinition.Basic tree;

	public ShrubFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify);
		this.tree = tree;
	}

	public ShrubFeature sapling() {
		return new ShrubFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos pos, MutableIntBoundingBox boundingBox) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();

		if (isNaturalDirtOrGrass(world, pos)) {
			pos = pos.up();
			this.setBlockState(blocks, world, pos, this.tree.getLog(), boundingBox);

			for (int y = pos.getY(); y <= pos.getY() + 2; ++y) {
				int dY = y - pos.getY();
				int radius = 2 - dY;

				for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x) {
					int dX = x - pos.getX();

					for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z) {
						int dZ = z - pos.getZ();

						if (Math.abs(dX) != radius || Math.abs(dZ) != radius || rand.nextInt(2) != 0) {
							BlockPos candidate = new BlockPos(x, y, z);

							if (isAirOrLeaves(world, candidate) || world.testBlockState(candidate, state -> state.getBlock() instanceof LeafPileBlock)) {
								this.setBlockState(blocks, world, candidate, this.tree.getLeaves(), boundingBox);
							}
						}
					}
				}
			}
		}

		return true;
	}
}