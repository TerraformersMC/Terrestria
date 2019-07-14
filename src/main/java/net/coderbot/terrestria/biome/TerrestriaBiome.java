package net.coderbot.terrestria.biome;

import net.coderbot.terrestria.Terrestria;
import net.coderbot.terrestria.feature.TerrestriaFeature;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TerrestriaBiome extends Biome {

    private static Biome biome;

    private TerrestriaBiome(Biome.Settings biomeSettings, ArrayList<SpawnEntry> spawns) {
        super(biomeSettings);
        for (SpawnEntry entry : spawns) {
            this.addSpawn(entry.type.getCategory(), entry);
        }
    }

    public static TerrestriaBiome.Builder builder() {
        return new Builder();
    }
    
    public static final class Builder {

        private Biome.Settings biomeSettings;
        private ArrayList<DefaultFeature> defaultFeatures;
        private ArrayList<TerrestriaFeature> features;
        private Map<StructureFeature, FeatureConfig> structureFeatures;
        private ConfiguredSurfaceBuilder surfaceBuilder;
        private Biome.Precipitation precipitation;
        private Biome.Category category;
        private float depth;
        private float scale;
        private float temperature;
        private float downfall;
        private int waterColor;
        private int waterFogColor;
        private ArrayList<Biome.SpawnEntry> spawnEntries;

        public Biome build() {
            biomeSettings = new Biome.Settings().parent(null).category(category).depth(depth).scale(scale)
                    .precipitation(precipitation).temperature(temperature).downfall(downfall).waterColor(waterColor)
                    .waterFogColor(waterFogColor)
                    .configureSurfaceBuilder(this.surfaceBuilder.surfaceBuilder, this.surfaceBuilder.config); // more
            TerrestriaBiome.biome = new TerrestriaBiome(this.biomeSettings, this.spawnEntries);
            for (DefaultFeature defaultFeature : defaultFeatures) {
                buildDefaultFeature(defaultFeature);
            }
            for (StructureFeature structure : structureFeatures.keySet()) {
                TerrestriaBiome.biome.addStructureFeature(structure, structureFeatures.get(structure));
            }
            for (TerrestriaFeature feature : features) {
                TerrestriaBiome.biome.addFeature(feature.getStep(), feature.getFeature());
            }
            return TerrestriaBiome.biome;
        }

        public TerrestriaBiome.Builder settings(Biome.Settings settings) {
            this.biomeSettings = settings;
            return this;
        }

        public <SC extends SurfaceConfig> TerrestriaBiome.Builder configuredSurfaceBuilder(SurfaceBuilder<SC> surfaceBuilder, SC surfaceConfig) {
            this.surfaceBuilder = new ConfiguredSurfaceBuilder(surfaceBuilder, surfaceConfig);
            return this;
        }

        public TerrestriaBiome.Builder addTerrestriaFeature(GenerationStep.Feature step, ConfiguredFeature feature) {
            this.features.add(new TerrestriaFeature(step, feature));
            return this;
        }

        public TerrestriaBiome.Builder precipitation(Biome.Precipitation precipitation) {
            this.precipitation = precipitation;
            return this;
        }

        public TerrestriaBiome.Builder category(Biome.Category category) {
            this.category = category;
            return this;
        }

        public TerrestriaBiome.Builder depth(float depth) {
            this.depth = depth;
            return this;
        }

        public TerrestriaBiome.Builder scale(float scale) {
            this.scale = scale;
            return this;
        }

        public TerrestriaBiome.Builder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public TerrestriaBiome.Builder downfall(float downfall) {
            this.downfall = downfall;
            return this;
        }

        public TerrestriaBiome.Builder waterColor(int waterColor) {
            this.waterColor = waterColor;
            return this;
        }

        public TerrestriaBiome.Builder waterFogColor(int waterFogColor) {
            this.waterFogColor = waterFogColor;
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

        void buildDefaultFeature(DefaultFeature feature) {
            switch (feature) {
                case LAND_CARVERS:
                    DefaultBiomeFeatures.addLandCarvers(TerrestriaBiome.biome);
                case OCEAN_CARVERS:
                    DefaultBiomeFeatures.addOceanCarvers(TerrestriaBiome.biome);
                case STRUCTURES:
                    DefaultBiomeFeatures.addDefaultStructures(TerrestriaBiome.biome);
                case LAKES:
                    DefaultBiomeFeatures.addDefaultLakes(TerrestriaBiome.biome);
                case DESERT_LAKES:
                    DefaultBiomeFeatures.addDesertLakes(TerrestriaBiome.biome);
                case DUNGEONS:
                    DefaultBiomeFeatures.addDungeons(TerrestriaBiome.biome);
                case MINEABLES:
                    DefaultBiomeFeatures.addMineables(TerrestriaBiome.biome);
                case ORES:
                    DefaultBiomeFeatures.addDefaultOres(TerrestriaBiome.biome);
                case EXTRA_GOLD:
                    DefaultBiomeFeatures.addExtraGoldOre(TerrestriaBiome.biome);
                case EMERALD_ORE:
                    DefaultBiomeFeatures.addEmeraldOre(TerrestriaBiome.biome);
                case INFECTED_STONE:
                    DefaultBiomeFeatures.addInfestedStone(TerrestriaBiome.biome);
                case DISKS:
                    DefaultBiomeFeatures.addDefaultDisks(TerrestriaBiome.biome);
                case CLAY:
                    DefaultBiomeFeatures.addClay(TerrestriaBiome.biome);
                case MOSSY_ROCKS:
                    DefaultBiomeFeatures.addMossyRocks(TerrestriaBiome.biome);
                case LARGE_FERNS:
                    DefaultBiomeFeatures.addLargeFerns(TerrestriaBiome.biome);
                case SWEET_BERRY_BUSHES:
                    DefaultBiomeFeatures.addSweetBerryBushes(TerrestriaBiome.biome);
                case SWEET_BERRY_BUSHES_SNOWY:
                    DefaultBiomeFeatures.addSweetBerryBushesSnowy(TerrestriaBiome.biome);
                case BAMBOO:
                    DefaultBiomeFeatures.addBamboo(TerrestriaBiome.biome);
                case BAMBOO_JUNGLE_TREES:
                    DefaultBiomeFeatures.addBambooJungleTrees(TerrestriaBiome.biome);
                case TAIGA_TREES:
                    DefaultBiomeFeatures.addTaigaTrees(TerrestriaBiome.biome);
                case WATER_BIOME_OAK_TREES:
                    DefaultBiomeFeatures.addWaterBiomeOakTrees(TerrestriaBiome.biome);
                case BIRCH_TREES:
                    DefaultBiomeFeatures.addBirchTrees(TerrestriaBiome.biome);
                case FOREST_TREES:
                    DefaultBiomeFeatures.addForestTrees(TerrestriaBiome.biome);
                case TALL_BIRCH_TREES:
                    DefaultBiomeFeatures.addTallBirchTrees(TerrestriaBiome.biome);
                case SAVANNAH_TREES:
                    DefaultBiomeFeatures.addSavannaTrees(TerrestriaBiome.biome);
                case EXTRA_SAVANNA_TREES:
                    DefaultBiomeFeatures.addExtraSavannaTrees(TerrestriaBiome.biome);
                case MOUNTAIN_TREES:
                    DefaultBiomeFeatures.addMountainTrees(TerrestriaBiome.biome);
                case EXTRA_MOUNTAIN_TREES:
                    DefaultBiomeFeatures.addExtraMountainTrees(TerrestriaBiome.biome);
                case JUNGLE_TREES:
                    DefaultBiomeFeatures.addJungleTrees(TerrestriaBiome.biome);
                case JUNGLE_EDGE_TREES:
                    DefaultBiomeFeatures.addJungleEdgeTrees(TerrestriaBiome.biome);
                case BADLANDS_PLATEAU_TREES:
                    DefaultBiomeFeatures.addBadlandsPlateauTrees(TerrestriaBiome.biome);
                case SNOWY_SPRUCE_TREES:
                    DefaultBiomeFeatures.addSnowySpruceTrees(TerrestriaBiome.biome);
                case GIANT_SPRUCE_TAIGA_TREES:
                    DefaultBiomeFeatures.addGiantSpruceTaigaTrees(TerrestriaBiome.biome);
                case MEGA_SPRUCE_TAIGA_TREES:
                    DefaultBiomeFeatures.addGiantTreeTaigaTrees(TerrestriaBiome.biome);
                case JUNGLE_GRASS:
                    DefaultBiomeFeatures.addJungleGrass(TerrestriaBiome.biome);
                case SAVANNA_TALL_GRASS:
                    DefaultBiomeFeatures.addSavannaTallGrass(TerrestriaBiome.biome);
                case SHATTERED_SAVANNAH_TALL_GRASS:
                    DefaultBiomeFeatures.addShatteredSavannaGrass(TerrestriaBiome.biome);
                case SAVANNAH_GRASS:
                    DefaultBiomeFeatures.addSavannaGrass(TerrestriaBiome.biome);
                case BADLANDS_GRASS:
                    DefaultBiomeFeatures.addBadlandsGrass(TerrestriaBiome.biome);
                case FOREST_FLOWERS:
                    DefaultBiomeFeatures.addForestFlowers(TerrestriaBiome.biome);
                case FOREST_GRASS:
                    DefaultBiomeFeatures.addForestGrass(TerrestriaBiome.biome);
                case SWAMP_FEATURES:
                    DefaultBiomeFeatures.addSwampFeatures(TerrestriaBiome.biome);
                case MUSHROOM_FIELDS_FEATURES:
                    DefaultBiomeFeatures.addMushroomFieldsFeatures(TerrestriaBiome.biome);
                case PLAINS_FEATURES:
                    DefaultBiomeFeatures.addPlainsFeatures(TerrestriaBiome.biome);
                case DESERT_DEAD_BUSHES:
                    DefaultBiomeFeatures.addDesertDeadBushes(TerrestriaBiome.biome);
                case GIANT_TAIGA_GRASS:
                    DefaultBiomeFeatures.addGiantTaigaGrass(TerrestriaBiome.biome);
                case DEFAULT_FLOWERS:
                    DefaultBiomeFeatures.addDefaultFlowers(TerrestriaBiome.biome);
                case EXTRA_DEFAULT_FLOWERS:
                    DefaultBiomeFeatures.addExtraDefaultFlowers(TerrestriaBiome.biome);
                case DEFAULT_GRASS:
                    DefaultBiomeFeatures.addDefaultGrass(TerrestriaBiome.biome);
                case TAIGA_GRASS:
                    DefaultBiomeFeatures.addTaigaGrass(TerrestriaBiome.biome);
                case PLANS_TALL_GRASS:
                    DefaultBiomeFeatures.addPlainsTallGrass(TerrestriaBiome.biome);
                case DEFAULT_MUSHROOMS:
                    DefaultBiomeFeatures.addDefaultMushrooms(TerrestriaBiome.biome);
                case DEFAULT_VEGETATION:
                    DefaultBiomeFeatures.addDefaultVegetation(TerrestriaBiome.biome);
                case BADLANDS_VEGETATION:
                    DefaultBiomeFeatures.addBadlandsVegetation(TerrestriaBiome.biome);
                case JUNGLE_VEGETATION:
                    DefaultBiomeFeatures.addJungleVegetation(TerrestriaBiome.biome);
                case DESERT_VEGETATION:
                    DefaultBiomeFeatures.addDesertVegetation(TerrestriaBiome.biome);
                case SWAMP_VEGETATION:
                    DefaultBiomeFeatures.addSwampVegetation(TerrestriaBiome.biome);
                case DESSERT_FEATURES:
                    DefaultBiomeFeatures.addDesertFeatures(TerrestriaBiome.biome);
                case FOSSILS:
                    DefaultBiomeFeatures.addFossils(TerrestriaBiome.biome);
                case KELP:
                    DefaultBiomeFeatures.addKelp(TerrestriaBiome.biome);
                case SEAGRASS_ON_STONE:
                    DefaultBiomeFeatures.addSeagrassOnStone(TerrestriaBiome.biome);
                case SEAGRASS:
                    DefaultBiomeFeatures.addSeagrass(TerrestriaBiome.biome);
                case MORE_SEAGRASS:
                    DefaultBiomeFeatures.addMoreSeagrass(TerrestriaBiome.biome);
                case LESS_KELP:
                    DefaultBiomeFeatures.addLessKelp(TerrestriaBiome.biome);
                case SPRINGS:
                    DefaultBiomeFeatures.addSprings(TerrestriaBiome.biome);
                case ICEBERGS:
                    DefaultBiomeFeatures.addIcebergs(TerrestriaBiome.biome);
                case BLUE_ICE:
                    DefaultBiomeFeatures.addBlueIce(TerrestriaBiome.biome);
                case FROZEN_TOP_LAYER:
                    DefaultBiomeFeatures.addFrozenTopLayer(TerrestriaBiome.biome);
            }
        }
    }

    public enum DefaultFeature {
        LAND_CARVERS("land_carvers"),
        OCEAN_CARVERS("ocean_carvers"),
        STRUCTURES("structures"),
        LAKES("lakes"),
        DESERT_LAKES("desert_lakes"),
        DUNGEONS("dungeons"),
        MINEABLES("mineables"),
        ORES("ores"),
        EXTRA_GOLD("extra_gold"),
        EMERALD_ORE("emerald_ore"),
        INFECTED_STONE("infected_stone"),
        DISKS("disks"),
        CLAY("clay"),
        MOSSY_ROCKS("mossy_rocks"),
        LARGE_FERNS("large_ferns"),
        SWEET_BERRY_BUSHES("sweet_berry_bushes"),
        SWEET_BERRY_BUSHES_SNOWY("sweet_berry_bushes_snowy"),
        BAMBOO("bamboo"),
        BAMBOO_JUNGLE_TREES("bamboo jungle trees"),
        TAIGA_TREES("taiga_trees"),
        WATER_BIOME_OAK_TREES("water_biome_oak_trees"),
        BIRCH_TREES("birch_trees"),
        FOREST_TREES("forest_trees"),
        TALL_BIRCH_TREES("tall_birch_trees"),
        SAVANNAH_TREES("savannah_trees"),
        EXTRA_SAVANNA_TREES("extra_savannah_trees"),
        MOUNTAIN_TREES("mountain_trees"),
        EXTRA_MOUNTAIN_TREES("extra_mountain_trees"),
        JUNGLE_TREES("jungle_trees"),
        JUNGLE_EDGE_TREES("jungle_edge_trees"),
        BADLANDS_PLATEAU_TREES("badlands_plateau_trees"),
        SNOWY_SPRUCE_TREES("snowy_spruce_trees"),
        GIANT_SPRUCE_TAIGA_TREES("giant_spruce_taiga_trees"),
        MEGA_SPRUCE_TAIGA_TREES("mega_spruce_taiga_trees"),
        JUNGLE_GRASS("jungle_grass"),
        SAVANNA_TALL_GRASS("savanna_tall_grass"),
        SHATTERED_SAVANNAH_TALL_GRASS("shattered_savannah_tall_grass"),
        SAVANNAH_GRASS("savannah_grass"),
        BADLANDS_GRASS("badlands_grass"),
        FOREST_FLOWERS("forrest_flowers"),
        FOREST_GRASS("forrest_grass"),
        SWAMP_FEATURES("swamp_features"),
        MUSHROOM_FIELDS_FEATURES("mushroom_fields_features"),
        PLAINS_FEATURES("plains_features"),
        DESERT_DEAD_BUSHES("desert_dead_bushes"),
        GIANT_TAIGA_GRASS("giant_taiga_grass"),
        DEFAULT_FLOWERS("default_flowers"),
        EXTRA_DEFAULT_FLOWERS("extra_default_flowers"),
        DEFAULT_GRASS("default_grass"),
        TAIGA_GRASS("taiga_grass"),
        PLANS_TALL_GRASS("plains_tall_grass"),
        DEFAULT_MUSHROOMS("default_mushrooms"),
        DEFAULT_VEGETATION("default_vegetation"),
        BADLANDS_VEGETATION("badlands_vegetation"),
        JUNGLE_VEGETATION("jungle_vegetation"),
        DESERT_VEGETATION("desert_vegetation"),
        SWAMP_VEGETATION("swamp_vegetation"),
        DESSERT_FEATURES("desert_features"),
        FOSSILS("fossils"),
        KELP("kelp"),
        SEAGRASS_ON_STONE("segrass_on_stone"),
        SEAGRASS("seagrass"),
        MORE_SEAGRASS("more_seagrass"),
        LESS_KELP("less_kelp"),
        SPRINGS("springs"),
        ICEBERGS("icebergs"),
        BLUE_ICE("blue_ice"),
        FROZEN_TOP_LAYER("frozen_top_layer");

        private final String name;

        DefaultFeature(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
