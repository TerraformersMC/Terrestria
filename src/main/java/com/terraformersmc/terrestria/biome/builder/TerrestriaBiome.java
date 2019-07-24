package com.terraformersmc.terrestria.biome.builder;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TerrestriaBiome extends Biome {

	private TerrestriaBiome(Biome.Settings biomeSettings, ArrayList<SpawnEntry> spawns) {
		super(biomeSettings);
		for (SpawnEntry entry : spawns) {
			this.addSpawn(entry.type.getCategory(), entry);
		}
	}

	public static TerrestriaBiome.Builder builder() {
		return new Builder();
	}

	public static final class Builder extends BuilderBiomeSettings {

		private ArrayList<DefaultFeature> defaultFeatures = new ArrayList<>();
		private ArrayList<TerrestriaFeature> features = new ArrayList<>();
		private Map<StructureFeature, FeatureConfig> structureFeatures = new HashMap<>();
		private Map<Feature<DefaultFeatureConfig>, Integer> treeFeatures = new HashMap<>();
		private Map<Feature<DefaultFeatureConfig>, Integer> rareTreeFeatures = new HashMap<>();
		private Map<BlockState, Integer> plantFeatures = new HashMap<>();
		private Map<BlockState, Integer> doublePlantFeatures = new HashMap<>();
		private ArrayList<Biome.SpawnEntry> spawnEntries = new ArrayList<>();
		// NOTE: Make sure to add any additional fields to the Frozen copy code down below!

		Builder() {
			super();

			parent(null);
		}

		public Biome build() {
			// Add SpawnEntries
			TerrestriaBiome biome = new TerrestriaBiome(this, this.spawnEntries);

			// Add structures
			for (Map.Entry<StructureFeature, FeatureConfig> structure : structureFeatures.entrySet()) {
				biome.addStructureFeature(structure.getKey(), structure.getValue());
			}

			// Tree Feature stuff
			if (treeFeatures.size() > 0) {

				// Determine the total tree count

				int totalTreesPerChunk = 0;
				for (Integer count : treeFeatures.values()) {
					totalTreesPerChunk += count;
				}

				// Add each tree

				for (Map.Entry<Feature<DefaultFeatureConfig>, Integer> tree : treeFeatures.entrySet()) {
					Feature<DefaultFeatureConfig> feature = tree.getKey();
					int count = tree.getValue();

					float weight = (float) count / totalTreesPerChunk;

					biome.addFeature(
							GenerationStep.Feature.VEGETAL_DECORATION,
							Biome.configureFeature(
									feature,
									FeatureConfig.DEFAULT,
									Decorator.COUNT_EXTRA_HEIGHTMAP,
									new CountExtraChanceDecoratorConfig(count, 0.1F * weight, 1)
							)
					);
				}
			}

			// Rare tree features

			for (Map.Entry<Feature<DefaultFeatureConfig>, Integer> tree : rareTreeFeatures.entrySet()) {
				Feature<DefaultFeatureConfig> feature = tree.getKey();
				int chance = tree.getValue();

				biome.addFeature(
						GenerationStep.Feature.VEGETAL_DECORATION,
						Biome.configureFeature(
								feature,
								FeatureConfig.DEFAULT,
								Decorator.CHANCE_HEIGHTMAP,
								new ChanceDecoratorConfig(chance)
						)
				);
			}

			// Add any minecraft (default) features

			for (DefaultFeature defaultFeature : defaultFeatures) {
				defaultFeature.add(biome);
			}

			// Add custom features that don't fit in the templates

			for (TerrestriaFeature feature : features) {
				biome.addFeature(feature.getStep(), feature.getFeature());
			}

			// Add Plant decoration features

			for (Map.Entry<BlockState, Integer> plant : plantFeatures.entrySet()) {
				biome.addFeature(
						GenerationStep.Feature.VEGETAL_DECORATION,
						Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(plant.getKey()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(plant.getValue())));
			}

			// Add Double Plant decoration features

			for (Map.Entry<BlockState, Integer> doublePlant : doublePlantFeatures.entrySet()) {
				biome.addFeature(
						GenerationStep.Feature.VEGETAL_DECORATION,
						Biome.configureFeature(Feature.DOUBLE_PLANT, new DoublePlantFeatureConfig(doublePlant.getKey()), Decorator.COUNT_HEIGHTMAP_32, new CountDecoratorConfig(doublePlant.getValue())));
			}


			return biome;
		}

		@Override
		public <SC extends SurfaceConfig> Builder configureSurfaceBuilder(SurfaceBuilder<SC> builder, SC config) {
			super.configureSurfaceBuilder(builder, config);

			return this;
		}

		@Override
		public Builder surfaceBuilder(ConfiguredSurfaceBuilder<?> surfaceBuilder) {
			super.surfaceBuilder(surfaceBuilder);

			return this;
		}

		@Override
		public Builder precipitation(Biome.Precipitation precipitation) {
			super.precipitation(precipitation);

			return this;
		}

		@Override
		public Builder category(Biome.Category category) {
			super.category(category);

			return this;
		}

		@Override
		public Builder depth(float depth) {
			super.depth(depth);

			return this;
		}

		@Override
		public Builder scale(float scale) {
			super.scale(scale);

			return this;
		}

		@Override
		public Builder temperature(float temperature) {
			super.temperature(temperature);

			return this;
		}

		@Override
		public Builder downfall(float downfall) {
			super.downfall(downfall);

			return this;
		}

		@Override
		public Builder waterColor(int color) {
			super.waterColor(color);

			return this;
		}

		@Override
		public Builder waterFogColor(int color) {
			super.waterFogColor(color);

			return this;
		}

		@Override
		public Builder parent(String parent) {
			super.parent(parent);

			return this;
		}
		
		public TerrestriaBiome.Builder addTreeFeature(Feature<DefaultFeatureConfig> feature, int numPerChunk) {
			this.treeFeatures.put(feature, numPerChunk);
			return this;
		}

		public TerrestriaBiome.Builder addRareTreeFeature(Feature<DefaultFeatureConfig> feature, int chance) {
			this.rareTreeFeatures.put(feature, chance);
			return this;
		}

		public TerrestriaBiome.Builder addGrassFeature(BlockState blockState, int count) {
			this.plantFeatures.put(blockState, count);
			return this;
		}

		public TerrestriaBiome.Builder addDoubleGrassFeature(BlockState blockState, int count) {
			this.doublePlantFeatures.put(blockState, count);
			return this;
		}

		public TerrestriaBiome.Builder addCustomFeature(GenerationStep.Feature step, ConfiguredFeature feature) {
			this.features.add(new TerrestriaFeature(step, feature));
			return this;
		}

		public TerrestriaBiome.Builder addSpawnEntry(SpawnEntry entry) {
			this.spawnEntries.add(entry);
			return this;
		}

		public TerrestriaBiome.Builder addStructureFeature(StructureFeature feature) {
			this.addStructureFeature(feature, FeatureConfig.DEFAULT);
			return this;
		}

		public TerrestriaBiome.Builder addStructureFeature(StructureFeature feature, FeatureConfig config) {
			this.structureFeatures.put(feature, config);
			return this;
		}

		public TerrestriaBiome.Builder addStructureFeatures(StructureFeature... defaultStructureFeatures) {
			for (StructureFeature feature : defaultStructureFeatures) {
				this.structureFeatures.put(feature, FeatureConfig.DEFAULT);
			}
			return this;
		}

		public TerrestriaBiome.Builder addDefaultFeature(DefaultFeature feature) {
			defaultFeatures.add(feature);
			return this;
		}

		public TerrestriaBiome.Builder addDefaultFeatures(DefaultFeature... features) {
			defaultFeatures.addAll(Arrays.asList(features));
			return this;
		}

		public TerrestriaBiome.Builder addDefaultSpawnEntries() {
			this.addSpawnEntry(new Biome.SpawnEntry(EntityType.SHEEP, 12, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.PIG, 10, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.CHICKEN, 10, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.COW, 8, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.BAT, 10, 8, 8))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.SPIDER, 100, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.SKELETON, 100, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.CREEPER, 100, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.SLIME, 100, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.WITCH, 5, 1, 1));
			return this;
		}
	}

	public static final class Frozen {
		private final Builder builder;

		Frozen(Builder builder) {
			this.builder = builder;
		}
	}
}
