package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.feature.FallenLogFeatureConfig;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.trees.decorator.SakuraLeafPileDecorator;
import com.terraformersmc.terrestria.feature.trees.decorator.FixSmallLogsDecorator;
import com.terraformersmc.terrestria.feature.trees.templates.CanopyTreeFeatureMega;
import com.terraformersmc.terrestria.feature.volcano.VolcanoGenerator;
import com.terraformersmc.terrestria.feature.volcano.VolcanoStructureFeature;
import com.terraformersmc.terrestria.feature.trees.*;
import com.terraformersmc.terraform.feature.CattailFeature;
import com.terraformersmc.terraform.feature.FallenLogFeature;
import com.terraformersmc.terrestria.feature.trees.RedwoodTreeFeatureMega;
import com.terraformersmc.terrestria.mixin.MixinTreeDecoratorType;
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

	public static CattailFeature CATTAIL;
	public static FallenLogFeature FALLEN_REDWOOD_LOG;
	public static FallenLogFeature FALLEN_HEMLOCK_LOG;

	public static VolcanoStructureFeature VOLCANO_STRUCTURE;
	public static StructurePieceType VOLCANO_PIECE;

	public static TreeDecoratorType<SakuraLeafPileDecorator> SAKURA_LEAF_PILE_DECORATOR;
	public static TreeDecoratorType<FixSmallLogsDecorator> FIX_SMALL_LOGS_DECORATOR;

	public static void init() {
		REDWOOD_TREE = register("redwood_tree", new RedwoodTreeFeature(BranchedTreeFeatureConfig::deserialize2));

		MEGA_REDWOOD_TREE = register("mega_redwood_tree",
				new RedwoodTreeFeatureMega(
						BranchedTreeFeatureConfig::deserialize2,
						TerrestriaBlocks.REDWOOD.getMegaDefinition(TerrestriaBlocks.REDWOOD_QUARTER_LOG)
				)
		);

		HEMLOCK_TREE = register("hemlock_tree", new HemlockTreeFeature(BranchedTreeFeatureConfig::deserialize2));

		MEGA_HEMLOCK_TREE = register("mega_hemlock_tree",
				new HemlockTreeFeatureMega(
						BranchedTreeFeatureConfig::deserialize2,
						TerrestriaBlocks.HEMLOCK.getMegaDefinition(TerrestriaBlocks.HEMLOCK_QUARTER_LOG)
				)
		);

		CYPRESS_TREE = register("cypress_tree", new CypressTreeFeature(BranchedTreeFeatureConfig::deserialize2));

		MEGA_CYPRESS_TREE = register("mega_cypress_tree",
				new CanopyTreeFeatureMega(
						BranchedTreeFeatureConfig::deserialize2,
						TerrestriaBlocks.CYPRESS.getMegaDefinition(TerrestriaBlocks.CYPRESS_QUARTER_LOG)
				)
		);

		WILLOW_TREE = register("willow_tree",
				new WillowTreeFeature(BranchedTreeFeatureConfig::deserialize2)
		);

		SAKURA_TREE = register("sakura_tree",
				new SakuraTreeFeature(BranchedTreeFeatureConfig::deserialize2)
		);

		JAPANESE_MAPLE_TREE = register("japanese_maple_tree",
				new JapaneseMapleTreeFeature(BranchedTreeFeatureConfig::deserialize2)
		);

		RAINBOW_EUCALYPTUS_TREE = register("rainbow_eucalyptus_tree",
				new CanopyTreeFeatureMega(BranchedTreeFeatureConfig::deserialize2, TerrestriaBlocks.RAINBOW_EUCALYPTUS.getMegaDefinition(
						TerrestriaBlocks.RAINBOW_EUCALYPTUS_QUARTER_LOG
				))
		);

		JUNGLE_PALM_TREE = register("jungle_palm_tree",
				new PalmTreeFeature(BranchedTreeFeatureConfig::deserialize2, Blocks.JUNGLE_WOOD.getDefaultState())
		);

		RUBBER_TREE = register("rubber_tree", new RubberTreeFeature(BranchedTreeFeatureConfig::deserialize2));

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

		Feature.STRUCTURES.put("Volcano", VOLCANO_STRUCTURE);

		VOLCANO_PIECE = Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, "volcano"), VolcanoGenerator::new);

		SAKURA_LEAF_PILE_DECORATOR = Registry.register(
				Registry.TREE_DECORATOR_TYPE, new Identifier(Terrestria.MOD_ID, "sakura_leaf_pile"),
				MixinTreeDecoratorType.create(SakuraLeafPileDecorator::new)
		);

		FIX_SMALL_LOGS_DECORATOR = Registry.register(
				Registry.TREE_DECORATOR_TYPE, new Identifier(Terrestria.MOD_ID, "fix_small_logs"),
				MixinTreeDecoratorType.create(FixSmallLogsDecorator::new)
		);
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
}
