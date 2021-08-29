package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.wood.block.QuarterLogBlock;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;

import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
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
		return TerrestriaTrunkPlacerTypes.MEGA;
	}

	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {
		// Set the blocks below the trunk to dirt
		BlockPos down = pos.down();
		setToDirt(world, down);
		setToDirt(world, down.east());
		setToDirt(world, down.south());
		setToDirt(world, down.south().east());

		// Place the trunk
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for(int i = 0; i < trunkHeight; ++i) {
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHWEST), pos, 0, i, 0);
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHEAST), pos, 1, i, 0);
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHEAST), pos, 1, i, 1);
			setLog(world, mutable, set, blockBox, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHWEST), pos, 0, i, 1);
		}

		BlockStateProvider rootsProvider = treeFeatureConfig.trunkProvider;

		if (treeFeatureConfig instanceof QuarteredMegaTreeConfig) {
			rootsProvider = ((QuarteredMegaTreeConfig) treeFeatureConfig).rootsProvider;
		}

		growRoots(set, world, pos.mutableCopy(), random, blockBox, rootsProvider);

		return ImmutableList.of(new FoliagePlacer.TreeNode(pos.up(trunkHeight), 0, true));
	}

	static BlockState getState(Random random, BlockPos pos, TreeFeatureConfig config, QuarterLogBlock.BarkSide side) {
		// TODO: Quarter logs aren't generated
		if (config instanceof QuarteredMegaTreeConfig && Terrestria.getConfigManager().getGeneralConfig().areQuarterLogsEnabled()) {
			return ((QuarteredMegaTreeConfig) config).quarteredTrunkProvider.getBlockState(random, pos).with(QuarterLogBlock.BARK_SIDE, side);
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
			setBlockState(modifiableTestableWorld, mutable, state, blockBox);
			set.add(mutable.toImmutable());
		}
	}

	public void growRoots(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, Random random, BlockBox box, BlockStateProvider wood) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(logs, world, pos.set(x - 1, y, z + random.nextInt(2)), random, box, wood);
		tryGrowRoot(logs, world, pos.set(x + 2, y, z + random.nextInt(2)), random, box, wood);
		tryGrowRoot(logs, world, pos.set(x + random.nextInt(2), y, z - 1), random, box, wood);
		tryGrowRoot(logs, world, pos.set(x + random.nextInt(2), y, z + 2), random, box, wood);
	}

	public void tryGrowRoot(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable bottom, Random random, BlockBox box, BlockStateProvider wood) {
		//Determine the root length
		if (random.nextInt(5) == 0) {
			return;
		}

		//Determine how high up on the tree it should be placed
		int height = random.nextInt(4) + 1;

		//Place the root
		for (int i = 0; i < height; i++) {
			if (TreeFeature.canTreeReplace(world, bottom) || TreeFeature.canReplace(world, bottom) || world.testBlockState(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				setBlockState(world, bottom, wood.getBlockState(random, bottom), box);
				logs.add(bottom.toImmutable());
			}

			bottom.move(Direction.UP);
		}
	}
}
