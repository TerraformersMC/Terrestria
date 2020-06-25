package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.feature.CattailFeature;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.arch.CanyonArchGenerator;
import com.terraformersmc.terrestria.feature.arch.CanyonArchStructureFeature;
import com.terraformersmc.terrestria.feature.volcano.VolcanoGenerator;
import com.terraformersmc.terrestria.feature.volcano.VolcanoStructureFeature;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;

// This class exports public feature constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaFeatures {

	public static CattailFeature CATTAIL;

	public static VolcanoStructureFeature VOLCANO_STRUCTURE;
	public static StructurePieceType VOLCANO_PIECE;

	//public static TreeDecoratorType<SakuraLeafPileDecorator> SAKURA_LEAF_PILE_DECORATOR;
	//public static TreeDecoratorType<FixSmallLogsDecorator> FIX_SMALL_LOGS_DECORATOR;

	public static CanyonArchStructureFeature CANYON_ARCH_STRUCTURE;
	public static StructurePieceType CANYON_ARCH_PIECE;

	public static void init() {

		CATTAIL = register("cattail", new CattailFeature(SeagrassFeatureConfig.CODEC, TerrestriaBlocks.CATTAIL, TerrestriaBlocks.TALL_CATTAIL));

		VOLCANO_STRUCTURE = Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(Terrestria.MOD_ID, "volcano"), new VolcanoStructureFeature(DefaultFeatureConfig.CODEC));

		StructureFeature.STRUCTURES.put("volcano", VOLCANO_STRUCTURE);

		VOLCANO_PIECE = Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, "volcano"), VolcanoGenerator::new);

		CANYON_ARCH_STRUCTURE = Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(Terrestria.MOD_ID, "canyon_arch"),
				new CanyonArchStructureFeature(DefaultFeatureConfig.CODEC)
		);

		StructureFeature.STRUCTURES.put("canyon_arch", CANYON_ARCH_STRUCTURE);

		CANYON_ARCH_PIECE = Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, "canyon_arch"), CanyonArchGenerator::new);
	}

	//TODO I have not tested this but it should work for registering structures
/*	public static <T extends StructureFeature<FC>, FC extends FeatureConfig> T registerStructure(String name, T feature) {
		T structure = Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(Terrestria.MOD_ID, name), feature);
		StructureFeature.STRUCTURES.put(name, feature);
		return structure;
	}*/

	public static <T extends Feature<FC>, FC extends FeatureConfig> T register(String name, T feature) {
		return Registry.register(Registry.FEATURE, new Identifier(Terrestria.MOD_ID, name), feature);
	}

	public static void addVolcanoStarts(Biome... biomes) {
		for (Biome biome : biomes) {
			biome.addStructureFeature(TerrestriaFeatures.VOLCANO_STRUCTURE.configure(FeatureConfig.DEFAULT));
		}
	}

	public static void addVolcanoStructure(Biome biome) {
		biome.addStructureFeature(TerrestriaFeatures.VOLCANO_STRUCTURE.configure(FeatureConfig.DEFAULT));
	}

	public static void addCanyonArchStructure(Biome biome) {
		biome.addStructureFeature(TerrestriaFeatures.CANYON_ARCH_STRUCTURE.configure(FeatureConfig.DEFAULT));
	}
}
