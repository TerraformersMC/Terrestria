package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.feature.CattailFeature;
import com.terraformersmc.terrestria.feature.misc.DumDumHeadFeature;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfiguration;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class TerrestriaFeatures {
	public static final CattailFeature CATTAIL = TerrestriaRegistry.register("cattail", new CattailFeature(ProbabilityFeatureConfiguration.CODEC, TerrestriaBlocks.CATTAIL, TerrestriaBlocks.TALL_CATTAIL));
	public static final Feature<NoneFeatureConfiguration> DUM_DUM_HEAD = TerrestriaRegistry.register("dum_dum_head", new DumDumHeadFeature(NoneFeatureConfiguration.CODEC));
	public static final TreeFeature QUARTERED_MEGA_TREE = TerrestriaRegistry.register("quartered_mega_tree", new TreeFeature(QuarteredMegaTreeConfiguration.getCodec()));

	public static void init() {}
}
