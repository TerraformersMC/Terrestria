package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.feature.FallenLogFeatureConfig;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.templates.CanopyTreeFeatureMega;
import com.terraformersmc.terrestria.feature.volcano.VolcanoGenerator;
import com.terraformersmc.terrestria.feature.volcano.VolcanoStructureFeature;
import com.terraformersmc.terrestria.feature.trees.*;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terraform.feature.CattailFeature;
import com.terraformersmc.terraform.feature.FallenLogFeature;
import com.terraformersmc.terrestria.feature.trees.RedwoodTreeFeatureMega;
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
	public static CanopyTreeFeatureMega MEGA_CYPRESS_TREE;
	public static WillowTreeFeature WILLOW_TREE;
	public static OakTreeFeature TALLER_BIRCH_TREE;
	public static SakuraTreeFeature SAKURA_TREE;
	public static JapaneseMapleTreeFeature JAPANESE_MAPLE_TREE;
	public static JungleGroundBushFeature JAPANESE_MAPLE_SHRUB;
	public static JapaneseMapleTreeFeature DARK_JAPANESE_MAPLE_TREE;
	public static CanopyTreeFeatureMega RAINBOW_EUCALYPTUS_TREE;
	public static PalmTreeFeature JUNGLE_PALM_TREE;
	public static RubberTreeFeature RUBBER_TREE;

	public static CattailFeature CATTAIL;
	public static FallenLogFeature FALLEN_REDWOOD_LOG;
	public static FallenLogFeature FALLEN_HEMLOCK_LOG;

	public static VolcanoStructureFeature VOLCANO_STRUCTURE;
	public static StructurePieceType VOLCANO_PIECE;

	public static void init() {
		REDWOOD_TREE = register("redwood_tree",
				new RedwoodTreeFeature(BranchedTreeFeatureConfig::deserialize2)
		);

		MEGA_REDWOOD_TREE = register("mega_redwood_tree",
				new RedwoodTreeFeatureMega(
						BranchedTreeFeatureConfig::deserialize2,
						TerrestriaBlocks.REDWOOD.getBasicDefinition().toMega(TerrestriaBlocks.REDWOOD_QUARTER_LOG.getDefaultState(), TerrestriaBlocks.REDWOOD.wood.getDefaultState())
				)
		);

		TINY_REDWOOD_TREE = register("tiny_redwood_tree",
				new RedwoodTreeFeatureTiny(BranchedTreeFeatureConfig::deserialize2)
		);

		HEMLOCK_TREE = register("hemlock_tree",
				new HemlockTreeFeature(BranchedTreeFeatureConfig::deserialize2)
		);

		MEGA_HEMLOCK_TREE = register("mega_hemlock_tree",
				new HemlockTreeFeatureMega(
						BranchedTreeFeatureConfig::deserialize2,
						TerrestriaBlocks.HEMLOCK.getBasicDefinition().toMega(TerrestriaBlocks.HEMLOCK_QUARTER_LOG.getDefaultState(), TerrestriaBlocks.HEMLOCK.wood.getDefaultState())
				)
		);

		TINY_HEMLOCK_TREE = register("tiny_hemlock_tree",
				new HemlockTreeFeatureTiny(BranchedTreeFeatureConfig::deserialize2)
		);

		CYPRESS_TREE = register("cypress_tree",
				new CypressTreeFeature(BranchedTreeFeatureConfig::deserialize2)
		);

		MEGA_CYPRESS_TREE = register("mega_cypress_tree",
				new CanopyTreeFeatureMega(
						BranchedTreeFeatureConfig::deserialize2,
						TerrestriaBlocks.CYPRESS.getBasicDefinition().toMega(TerrestriaBlocks.CYPRESS_QUARTER_LOG.getDefaultState(), TerrestriaBlocks.CYPRESS.wood.getDefaultState())
				)
		);

		WILLOW_TREE = register("willow_tree",
				new WillowTreeFeature(BranchedTreeFeatureConfig::deserialize2, TerrestriaBlocks.WILLOW.getBasicDefinition())
		);

		// TODO: Heightmaps
		SAKURA_TREE = register("sakura_tree",
				new SakuraTreeFeature(BranchedTreeFeatureConfig::deserialize2, false, TerrestriaBlocks.SAKURA.getBasicDefinition().toSakura(
						TerrestriaBlocks.SAKURA.log.getDefaultState().with(SmallLogBlock.HAS_LEAVES, true),
						TerrestriaBlocks.SAKURA_LEAF_PILE.getDefaultState()
				))
		);

		JAPANESE_MAPLE_TREE = register("japanese_maple_tree",
				new JapaneseMapleTreeFeature(BranchedTreeFeatureConfig::deserialize2, TerrestriaBlocks.JAPANESE_MAPLE.getBasicDefinition())
		);

		TreeDefinition.Basic shrubDefinition = new TreeDefinition.Basic(
				TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(),
				TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState()
		);

		// TODO: TreeDefinition
		JAPANESE_MAPLE_SHRUB = register("japanese_maple_shrub",
				new JungleGroundBushFeature(BranchedTreeFeatureConfig::deserialize2)
		);

		TreeDefinition.Basic darkJapaneseMapleTreeDefinition = new TreeDefinition.Basic(
				TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(),
				TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES.getDefaultState()
		);

		DARK_JAPANESE_MAPLE_TREE = register("dark_japanese_maple_tree",
				new JapaneseMapleTreeFeature(BranchedTreeFeatureConfig::deserialize2, darkJapaneseMapleTreeDefinition)
		);

		RAINBOW_EUCALYPTUS_TREE = register("rainbow_eucalyptus_tree",
				new CanopyTreeFeatureMega(BranchedTreeFeatureConfig::deserialize2, TerrestriaBlocks.RAINBOW_EUCALYPTUS.getBasicDefinition().toMega(
						TerrestriaBlocks.RAINBOW_EUCALYPTUS_QUARTER_LOG.getDefaultState(),
						TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood.getDefaultState()
				))
		);

		TreeDefinition.Basic junglePalm = new TreeDefinition.Basic(
				Blocks.JUNGLE_LOG.getDefaultState(),
				TerrestriaBlocks.JUNGLE_PALM_LEAVES.getDefaultState()
		);

		JUNGLE_PALM_TREE = register("jungle_palm_tree",
				new PalmTreeFeature(BranchedTreeFeatureConfig::deserialize2, junglePalm.withBark(Blocks.JUNGLE_WOOD.getDefaultState()))
		);

		RUBBER_TREE = register("rubber_tree",
				new RubberTreeFeature(BranchedTreeFeatureConfig::deserialize2, TerrestriaBlocks.RUBBER.getBasicDefinition())
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

		Feature.STRUCTURES.put("Volcano", VOLCANO_STRUCTURE);

		VOLCANO_PIECE = Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, "volcano"), VolcanoGenerator::new);
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
