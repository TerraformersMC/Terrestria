package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.feature.FallenLogFeatureConfig;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.trees.decorator.SakuraLeafPileDecorator;
import com.terraformersmc.terrestria.feature.trees.decorator.FixSmallLogsDecorator;
import com.terraformersmc.terrestria.feature.trees.templates.CanopyTreeFeatureMega;
import com.terraformersmc.terrestria.feature.arch.CanyonArchGenerator;
import com.terraformersmc.terrestria.feature.arch.CanyonArchStructureFeature;
import com.terraformersmc.terrestria.feature.trees.*;
import com.terraformersmc.terrestria.feature.volcano.VolcanoGenerator;
import com.terraformersmc.terrestria.feature.volcano.VolcanoStructureFeature;
import com.terraformersmc.terraform.feature.CattailFeature;
import com.terraformersmc.terraform.feature.FallenLogFeature;
import com.terraformersmc.terrestria.feature.trees.RedwoodTreeFeatureMega;

import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.TreeDecoratorType;
import net.minecraft.world.gen.feature.*;

// This class exports public feature constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaFeatures {
	public static BryceTreeFeature BRYCE_TREE;
	public static RedwoodTreeFeature REDWOOD_TREE;
	public static RedwoodTreeFeatureMega MEGA_REDWOOD_TREE;
	public static HemlockTreeFeature HEMLOCK_TREE;
	public static HemlockTreeFeatureMega MEGA_HEMLOCK_TREE;
	public static CypressTreeFeature CYPRESS_TREE;
	public static CanopyTreeFeatureMega MEGA_CYPRESS_TREE;
	public static WillowTreeFeature WILLOW_TREE;
	public static SakuraTreeFeature SAKURA_TREE;
	public static JapaneseMapleTreeFeature JAPANESE_MAPLE_TREE;
	public static CanopyTreeFeatureMega RAINBOW_EUCALYPTUS_TREE;
	public static PalmTreeFeature JUNGLE_PALM_TREE;
	public static RubberTreeFeature RUBBER_TREE;
	public static YuccaPalmTreeFeature YUCCA_PALM_TREE;

	public static ShrubFeature SHRUB;

	public static SaguaroCactusFeature SAGUARO_CACTUS;

	public static CattailFeature CATTAIL;
	public static FallenLogFeature FALLEN_REDWOOD_LOG;
	public static FallenLogFeature FALLEN_HEMLOCK_LOG;

	public static VolcanoStructureFeature VOLCANO_STRUCTURE;
	public static StructurePieceType VOLCANO_PIECE;

	public static TreeDecoratorType<SakuraLeafPileDecorator> SAKURA_LEAF_PILE_DECORATOR;
	public static TreeDecoratorType<FixSmallLogsDecorator> FIX_SMALL_LOGS_DECORATOR;

	public static CanyonArchStructureFeature CANYON_ARCH_STRUCTURE;
	public static StructurePieceType CANYON_ARCH_PIECE;

	public static void init() {
		REDWOOD_TREE = register("redwood_tree", new RedwoodTreeFeature(BranchedTreeFeatureConfig::deserialize));

		MEGA_REDWOOD_TREE = register("mega_redwood_tree",
				new RedwoodTreeFeatureMega(
						MegaTreeFeatureConfig::deserialize,
						TerrestriaBlocks.REDWOOD.getMegaDefinition(TerrestriaBlocks.REDWOOD_QUARTER_LOG)
				)
		);

		HEMLOCK_TREE = register("hemlock_tree", new HemlockTreeFeature(BranchedTreeFeatureConfig::deserialize));

		MEGA_HEMLOCK_TREE = register("mega_hemlock_tree",
				new HemlockTreeFeatureMega(
						MegaTreeFeatureConfig::deserialize,
						TerrestriaBlocks.HEMLOCK.getMegaDefinition(TerrestriaBlocks.HEMLOCK_QUARTER_LOG)
				)
		);

		CYPRESS_TREE = register("cypress_tree", new CypressTreeFeature(BranchedTreeFeatureConfig::deserialize));

		MEGA_CYPRESS_TREE = register("mega_cypress_tree",
				new CanopyTreeFeatureMega(
						MegaTreeFeatureConfig::deserialize,
						TerrestriaBlocks.CYPRESS.getMegaDefinition(TerrestriaBlocks.CYPRESS_QUARTER_LOG)
				)
		);

		WILLOW_TREE = register("willow_tree",
				new WillowTreeFeature(BranchedTreeFeatureConfig::deserialize)
		);

		SAKURA_TREE = register("sakura_tree",
				new SakuraTreeFeature(BranchedTreeFeatureConfig::deserialize)
		);

		JAPANESE_MAPLE_TREE = register("japanese_maple_tree",
				new JapaneseMapleTreeFeature(BranchedTreeFeatureConfig::deserialize)
		);

		RAINBOW_EUCALYPTUS_TREE = register("rainbow_eucalyptus_tree",
				new CanopyTreeFeatureMega(MegaTreeFeatureConfig::deserialize, TerrestriaBlocks.RAINBOW_EUCALYPTUS.getMegaDefinition(
						TerrestriaBlocks.RAINBOW_EUCALYPTUS_QUARTER_LOG
				))
		);

		JUNGLE_PALM_TREE = register("jungle_palm_tree",
				new PalmTreeFeature(BranchedTreeFeatureConfig::deserialize, Blocks.JUNGLE_WOOD.getDefaultState())
		);

		RUBBER_TREE = register("rubber_tree", new RubberTreeFeature(BranchedTreeFeatureConfig::deserialize));

		SAGUARO_CACTUS = register("saguaro_cactus",
				new SaguaroCactusFeature(BranchedTreeFeatureConfig::deserialize, false, TerrestriaBlocks.SAGUARO_CACTUS.getDefaultState(), null)
		);

		YUCCA_PALM_TREE = register("yucca_palm",
			new YuccaPalmTreeFeature(BranchedTreeFeatureConfig::deserialize, false, TerrestriaBlocks.YUCCA_PALM.log.getDefaultState(), TerrestriaBlocks.YUCCA_PALM.leaves.getDefaultState())
		);

		BRYCE_TREE = register("bryce",
			new BryceTreeFeature(BranchedTreeFeatureConfig::deserialize, false, TerrestriaBlocks.SMALL_OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState())
		);

		CATTAIL = register("cattail",
				new CattailFeature(SeagrassFeatureConfig::deserialize, TerrestriaBlocks.CATTAIL, TerrestriaBlocks.TALL_CATTAIL)
		);

		FALLEN_REDWOOD_LOG = register("fallen_redwood_log",
				new FallenLogFeature(FallenLogFeatureConfig::deserialize)
		);

		FALLEN_HEMLOCK_LOG = register("fallen_hemlock_log",
				new FallenLogFeature(FallenLogFeatureConfig::deserialize)
		);

		VOLCANO_STRUCTURE = Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(Terrestria.MOD_ID, "volcano"),
				new VolcanoStructureFeature(DefaultFeatureConfig::deserialize)
		);

		Feature.STRUCTURES.put("volcano", VOLCANO_STRUCTURE);

		VOLCANO_PIECE = Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, "volcano"), VolcanoGenerator::new);

		// TODO
		/*SAKURA_LEAF_PILE_DECORATOR = Registry.register(
				Registry.TREE_DECORATOR_TYPE, new Identifier(Terrestria.MOD_ID, "sakura_leaf_pile"),
				MixinTreeDecoratorType.create(SakuraLeafPileDecorator::new)
		);

		FIX_SMALL_LOGS_DECORATOR = Registry.register(
				Registry.TREE_DECORATOR_TYPE, new Identifier(Terrestria.MOD_ID, "fix_small_logs"),
				MixinTreeDecoratorType.create(FixSmallLogsDecorator::new)
		);*/

		CANYON_ARCH_STRUCTURE = Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(Terrestria.MOD_ID, "canyon_arch"),
				new CanyonArchStructureFeature(DefaultFeatureConfig::deserialize)
		);

		Feature.STRUCTURES.put("canyon_arch", CANYON_ARCH_STRUCTURE);

		CANYON_ARCH_PIECE = Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, "canyon_arch"), CanyonArchGenerator::new);

		SHRUB = register("shrub", new ShrubFeature(ShrubFeatureConfig::deserialize));
	}

	public static <T extends Feature<FC>, FC extends FeatureConfig> T register(String name, T feature) {
		return Registry.register(Registry.FEATURE, new Identifier(Terrestria.MOD_ID, name), feature);
	}

	public static void addVolcanoStarts(Biome... biomes) {
		for (Biome biome : biomes) {
			biome.addStructureFeature(TerrestriaFeatures.VOLCANO_STRUCTURE.configure(FeatureConfig.DEFAULT));
		}
	}

	public static void addVolcanoStructure(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, TerrestriaFeatures.VOLCANO_STRUCTURE.configure(FeatureConfig.DEFAULT));
	}

	public static void addCanyonArchStructure(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, TerrestriaFeatures.CANYON_ARCH_STRUCTURE.configure(FeatureConfig.DEFAULT));
	}
}
