package com.terraformersmc.terrestria.init;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.placer.PlacerTypes;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class TerrestriaTrunkPlacerTypes {

	public static TrunkPlacerType<BentTrunkPlacer> BENT;
	public static TrunkPlacerType<CanopyTree4BranchTrunkPlacer> CANOPY_4_BRANCHES;
	public static TrunkPlacerType<QuarteredMegaIncrementedStraightTrunkPlacer> QUARTERED_MEGA_TREE;
	public static TrunkPlacerType<QuarteredMegaCanopyTrunkPlacer> QUARTERED_MEGA_CANOPY;
	public static TrunkPlacerType<IncrementedStraightTrunkPlacer> INCREMENTED_STRAIGHT;
	public static TrunkPlacerType<SmallCanopyTree4BranchTrunkPlacer> SMALL_CANOPY_4_BRANCHES;
	public static TrunkPlacerType<SpindlyTrunkPlacer> SPINDLY;
	public static TrunkPlacerType<SmallBranchingTrunkPlacer> SMALL_BRANCHING;
	public static TrunkPlacerType<RubberTreeTrunkPlacer> RUBBER_TREE;

	public static void init() {
		BENT = register("bent_trunk_placer", BentTrunkPlacer.CODEC);
		CANOPY_4_BRANCHES = register("canopy_tree_4_branch_trunk_placer", CanopyTree4BranchTrunkPlacer.CODEC);
		QUARTERED_MEGA_TREE = register("quartered_mega_trunk_placer", QuarteredMegaIncrementedStraightTrunkPlacer.CODEC);
		QUARTERED_MEGA_CANOPY = register("quartered_mega_canopy_trunk_placer", QuarteredMegaCanopyTrunkPlacer.CODEC);
		INCREMENTED_STRAIGHT = register("incremented_straight_trunk_placer", IncrementedStraightTrunkPlacer.CODEC);
		RUBBER_TREE = register("rubber_tree_trunk_placer", RubberTreeTrunkPlacer.CODEC);
		SMALL_CANOPY_4_BRANCHES = register("small_canopy_tree_4_branch_trunk_placer", SmallCanopyTree4BranchTrunkPlacer.CODEC);
		SPINDLY = register("spindly_trunk_placer", SpindlyTrunkPlacer.CODEC);
		SMALL_BRANCHING = register("small_branching_trunk_placer", SmallBranchingTrunkPlacer.CODEC);
	}

	private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
		return PlacerTypes.registerTrunkPlacer(new Identifier(Terrestria.MOD_ID, name), codec);
	}

}
