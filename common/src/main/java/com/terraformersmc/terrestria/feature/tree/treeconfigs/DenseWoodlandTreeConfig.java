package com.terraformersmc.terrestria.feature.tree.treeconfigs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class DenseWoodlandTreeConfig extends TreeFeatureConfig {
	public final BlockStateProvider limbProvider;
	public final BlockStateProvider rootsProvider;

	public static final Codec<DenseWoodlandTreeConfig> CODEC = RecordCodecBuilder.create (
		instance -> instance.group (
			BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter(config -> config.trunkProvider),
			TrunkPlacer.TYPE_CODEC.fieldOf("trunk_placer").forGetter(config -> config.trunkPlacer),
			BlockStateProvider.TYPE_CODEC.fieldOf("foliage_provider").forGetter(config -> config.foliageProvider),
			FoliagePlacer.TYPE_CODEC.fieldOf("foliage_placer").forGetter(config -> config.foliagePlacer),
			BlockStateProvider.TYPE_CODEC.fieldOf("dirt_provider").forGetter(config -> config.dirtProvider),
			FeatureSize.TYPE_CODEC.fieldOf("minimum_size").forGetter(config -> config.minimumSize),
			TreeDecorator.TYPE_CODEC.listOf().fieldOf("decorators").forGetter(config -> config.decorators),
			Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter(config -> config.ignoreVines),
			Codec.BOOL.fieldOf("force_dirt").orElse(false).forGetter(config -> config.forceDirt),
			BlockStateProvider.TYPE_CODEC.fieldOf("limb_provider").forGetter(config -> config.limbProvider),
			BlockStateProvider.TYPE_CODEC.fieldOf("roots_provider").forGetter(config -> config.rootsProvider)
		).apply(instance, DenseWoodlandTreeConfig::new)
	);

	protected DenseWoodlandTreeConfig(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, BlockStateProvider dirtProvider, FeatureSize minimumSize, List<TreeDecorator> decorators, boolean ignoreVines, boolean forceDirt, BlockStateProvider limbProvider, BlockStateProvider rootsProvider) {
		super(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, Optional.empty(),
				dirtProvider, minimumSize, decorators, ignoreVines, forceDirt);

		this.limbProvider = limbProvider;
		this.rootsProvider = rootsProvider;
	}

	public DenseWoodlandTreeConfig(TreeFeatureConfig config, BlockStateProvider limbProvider, BlockStateProvider rootsProvider) {
		super(config.trunkProvider, config.trunkPlacer, config.foliageProvider, config.foliagePlacer, config.rootPlacer,
				config.dirtProvider, config.minimumSize, config.decorators, config.ignoreVines, config.forceDirt);

		this.limbProvider = limbProvider;
		this.rootsProvider = rootsProvider;
	}
}
