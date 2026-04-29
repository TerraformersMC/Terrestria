package com.terraformersmc.terrestria.init;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terraform.tree.api.placer.PlacerTypes;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class TerrestriaTrunkPlacerTypes {
	public static TrunkPlacerType<BentTrunkPlacer> BENT = register("bent_trunk_placer", BentTrunkPlacer.CODEC);
	public static TrunkPlacerType<CanopyTree4BranchTrunkPlacer> CANOPY_4_BRANCHES = register("canopy_tree_4_branch_trunk_placer", CanopyTree4BranchTrunkPlacer.CODEC);
	public static TrunkPlacerType<QuarteredMegaCanopyTrunkPlacer> QUARTERED_MEGA_CANOPY = register("quartered_mega_canopy_trunk_placer", QuarteredMegaCanopyTrunkPlacer.CODEC);
	public static TrunkPlacerType<MegaTrunkPlacer> MEGA = register("mega_trunk_placer", MegaTrunkPlacer.CODEC);
	public static TrunkPlacerType<RubberTreeTrunkPlacer> RUBBER_TREE = register("rubber_tree_trunk_placer", RubberTreeTrunkPlacer.CODEC);
	public static TrunkPlacerType<SaguaroCactusTrunkPlacer> SAGUARO_CACTUS = register("saguaro_cactus_trunk_placer", SaguaroCactusTrunkPlacer.CODEC);
	public static TrunkPlacerType<SmallCanopyTree4BranchTrunkPlacer> SMALL_CANOPY_4_BRANCHES = register("small_canopy_tree_4_branch_trunk_placer", SmallCanopyTree4BranchTrunkPlacer.CODEC);
	public static TrunkPlacerType<SpindlyTrunkPlacer> SPINDLY = register("spindly_trunk_placer", SpindlyTrunkPlacer.CODEC);
	public static TrunkPlacerType<SmallBranchingTrunkPlacer> SMALL_BRANCHING = register("small_branching_trunk_placer", SmallBranchingTrunkPlacer.CODEC);
	public static TrunkPlacerType<FallenStraightTrunkPlacer> FALLEN_STRAIGHT = register("fallen_straight_trunk_placer", FallenStraightTrunkPlacer.CODEC);

	private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, MapCodec<P> codec) {
		return PlacerTypes.registerTrunkPlacer(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, name), codec);
	}

	public static void init() { }
}
