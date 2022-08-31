package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import com.terraformersmc.terrestria.Terrestria;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerrestriaBoats {
	public static TerraformBoatType REDWOOD_BOAT_TYPE;
	public static TerraformBoatType HEMLOCK_BOAT_TYPE;
	public static TerraformBoatType RUBBER_BOAT_TYPE;
	public static TerraformBoatType CYPRESS_BOAT_TYPE;
	public static TerraformBoatType WILLOW_BOAT_TYPE;
	public static TerraformBoatType JAPANESE_MAPLE_BOAT_TYPE;
	public static TerraformBoatType RAINBOW_EUCALYPTUS_BOAT_TYPE;
	public static TerraformBoatType SAKURA_BOAT_TYPE;
	public static TerraformBoatType YUCCA_PALM_BOAT_TYPE;

	public static TerraformBoatItem REDWOOD_BOAT;
	public static TerraformBoatItem HEMLOCK_BOAT;
	public static TerraformBoatItem RUBBER_BOAT;
	public static TerraformBoatItem CYPRESS_BOAT;
	public static TerraformBoatItem WILLOW_BOAT;
	public static TerraformBoatItem JAPANESE_MAPLE_BOAT;
	public static TerraformBoatItem RAINBOW_EUCALYPTUS_BOAT;
	public static TerraformBoatItem SAKURA_BOAT;
	public static TerraformBoatItem YUCCA_PALM_BOAT;

	public static TerraformBoatItem REDWOOD_CHEST_BOAT;
	public static TerraformBoatItem HEMLOCK_CHEST_BOAT;
	public static TerraformBoatItem RUBBER_CHEST_BOAT;
	public static TerraformBoatItem CYPRESS_CHEST_BOAT;
	public static TerraformBoatItem WILLOW_CHEST_BOAT;
	public static TerraformBoatItem JAPANESE_MAPLE_CHEST_BOAT;
	public static TerraformBoatItem RAINBOW_EUCALYPTUS_CHEST_BOAT;
	public static TerraformBoatItem SAKURA_CHEST_BOAT;
	public static TerraformBoatItem YUCCA_PALM_CHEST_BOAT;


	public static void init() {
		REDWOOD_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "redwood_boat"), () -> REDWOOD_BOAT_TYPE, false);
		HEMLOCK_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "hemlock_boat"), () -> HEMLOCK_BOAT_TYPE, false);
		RUBBER_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "rubber_boat"), () -> RUBBER_BOAT_TYPE, false);
		CYPRESS_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "cypress_boat"), () -> CYPRESS_BOAT_TYPE, false);
		WILLOW_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "willow_boat"), () -> WILLOW_BOAT_TYPE, false);
		JAPANESE_MAPLE_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "japanese_maple_boat"), () -> JAPANESE_MAPLE_BOAT_TYPE, false);
		RAINBOW_EUCALYPTUS_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "rainbow_eucalyptus_boat"), () -> RAINBOW_EUCALYPTUS_BOAT_TYPE, false);
		SAKURA_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "sakura_boat"), () -> SAKURA_BOAT_TYPE, false);
		YUCCA_PALM_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "yucca_palm_boat"), () -> YUCCA_PALM_BOAT_TYPE, false);

		REDWOOD_CHEST_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "redwood_chest_boat"), () -> REDWOOD_BOAT_TYPE, true);
		HEMLOCK_CHEST_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "hemlock_chest_boat"), () -> HEMLOCK_BOAT_TYPE, true);
		RUBBER_CHEST_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "rubber_chest_boat"), () -> RUBBER_BOAT_TYPE, true);
		CYPRESS_CHEST_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "cypress_chest_boat"), () -> CYPRESS_BOAT_TYPE, true);
		WILLOW_CHEST_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "willow_chest_boat"), () -> WILLOW_BOAT_TYPE, true);
		JAPANESE_MAPLE_CHEST_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "japanese_maple_chest_boat"), () -> JAPANESE_MAPLE_BOAT_TYPE, true);
		RAINBOW_EUCALYPTUS_CHEST_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "rainbow_eucalyptus_chest_boat"), () -> RAINBOW_EUCALYPTUS_BOAT_TYPE, true);
		SAKURA_CHEST_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "sakura_chest_boat"), () -> SAKURA_BOAT_TYPE, true);
		YUCCA_PALM_CHEST_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "yucca_palm_chest_boat"), () -> YUCCA_PALM_BOAT_TYPE, true);

		REDWOOD_BOAT_TYPE = new TerraformBoatType.Builder().item(REDWOOD_BOAT).chestItem(REDWOOD_CHEST_BOAT).build();
		HEMLOCK_BOAT_TYPE = new TerraformBoatType.Builder().item(HEMLOCK_BOAT).chestItem(HEMLOCK_CHEST_BOAT).build();
		RUBBER_BOAT_TYPE = new TerraformBoatType.Builder().item(RUBBER_BOAT).chestItem(RUBBER_CHEST_BOAT).build();
		CYPRESS_BOAT_TYPE = new TerraformBoatType.Builder().item(CYPRESS_BOAT).chestItem(CYPRESS_CHEST_BOAT).build();
		WILLOW_BOAT_TYPE = new TerraformBoatType.Builder().item(WILLOW_BOAT).chestItem(WILLOW_CHEST_BOAT).build();
		JAPANESE_MAPLE_BOAT_TYPE = new TerraformBoatType.Builder().item(JAPANESE_MAPLE_BOAT).chestItem(JAPANESE_MAPLE_CHEST_BOAT).build();
		RAINBOW_EUCALYPTUS_BOAT_TYPE = new TerraformBoatType.Builder().item(RAINBOW_EUCALYPTUS_BOAT).chestItem(RAINBOW_EUCALYPTUS_CHEST_BOAT).build();
		SAKURA_BOAT_TYPE = new TerraformBoatType.Builder().item(SAKURA_BOAT).chestItem(SAKURA_CHEST_BOAT).build();
		YUCCA_PALM_BOAT_TYPE = new TerraformBoatType.Builder().item(YUCCA_PALM_BOAT).chestItem(YUCCA_PALM_CHEST_BOAT).build();

		Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Terrestria.MOD_ID, "redwood"), REDWOOD_BOAT_TYPE);
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Terrestria.MOD_ID, "hemlock"), HEMLOCK_BOAT_TYPE);
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Terrestria.MOD_ID, "rubber"), RUBBER_BOAT_TYPE);
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Terrestria.MOD_ID, "cypress"), CYPRESS_BOAT_TYPE);
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Terrestria.MOD_ID, "willow"), WILLOW_BOAT_TYPE);
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Terrestria.MOD_ID, "japanese_maple"), JAPANESE_MAPLE_BOAT_TYPE);
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Terrestria.MOD_ID, "rainbow_eucalyptus"), RAINBOW_EUCALYPTUS_BOAT_TYPE);
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Terrestria.MOD_ID, "sakura"), SAKURA_BOAT_TYPE);
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Terrestria.MOD_ID, "yucca_palm"), YUCCA_PALM_BOAT_TYPE);
	}
}
