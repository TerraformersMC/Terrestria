package com.terraformersmc.terrestria.init;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.placer.PlacerTypes;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.placer.trunk.BentTrunkPlacer;
import com.terraformersmc.terrestria.feature.placer.trunk.CanopyTree4BranchTrunkPlacer;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class TerrestriaTrunkPlacerTypes {

	public static TrunkPlacerType<BentTrunkPlacer> BENT;
	public static TrunkPlacerType<CanopyTree4BranchTrunkPlacer> CANOPY_4_BRANCHES;

	private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
		return PlacerTypes.registerTrunkPlacer(new Identifier(Terrestria.MOD_ID, name), codec);
	}

	public static void init() {
		BENT = register("bent_trunk_placer", BentTrunkPlacer.CODEC);
		CANOPY_4_BRANCHES = register("canopy_tree_4_branch_trunk_placer", CanopyTree4BranchTrunkPlacer.CODEC);
	}

}
