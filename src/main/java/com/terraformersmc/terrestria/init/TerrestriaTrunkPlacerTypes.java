package com.terraformersmc.terrestria.init;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.placer.PlacerTypes;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.placer.trunk.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class TerrestriaTrunkPlacerTypes {

	public static TrunkPlacerType<BentTrunkPlacer> BENT;
	public static TrunkPlacerType<CanopyTree4BranchTrunkPlacer> CANOPY_4_BRANCHES;
	public static TrunkPlacerType<QuarteredMegaIncrementedStraightTrunkPlacer> QUARTERED_MEGA_TREE;
	public static TrunkPlacerType<QuarteredMegaCanopyTrunkPlacer> QUARTERED_MEGA_CANOPY;
	public static TrunkPlacerType<IncrementedStraightTrunkPlacer> INCREMENTED_STRAIGHT;

	private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
		return PlacerTypes.registerTrunkPlacer(new Identifier(Terrestria.MOD_ID, name), codec);
	}

	public static void init() {
		BENT = register("bent_trunk_placer", BentTrunkPlacer.CODEC);
		CANOPY_4_BRANCHES = register("canopy_tree_4_branch_trunk_placer", CanopyTree4BranchTrunkPlacer.CODEC);
		QUARTERED_MEGA_TREE = register("quartered_mega_trunk_placer", QuarteredMegaIncrementedStraightTrunkPlacer.CODEC);
		QUARTERED_MEGA_CANOPY = register("quartered_mega_canopy_trunk_placer", QuarteredMegaCanopyTrunkPlacer.CODEC);
		INCREMENTED_STRAIGHT = register("incremented_straight_trunk_placer", IncrementedStraightTrunkPlacer.CODEC);
	}

}
