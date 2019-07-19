package net.coderbot.terrestria.init;

import io.github.terraformersmc.terraform.block.SmallLogBlock;
import io.github.terraformersmc.terraform.feature.CattailFeature;
import io.github.terraformersmc.terraform.feature.FallenLogFeature;
import net.coderbot.terrestria.Terrestria;
import net.coderbot.terrestria.feature.TreeDefinition;
import net.coderbot.terrestria.feature.trees.*;
import net.coderbot.terrestria.feature.trees.RedwoodTreeFeatureMega;
import net.coderbot.terrestria.feature.volcano.VolcanoGenerator;
import net.coderbot.terrestria.feature.volcano.VolcanoStructureFeature;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.*;

// This class exports public feature constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaFeatures {
	public static RedwoodTreeFeature REDWOOD_TREE;
	public static RedwoodTreeFeatureMega MEGA_REDWOOD_TREE;
	public static RedwoodTreeFeatureTiny TINY_REDWOOD_TREE;
	public static HemlockTreeFeature HEMLOCK_TREE;
	public static HemlockTreeFeatureMega MEGA_HEMLOCK_TREE;
	public static HemlockTreeFeatureTiny TINY_HEMLOCK_TREE;
	public static CypressTreeFeature CYPRESS_TREE;
	public static CypressTreeFeatureMega MEGA_CYPRESS_TREE;
	public static WillowTreeFeature WILLOW_TREE;
	public static OakTreeFeature TALLER_BIRCH_TREE;
	public static SakuraTreeFeature SAKURA_TREE;
	public static JapaneseMapleTreeFeature JAPANESE_MAPLE_TREE;
	public static JapaneseMapleShrubFeature JAPANESE_MAPLE_SHRUB;
	public static JapaneseMapleTreeFeature DARK_JAPANESE_MAPLE_TREE;
	public static RainbowEucalyptusTreeFeature RAINBOW_EUCALYPTUS_TREE;
	public static JungleTreeFeature SMALL_RAINBOW_EUCALYPTUS_TREE;
	public static PalmTreeFeature PALM_TREE;
	public static RubberTreeFeature RUBBER_TREE;

	public static CattailFeature CATTAIL;
	public static FallenLogFeature FALLEN_REDWOOD_LOG;
	public static FallenLogFeature FALLEN_HEMLOCK_LOG;

	public static VolcanoStructureFeature VOLCANO_STRUCTURE;
	public static StructurePieceType VOLCANO_PIECE;

	public static void init() {
		REDWOOD_TREE = register("redwood_tree",
				new RedwoodTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.REDWOOD.getBasicDefinition())
		);

		MEGA_REDWOOD_TREE = register("mega_redwood_tree",
				new RedwoodTreeFeatureMega(
						DefaultFeatureConfig::deserialize,
						false,
						TerrestriaBlocks.REDWOOD.getBasicDefinition().toMega(TerrestriaBlocks.REDWOOD_QUARTER_LOG.getDefaultState(), TerrestriaBlocks.REDWOOD.wood.getDefaultState())
				)
		);

		TINY_REDWOOD_TREE = register("tiny_redwood_tree",
				new RedwoodTreeFeatureTiny(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.REDWOOD.getBasicDefinition())
		);

		HEMLOCK_TREE = register("hemlock_tree",
				new HemlockTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.HEMLOCK.getBasicDefinition())
		);

		MEGA_HEMLOCK_TREE = register("mega_hemlock_tree",
				new HemlockTreeFeatureMega(
						DefaultFeatureConfig::deserialize, false,
						TerrestriaBlocks.HEMLOCK.getBasicDefinition().toMega(TerrestriaBlocks.HEMLOCK_QUARTER_LOG.getDefaultState(), TerrestriaBlocks.HEMLOCK.wood.getDefaultState())
				)
		);

		TINY_HEMLOCK_TREE = register("tiny_hemlock_tree",
				new HemlockTreeFeatureTiny(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.HEMLOCK.getBasicDefinition())
		);

		CYPRESS_TREE = register("cypress_tree",
				new CypressTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.CYPRESS.getBasicDefinition())
		);

		MEGA_CYPRESS_TREE = register("mega_cypress_tree",
				new CypressTreeFeatureMega(
						DefaultFeatureConfig::deserialize, false,
						TerrestriaBlocks.CYPRESS.getBasicDefinition().toMega(TerrestriaBlocks.CYPRESS_QUARTER_LOG.getDefaultState(), TerrestriaBlocks.CYPRESS.wood.getDefaultState())
				)
		);

		WILLOW_TREE = register("willow_tree",
				new WillowTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.WILLOW.getBasicDefinition())
		);

		TALLER_BIRCH_TREE = register("taller_birch_tree",
				new OakTreeFeature(DefaultFeatureConfig::deserialize, false, 8, Blocks.BIRCH_LOG.getDefaultState(), Blocks.BIRCH_LEAVES.getDefaultState(), false)
		);

		SAKURA_TREE = register("sakura_tree",
				new SakuraTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.SAKURA.getBasicDefinition().toSakura(
						TerrestriaBlocks.SAKURA.log.getDefaultState().with(SmallLogBlock.HAS_LEAVES, true),
						TerrestriaBlocks.SAKURA_LEAF_PILE.getDefaultState()
				))
		);

		JAPANESE_MAPLE_TREE = register("japanese_maple_tree",
				new JapaneseMapleTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.JAPANESE_MAPLE.getBasicDefinition())
		);

		TreeDefinition.Basic shrubDefinition = new TreeDefinition.Basic(
				TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(),
				TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState()
		);

		JAPANESE_MAPLE_SHRUB = register("japanese_maple_shrub",
				new JapaneseMapleShrubFeature(DefaultFeatureConfig::deserialize, false, shrubDefinition)
		);

		TreeDefinition.Basic darkJapaneseMapleTreeDefinition = new TreeDefinition.Basic(
				TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(),
				TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES.getDefaultState()
		);

		DARK_JAPANESE_MAPLE_TREE = register("dark_japanese_maple_tree",
				new JapaneseMapleTreeFeature(DefaultFeatureConfig::deserialize, false, darkJapaneseMapleTreeDefinition)
		);

		RAINBOW_EUCALYPTUS_TREE = register("rainbow_eucalyptus_tree",
				new RainbowEucalyptusTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.RAINBOW_EUCALYPTUS.getBasicDefinition().toMega(
						TerrestriaBlocks.RAINBOW_EUCALYPTUS_QUARTER_LOG.getDefaultState(),
						TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood.getDefaultState()
				))
		);

		SMALL_RAINBOW_EUCALYPTUS_TREE = register("small_rainbow_eucalyptus_tree",
				new JungleTreeFeature(DefaultFeatureConfig::deserialize, false, 5,
						TerrestriaBlocks.RAINBOW_EUCALYPTUS.log.getDefaultState(),
						TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves.getDefaultState(),
						true
				)
		);

		// TODO: palm wood
		TreeDefinition.Basic palmDefinition = new TreeDefinition.Basic(
				Blocks.JUNGLE_LOG.getDefaultState(),
				TerrestriaBlocks.PALM.leaves.getDefaultState()
		);

		PALM_TREE = register("palm_tree",
				new PalmTreeFeature(DefaultFeatureConfig::deserialize, false, palmDefinition.withBark(Blocks.JUNGLE_WOOD.getDefaultState()))
		);

		RUBBER_TREE = register("rubber_tree",
				new RubberTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.RUBBER.getBasicDefinition())
		);

		CATTAIL = register("cattail",
				new CattailFeature(SeagrassFeatureConfig::deserialize, TerrestriaBlocks.CATTAIL, TerrestriaBlocks.TALL_CATTAIL)
		);

		FALLEN_REDWOOD_LOG = register("fallen_redwood_log",
				new FallenLogFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.REDWOOD.log.getDefaultState())
		);

		FALLEN_HEMLOCK_LOG = register("fallen_hemlock_log",
				new FallenLogFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.HEMLOCK.log.getDefaultState())
		);

		VOLCANO_STRUCTURE = Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(Terrestria.MOD_ID, "volcano"),
				new VolcanoStructureFeature(DefaultFeatureConfig::deserialize)
		);

		Feature.STRUCTURES.put("Volcano", VOLCANO_STRUCTURE);

		VOLCANO_PIECE = Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, "volcano"), VolcanoGenerator::new);
	}

	public static <T extends Feature<FC>, FC extends FeatureConfig> T register(String name, T feature) {
		return Registry.register(Registry.FEATURE, new Identifier(Terrestria.MOD_ID, name), feature);
	}

	public static void addVolcanoStarts(Biome... biomes) {
		for (Biome biome : biomes) {
			biome.addStructureFeature(TerrestriaFeatures.VOLCANO_STRUCTURE, new DefaultFeatureConfig());
		}
	}

	public static void addVolcanoStructure(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, Biome.configureFeature(TerrestriaFeatures.VOLCANO_STRUCTURE, FeatureConfig.DEFAULT, Decorator.NOPE, DecoratorConfig.DEFAULT));
	}
}
