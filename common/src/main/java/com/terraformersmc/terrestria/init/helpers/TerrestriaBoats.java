package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import com.terraformersmc.terrestria.Terrestria;

import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class TerrestriaBoats {
	private static final HashMap<Identifier, TerraformBoatType> BOAT_TYPES = new HashMap<>(32);
	private static final HashMap<Identifier, TerraformBoatItem> BOAT_ITEMS = new HashMap<>(32);
	private static final HashMap<Identifier, TerraformBoatItem> CHEST_BOAT_ITEMS = new HashMap<>(32);

	public static TerraformBoatType register(String path, BlockItem planks) {
		Identifier typeId = Identifier.of(Terrestria.MOD_ID, path);
		Identifier boatId = Identifier.of(Terrestria.MOD_ID, path + "_boat");
		Identifier chestId = Identifier.of(Terrestria.MOD_ID, path + "_chest_boat");

		BOAT_ITEMS.put(boatId, (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(boatId, () -> BOAT_TYPES.get(typeId), false));
		CHEST_BOAT_ITEMS.put(chestId, (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(chestId, () -> BOAT_TYPES.get(typeId), true));

		BOAT_TYPES.put(typeId, new TerraformBoatType.Builder().item(BOAT_ITEMS.get(boatId)).chestItem(CHEST_BOAT_ITEMS.get(chestId)).planks(planks).build());
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, typeId, BOAT_TYPES.get(typeId));

		return BOAT_TYPES.get(typeId);
	}
}
