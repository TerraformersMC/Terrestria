package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.feature.placer.foliage.CanopyFoliagePlacer;
import com.terraformersmc.terrestria.feature.placer.foliage.NoneFoliagePlacer;
import com.terraformersmc.terrestria.feature.placer.foliage.PalmFanFoliagePlacer;
import com.terraformersmc.terrestria.feature.placer.trunk.BentTrunkPlacer;
import com.terraformersmc.terrestria.feature.placer.trunk.CanopyTree4BranchTrunkPlacer;
import com.terraformersmc.terrestria.feature.placer.trunk.FallenStraightTrunkPlacer;
import com.terraformersmc.terrestria.feature.treeconfigs.SandyTreeConfig;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class TerrestriaFeatureConfigs {

	public static TreeFeatureConfig JUNGLE_PALM_TREE;
	public static TreeFeatureConfig WILLOW_TREE;

	public static TreeFeatureConfig SMALL_HEMLOCK_TREE;
	public static TreeFeatureConfig SMALL_REDWOOD_TREE;

	public static TreeFeatureConfig FALLEN_HEMLOCK_LOG;
	public static TreeFeatureConfig FALLEN_REDWOOD_LOG;

	public static TreeFeatureConfig JAPANESE_MAPLE_SHRUB;

	public static void init() {
		JUNGLE_PALM_TREE = new SandyTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(Blocks.JUNGLE_WOOD.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.JUNGLE_PALM_LEAVES.getDefaultState()),
				new PalmFanFoliagePlacer(3, 0, 0, 0),
				new BentTrunkPlacer(15, 15, 15),
				new TwoLayersFeatureSize(1, 0, 2))

				.ignoreVines()
				.build());
		SMALL_HEMLOCK_TREE = spruceOf(TerrestriaBlocks.HEMLOCK);
		SMALL_REDWOOD_TREE = spruceOf(TerrestriaBlocks.REDWOOD);
		FALLEN_HEMLOCK_LOG = fallenLogOf(TerrestriaBlocks.HEMLOCK, new FallenStraightTrunkPlacer(5, 8, 1));
		FALLEN_REDWOOD_LOG = fallenLogOf(TerrestriaBlocks.REDWOOD, new FallenStraightTrunkPlacer(5, 8, 1));
		JAPANESE_MAPLE_SHRUB = shrubOf(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(), TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState());
		WILLOW_TREE = droopyOf(TerrestriaBlocks.WILLOW, 2, 4, 3);
	}

	static TreeFeatureConfig droopyOf(WoodBlocks woodBlocks, int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		return droopyOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), baseHeight, firstRandomHeight, secondRandomHeight);
	}

	static TreeFeatureConfig droopyOf(BlockState log, BlockState leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new CanopyFoliagePlacer(0, 0, 0, 0),
				new CanopyTree4BranchTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight),
				new TwoLayersFeatureSize(1, 0 , 1))
				//some dangly bit decorator
				//.decorators()
				.build();
	}

	static TreeFeatureConfig fallenLogOf(WoodBlocks woodBlocks, FallenStraightTrunkPlacer trunk) {
		return fallenLogOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), trunk);
	}

	static TreeFeatureConfig fallenLogOf(BlockState log, BlockState leaves, FallenStraightTrunkPlacer trunk) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new NoneFoliagePlacer(),
				new FallenStraightTrunkPlacer(5, 8, 1),
				new TwoLayersFeatureSize(0, 0, 0))

				.build();
	}

	static TreeFeatureConfig shrubOf(WoodBlocks woodBlocks) {
		return shrubOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState());
	}

	static TreeFeatureConfig shrubOf(BlockState log, BlockState leaves) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new BushFoliagePlacer(2, 0, 1, 0, 2),
				new StraightTrunkPlacer(1, 0, 0),
				new TwoLayersFeatureSize(0, 0, 0))

				.heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES)
				.build();
	}

	static TreeFeatureConfig spruceOf(WoodBlocks woodBlocks) {
		return spruceOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState());
	}

	static TreeFeatureConfig spruceOf(BlockState log, BlockState leaves) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new SpruceFoliagePlacer(2, 1, 0, 2, 1, 1),
				new StraightTrunkPlacer(5, 2, 1),
				new TwoLayersFeatureSize(2, 0, 2))

				.ignoreVines()
				.build();
	}
}
