package com.terraformersmc.terrestria.init;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terraform.tree.api.placer.PlacerTypes;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.foliageplacers.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class TerrestriaFoliagePlacerTypes {
	public static FoliagePlacerType<CanopyFoliagePlacer> CANOPY = register("canopy_foliage_placer", CanopyFoliagePlacer.CODEC);
	public static FoliagePlacerType<CypressFoliagePlacer> CYPRESS = register("cypress_foliage_placer", CypressFoliagePlacer.CODEC);
	public static FoliagePlacerType<DotShrubPlacer> DOT_SHRUB = register("dot_shrub_foliage_placer", DotShrubPlacer.CODEC);
	public static FoliagePlacerType<JapaneseCanopyFoliagePlacer> JAPANESE_CANOPY = register("japanese_canopy_foliage_placer", JapaneseCanopyFoliagePlacer.CODEC);
	public static FoliagePlacerType<NoneFoliagePlacer> NONE = register("none_foliage_placer", NoneFoliagePlacer.CODEC);
	public static FoliagePlacerType<PalmFanFoliagePlacer> PALM_TOP = register("palm_top_foliage_placer", PalmFanFoliagePlacer.CODEC);
	public static FoliagePlacerType<PredictiveSpruceFoliagePlacer> PREDICTIVE_SPRUCE = register("predictive_spruce", PredictiveSpruceFoliagePlacer.CODEC);
	public static FoliagePlacerType<SmallCanopyFoliagePlacer> SMALL_CANOPY = register("small_canopy_foliage_placer", SmallCanopyFoliagePlacer.CODEC);
	public static FoliagePlacerType<SmallLogSphereFoliagePlacer> SMALL_LOG_SPHERE = register("small_log_sphere_foliage_placer", SmallLogSphereFoliagePlacer.CODEC);
	public static FoliagePlacerType<SphereFoliagePlacer> SPHERE = register("sphere_foliage_placer", SphereFoliagePlacer.CODEC);

	private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, MapCodec<P> codec) {
		return PlacerTypes.registerFoliagePlacer(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, name), codec);
	}

	public static void init() { }
}
