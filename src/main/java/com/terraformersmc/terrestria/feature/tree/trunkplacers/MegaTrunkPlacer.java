package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.block.QuarterLogBlock;
import com.terraformersmc.terrestria.config.TerrestriaConfigManager;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;

import net.minecraft.block.BlockState;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class MegaTrunkPlacer extends TrunkPlacer {
	public static final Codec<MegaTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
		method_28904(instance).apply(instance, MegaTrunkPlacer::new)
	);

	public MegaTrunkPlacer(int height, int randomHeight, int extraRandomHeight) {
		super(height, randomHeight, extraRandomHeight);
	}

	protected TrunkPlacerType<?> getType() {
		return TrunkPlacerType.GIANT_TRUNK_PLACER;
	}

	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {
		// Set the blocks below the trunk to dirt
		BlockPos down = pos.down();
		method_27400(world, down);
		method_27400(world, down.east());
		method_27400(world, down.south());
		method_27400(world, down.south().east());

		// Place the trunk
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for(int i = 0; i < trunkHeight; ++i) {
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHWEST), pos, 0, i, 0);
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHEAST), pos, 1, i, 0);
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHEAST), pos, 1, i, 1);
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHWEST), pos, 0, i, 1);
		}

		// If roots are available, grow them
		if (treeFeatureConfig instanceof QuarteredMegaTreeConfig) {
			growRoots(set, world, pos.mutableCopy(), random, blockBox, (QuarteredMegaTreeConfig) treeFeatureConfig);
		}

		return ImmutableList.of(new FoliagePlacer.TreeNode(pos.up(trunkHeight), 0, true));
	}

	static BlockState getState(Random random, BlockPos pos, TreeFeatureConfig config, QuarterLogBlock.BarkSide side) {
		// TODO: Quarter logs aren't generated
		if (config instanceof QuarteredMegaTreeConfig && TerrestriaConfigManager.getGeneralConfig().areQuarterLogsEnabled()) {
			return ((QuarteredMegaTreeConfig) config).quarterLogBlock.with(QuarterLogBlock.BARK_SIDE, side);
		} else {
			return config.trunkProvider.getBlockState(random, pos);
		}
	}

	private static void setLog(ModifiableTestableWorld modifiableTestableWorld, BlockPos.Mutable mutable, Set<BlockPos> set, BlockBox blockBox, BlockState state, BlockPos blockPos, int i, int j, int k) {
		mutable.set(blockPos, i, j, k);

		setLog(modifiableTestableWorld, mutable, set, blockBox, state);
	}

	protected static void setLog(ModifiableTestableWorld modifiableTestableWorld, BlockPos mutable, Set<BlockPos> set, BlockBox blockBox, BlockState state) {
		if (TreeFeature.canReplace(modifiableTestableWorld, mutable)) {
			method_27404(modifiableTestableWorld, mutable, state, blockBox);
			set.add(mutable.toImmutable());
		}
	}

	public void growRoots(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, Random random, BlockBox box, QuarteredMegaTreeConfig treeFeatureConfig) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(logs, world, pos.set(x - 1, y, z + random.nextInt(2)), random, box, treeFeatureConfig);
		tryGrowRoot(logs, world, pos.set(x + 2, y, z + random.nextInt(2)), random, box, treeFeatureConfig);
		tryGrowRoot(logs, world, pos.set(x + random.nextInt(2), y, z - 1), random, box, treeFeatureConfig);
		tryGrowRoot(logs, world, pos.set(x + random.nextInt(2), y, z + 2), random, box, treeFeatureConfig);
	}

	public void tryGrowRoot(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable bottom, Random random, BlockBox box, QuarteredMegaTreeConfig treeFeatureConfig) {
		//Determine the root length
		if (random.nextInt(5) == 0) {
			return;
		}

		//Determine how high up on the tree it should be placed
		int height = random.nextInt(4) + 1;

		//Place the root
		for (int i = 0; i < height; i++) {
			if (TreeFeature.canTreeReplace(world, bottom) || TreeFeature.canReplace(world, bottom) || world.testBlockState(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				method_27404(world, bottom, treeFeatureConfig.woodBlock, box);
				logs.add(bottom.toImmutable());
			}

			bottom.move(Direction.UP);
		}
	}
}
