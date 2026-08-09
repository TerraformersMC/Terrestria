package com.terraformersmc.terrestria.feature.tree.treeconfigs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Optional;

public class QuarteredMegaTreeConfiguration extends TreeConfiguration {
	private static final MapCodec<TreeConfiguration> SUPER_CODEC = MapCodec.assumeMapUnsafe(TreeConfiguration.CODEC);
	public static final MapCodec<QuarteredMegaTreeConfiguration> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			SUPER_CODEC.forGetter(config -> config),
			BlockStateProvider.CODEC.fieldOf("quartered_trunk_provider").forGetter(config -> config.quarteredTrunkProvider),
			BlockStateProvider.CODEC.fieldOf("roots_provider").forGetter(config -> config.rootsProvider)
		).apply(instance, QuarteredMegaTreeConfiguration::new)
	);

	public final BlockStateProvider quarteredTrunkProvider;
	public final BlockStateProvider rootsProvider;

	public QuarteredMegaTreeConfiguration(TreeConfiguration config, BlockStateProvider quarteredTrunkProvider, BlockStateProvider rootsProvider) {
		super(config.trunkProvider, config.trunkPlacer, config.foliageProvider, config.foliagePlacer, Optional.empty(), config.minimumSize, config.decorators, config.ignoreVines, config.belowTrunkProvider);

		this.quarteredTrunkProvider = quarteredTrunkProvider;
		this.rootsProvider = rootsProvider;
	}

	public static Codec<TreeConfiguration> getCodec() {
		// Our Codec is a perfect superset of Codec<TreeConfiguration>, serializing all its parameters
		// to and from QuarteredMegaTreeConfiguration which extends TreeConfiguration, so this is safe.
		//noinspection unchecked, rawtypes
		return (Codec<TreeConfiguration>)(Codec) CODEC.codec();
	}

	public static class QuarteredMegaTreeConfigurationBuilder {
		public final TreeConfigurationBuilder treeConfigurationBuilder;
		private final BlockStateProvider quarteredTrunkProvider;
		private final BlockStateProvider rootsProvider;

		public QuarteredMegaTreeConfigurationBuilder(TreeConfigurationBuilder treeConfigurationBuilder, BlockStateProvider quarteredTrunkProvider, BlockStateProvider rootsProvider) {
			this.treeConfigurationBuilder = treeConfigurationBuilder;
			this.quarteredTrunkProvider = quarteredTrunkProvider;
			this.rootsProvider = rootsProvider;
		}

		public QuarteredMegaTreeConfiguration build() {
			return new QuarteredMegaTreeConfiguration(
				this.treeConfigurationBuilder.build(),
				this.quarteredTrunkProvider,
				this.rootsProvider
			);
		}
	}
}
