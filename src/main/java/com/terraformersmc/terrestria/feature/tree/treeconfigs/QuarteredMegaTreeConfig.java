package com.terraformersmc.terrestria.feature.tree.treeconfigs;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.tree.TreeDecorator;
import net.minecraft.world.gen.trunk.TrunkPlacer;

public class QuarteredMegaTreeConfig extends TreeFeatureConfig {
	// begin: vanilla copy
	public static final Codec<QuarteredMegaTreeConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(
				BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.trunkProvider;
		}), BlockStateProvider.TYPE_CODEC.fieldOf("leaves_provider").forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.leavesProvider;
		}), FoliagePlacer.TYPE_CODEC.fieldOf("foliage_placer").forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.foliagePlacer;
		}), TrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.trunkPlacer;
		}), FeatureSize.TYPE_CODEC.fieldOf("minimum_size").forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.minimumSize;
		}), TreeDecorator.TYPE_CODEC.listOf().fieldOf("decorators").forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.decorators;
		}), Codec.INT.fieldOf("max_water_depth").orElse(0).forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.maxWaterDepth;
		}), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter(config -> config.ignoreVines),
			Heightmap.Type.CODEC.fieldOf("heightmap").forGetter(config -> config.heightmap),
			BlockStateProvider.TYPE_CODEC.fieldOf("quartered_trunk_provider").forGetter(config -> config.quarteredTrunkProvider),
			BlockStateProvider.TYPE_CODEC.fieldOf("roots_provider").forGetter(config -> config.rootsProvider)
		).apply(instance, QuarteredMegaTreeConfig::new);
	});
	// end: vanilla copy

	public final BlockStateProvider quarteredTrunkProvider;
	public final BlockStateProvider rootsProvider;

	protected QuarteredMegaTreeConfig(BlockStateProvider trunkProvider, BlockStateProvider leavesProvider,
	                            FoliagePlacer foliagePlacer, TrunkPlacer trunkPlacer, FeatureSize minimumSize, 
	                            List<TreeDecorator> decorators, int maxWaterDepth, boolean ignoreVines, 
	                            Heightmap.Type heightmap, BlockStateProvider quarteredTrunkProvider,
	                            BlockStateProvider rootsProvider) {
		super(trunkProvider, leavesProvider, foliagePlacer, trunkPlacer, minimumSize, decorators, maxWaterDepth,
				ignoreVines, heightmap);

		this.quarteredTrunkProvider = quarteredTrunkProvider;
		this.rootsProvider = rootsProvider;
	}
	
	public QuarteredMegaTreeConfig(TreeFeatureConfig config, BlockStateProvider quarteredTrunkProvider, BlockStateProvider rootsProvider) {
		super(config.trunkProvider, config.leavesProvider, config.foliagePlacer, config.trunkPlacer, config.minimumSize, config.decorators, config.maxWaterDepth, config.ignoreVines, config.heightmap);

		this.quarteredTrunkProvider = quarteredTrunkProvider;
		this.rootsProvider = rootsProvider;
	}
}
