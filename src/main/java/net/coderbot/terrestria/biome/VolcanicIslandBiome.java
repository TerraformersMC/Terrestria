package net.coderbot.terrestria.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureConfig;

public class VolcanicIslandBiome extends CalderaBiome {
	public VolcanicIslandBiome(Settings settings, int trees, Feature<DefaultFeatureConfig> tree1, Feature<DefaultFeatureConfig> tree2, float volcanoChance) {
		super(settings);

		this.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Biome.configureFeature(
						Feature.RANDOM_SELECTOR,
						new RandomFeatureConfig(
								new Feature[]{ tree2 },
								new FeatureConfig[]{ FeatureConfig.DEFAULT },
								new float[]{ 0.3F },

								tree1,
								FeatureConfig.DEFAULT
						),
						Decorator.COUNT_EXTRA_HEIGHTMAP,
						new CountExtraChanceDecoratorConfig(trees, 0.1F, 1)
				)
		);
	}
}
