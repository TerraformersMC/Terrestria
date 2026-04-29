package com.terraformersmc.terrestria.feature.tree.treeconfigs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class QuarteredMegaTreeConfig extends TreeConfiguration {
	// begin: vanilla copy
	public static final Codec<QuarteredMegaTreeConfig> CODEC = RecordCodecBuilder.create (
		instance -> instance.group (
			BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(config -> config.trunkProvider),
			TrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter(config -> config.trunkPlacer),
			BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter(config -> config.foliageProvider),
			FoliagePlacer.CODEC.fieldOf("foliage_placer").forGetter(config -> config.foliagePlacer),
			FeatureSize.CODEC.fieldOf("minimum_size").forGetter(config -> config.minimumSize),
			TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter(config -> config.decorators),
			Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter(config -> config.ignoreVines),
			BlockStateProvider.CODEC.fieldOf("dirt_provider").forGetter(config -> config.belowTrunkProvider),
			BlockStateProvider.CODEC.fieldOf("quartered_trunk_provider").forGetter(config -> config.quarteredTrunkProvider),
			BlockStateProvider.CODEC.fieldOf("roots_provider").forGetter(config -> config.rootsProvider)
		).apply(instance, QuarteredMegaTreeConfig::new)
	);
	// end: vanilla copy

	public final BlockStateProvider quarteredTrunkProvider;
	public final BlockStateProvider rootsProvider;

	// TODO: Consider whether the new Optional<RootPlacer> arg is useful to us
	protected QuarteredMegaTreeConfig(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer,
                                      BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer,
                                      FeatureSize minimumSize, List<TreeDecorator> decorators,
                                      boolean ignoreVines, BlockStateProvider belowTrunkProvider,
                                      BlockStateProvider quarteredTrunkProvider,
                                      BlockStateProvider rootsProvider) {
		super(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, Optional.empty(),
				minimumSize, decorators, ignoreVines, belowTrunkProvider);

		this.quarteredTrunkProvider = quarteredTrunkProvider;
		this.rootsProvider = rootsProvider;
	}
	
	public QuarteredMegaTreeConfig(TreeConfiguration config, BlockStateProvider quarteredTrunkProvider, BlockStateProvider rootsProvider) {
		super(config.trunkProvider, config.trunkPlacer, config.foliageProvider, config.foliagePlacer, Optional.empty(), config.minimumSize, config.decorators, config.ignoreVines, config.belowTrunkProvider);

		this.quarteredTrunkProvider = quarteredTrunkProvider;
		this.rootsProvider = rootsProvider;
	}
}
