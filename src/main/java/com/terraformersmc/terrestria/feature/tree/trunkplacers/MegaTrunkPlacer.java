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
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
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
		BlockPos blockPos = pos.down();
		method_27400(world, blockPos);
		method_27400(world, blockPos.east());
		method_27400(world, blockPos.south());
		method_27400(world, blockPos.south().east());
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for(int i = 0; i < trunkHeight; ++i) {
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHWEST), pos, 0, i, 0);
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHEAST), pos, 1, i, 0);
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHEAST), pos, 1, i, 1);
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHWEST), pos, 0, i, 1);
		}

		return ImmutableList.of(new FoliagePlacer.TreeNode(pos.up(trunkHeight), 0, true));
	}

	private static BlockState getState(Random random, BlockPos pos, TreeFeatureConfig config, QuarterLogBlock.BarkSide side) {
		if (config instanceof QuarteredMegaTreeConfig && TerrestriaConfigManager.getGeneralConfig().areQuarterLogsEnabled()) {
			return ((QuarteredMegaTreeConfig) config).quarterLogBlock.with(QuarterLogBlock.BARK_SIDE, side);
		} else {
			return config.trunkProvider.getBlockState(random, pos);
		}
	}

	private static void setLog(ModifiableTestableWorld modifiableTestableWorld, BlockPos.Mutable mutable, Set<BlockPos> set, BlockBox blockBox, BlockState state, BlockPos blockPos, int i, int j, int k) {
		mutable.set(blockPos, i, j, k);

		if (TreeFeature.canReplace(modifiableTestableWorld, mutable)) {
			method_27404(modifiableTestableWorld, mutable, state, blockBox);
			set.add(mutable.toImmutable());
		}
	}
}
