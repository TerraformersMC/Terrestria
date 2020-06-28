package com.terraformersmc.terrestria.init;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.placer.PlacerTypes;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.foliageplacers.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class TerrestriaFoliagePlacerTypes {

	public static FoliagePlacerType<CanopyFoliagePlacer> CANOPY;
	public static FoliagePlacerType<DotShrubPlacer> DOT_SHRUB;
	public static FoliagePlacerType<JapaneseCanopyFoliagePlacer> JAPANESE_CANOPY;
	public static FoliagePlacerType<SmallCanopyFoliagePlacer> SMALL_CANOPY;
	public static FoliagePlacerType<NoneFoliagePlacer> NONE;
	public static FoliagePlacerType<PalmFanFoliagePlacer> PALM_TOP;
	public static FoliagePlacerType<CypressFoliagePlacer> CYPRESS;
	public static FoliagePlacerType<SmallLogSphereFoliagePlacer> SMALL_LOG_SPHERE;
	public static FoliagePlacerType<SphereFoliagePlacer> SPHERE;

	public static void init() {
		CANOPY = register("canopy_foliage_placer", CanopyFoliagePlacer.CODEC);
		DOT_SHRUB = register("dot_shrub_foliage_placer", DotShrubPlacer.CODEC);
		JAPANESE_CANOPY = register("japanese_canopy_foliage_placer", JapaneseCanopyFoliagePlacer.CODEC);
		SMALL_CANOPY = register("small_canopy_foliage_placer", SmallCanopyFoliagePlacer.CODEC);
		NONE = register("none_foliage_placer", NoneFoliagePlacer.CODEC);
		PALM_TOP = register("palm_top_foliage_placer", PalmFanFoliagePlacer.CODEC);
		SMALL_LOG_SPHERE = register("small_log_sphere_foliage_placer", SmallLogSphereFoliagePlacer.CODEC);
		SPHERE = register("sphere_foliage_placer", SphereFoliagePlacer.CODEC);
		CYPRESS = register("cypress_foliage_placer", CypressFoliagePlacer.CODEC);
	}

	private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, Codec<P> codec) {
		return PlacerTypes.registerFoliagePlacer(new Identifier(Terrestria.MOD_ID, name), codec);
	}

}
