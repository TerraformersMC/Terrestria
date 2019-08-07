package com.terraformersmc.terrestria.biome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.terraformersmc.terraform.biome.builder.BuilderBiomeSettings;
import com.terraformersmc.terraform.biome.builder.DefaultFeature;
import com.terraformersmc.terraform.biome.builder.FeatureEntry;
import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terraform.biome.builder.TerraformBiome.Builder;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DoublePlantFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

public class BetterBiomeBuilder extends BuilderBiomeSettings {

	private ArrayList<DefaultFeature> defaultFeatures = new ArrayList<>();
	private ArrayList<FeatureEntry> features = new ArrayList<>();
	private Map<StructureFeature<FeatureConfig>, FeatureConfig> structureFeatures = new HashMap<>();
	private Map<Feature<DefaultFeatureConfig>, Integer> treeFeatures = new HashMap<>();
	private Map<Feature<DefaultFeatureConfig>, Integer> rareTreeFeatures = new HashMap<>();
	private Map<BlockState, Integer> plantFeatures = new HashMap<>();
	private Map<BlockState, Integer> doublePlantFeatures = new HashMap<>();
	private ArrayList<SpawnEntry> spawnEntries = new ArrayList<>();
	private boolean template = false;
	private float spawnChance = 0.1F;
	// NOTE: Make sure to add any additional fields to the Template copy code down below!

	BetterBiomeBuilder() {
		super();

		parent(null);
	}

	BetterBiomeBuilder(BetterBiomeBuilder existing) {
		super(existing);

		this.defaultFeatures.addAll(existing.defaultFeatures);
		this.features.addAll(existing.features);
		this.structureFeatures.putAll(existing.structureFeatures);
		this.treeFeatures.putAll(existing.treeFeatures);
		this.rareTreeFeatures.putAll(existing.rareTreeFeatures);
		this.plantFeatures.putAll(existing.plantFeatures);
		this.doublePlantFeatures.putAll(existing.doublePlantFeatures);
		this.spawnEntries.addAll(existing.spawnEntries);
		
		this.spawnChance = existing.spawnChance;
	}

	public Biome build() {
		if(template) {
			throw new IllegalStateException("Tried to call build() on a frozen BetterBiomeBuilder instance!");
		}

		// Add SpawnEntries
		BetterBiome biome = new BetterBiome(this, this.spawnEntries, this.spawnChance);

		// Add structures
		for (Map.Entry<StructureFeature<FeatureConfig>, FeatureConfig> structure : structureFeatures.entrySet()) {
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

		for (FeatureEntry feature : features) {
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
	public <SC extends SurfaceConfig> BetterBiomeBuilder configureSurfaceBuilder(SurfaceBuilder<SC> builder, SC config) {
		super.configureSurfaceBuilder(builder, config);

		return this;
	}

	@Override
	public BetterBiomeBuilder surfaceBuilder(ConfiguredSurfaceBuilder<?> surfaceBuilder) {
		super.surfaceBuilder(surfaceBuilder);

		return this;
	}

	@Override
	public BetterBiomeBuilder precipitation(Biome.Precipitation precipitation) {
		super.precipitation(precipitation);

		return this;
	}

	@Override
	public BetterBiomeBuilder category(Biome.Category category) {
		super.category(category);

		return this;
	}

	@Override
	public BetterBiomeBuilder depth(float depth) {
		super.depth(depth);

		return this;
	}

	@Override
	public BetterBiomeBuilder scale(float scale) {
		super.scale(scale);

		return this;
	}

	@Override
	public BetterBiomeBuilder temperature(float temperature) {
		super.temperature(temperature);

		return this;
	}

	@Override
	public BetterBiomeBuilder downfall(float downfall) {
		super.downfall(downfall);

		return this;
	}

	@Override
	public BetterBiomeBuilder waterColor(int color) {
		super.waterColor(color);

		return this;
	}

	@Override
	public BetterBiomeBuilder waterFogColor(int color) {
		super.waterFogColor(color);

		return this;
	}

	@Override
	public BetterBiomeBuilder parent(String parent) {
		super.parent(parent);

		return this;
	}
	
	public BetterBiomeBuilder setSpawnChance(float spawnChance) {
		this.spawnChance = spawnChance;
		return this;
	}

	public BetterBiomeBuilder addTreeFeature(Feature<DefaultFeatureConfig> feature, int numPerChunk) {
		this.treeFeatures.put(feature, numPerChunk);
		return this;
	}

	public BetterBiomeBuilder addRareTreeFeature(Feature<DefaultFeatureConfig> feature, int chance) {
		this.rareTreeFeatures.put(feature, chance);
		return this;
	}

	public BetterBiomeBuilder addGrassFeature(BlockState blockState, int count) {
		this.plantFeatures.put(blockState, count);
		return this;
	}

	public BetterBiomeBuilder addDoubleGrassFeature(BlockState blockState, int count) {
		this.doublePlantFeatures.put(blockState, count);
		return this;
	}

	public BetterBiomeBuilder addCustomFeature(GenerationStep.Feature step, ConfiguredFeature feature) {
		this.features.add(new FeatureEntry(step, feature));
		return this;
	}

	public BetterBiomeBuilder addSpawnEntry(SpawnEntry entry) {
		this.spawnEntries.add(entry);
		return this;
	}

	public BetterBiomeBuilder addStructureFeature(StructureFeature<DefaultFeatureConfig> feature) {
		return this.addStructureFeature(feature, FeatureConfig.DEFAULT);
	}

	public <FC extends FeatureConfig> BetterBiomeBuilder addStructureFeature(StructureFeature<FC> feature, FC config) {
		this.structureFeatures.put((StructureFeature)feature, config);
		return this;
	}

	public BetterBiomeBuilder addStructureFeatures(StructureFeature<DefaultFeatureConfig>... defaultStructureFeatures) {
		for (StructureFeature<DefaultFeatureConfig> feature : defaultStructureFeatures) {
			this.structureFeatures.put((StructureFeature) feature, FeatureConfig.DEFAULT);
		}
		return this;
	}

	public BetterBiomeBuilder addDefaultFeature(DefaultFeature feature) {
		defaultFeatures.add(feature);
		return this;
	}

	public BetterBiomeBuilder addDefaultFeatures(DefaultFeature... features) {
		defaultFeatures.addAll(Arrays.asList(features));
		return this;
	}

	public BetterBiomeBuilder addDefaultSpawnEntries() {
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
	
	public static class BetterBiome extends Biome {
		
		private final float spawnChance;

		private BetterBiome(Biome.Settings biomeSettings, ArrayList<SpawnEntry> spawns, float spawnChance) {
			super(biomeSettings);
			for (SpawnEntry entry : spawns) {
				this.addSpawn(entry.type.getCategory(), entry);
			}
			this.spawnChance = spawnChance;
		}
		
		@Override
		public float getMaxSpawnLimit() {
			return this.spawnChance;
		}
	}
	
	public static BetterBiomeBuilder builder() {
		return new BetterBiomeBuilder();
	}
}
