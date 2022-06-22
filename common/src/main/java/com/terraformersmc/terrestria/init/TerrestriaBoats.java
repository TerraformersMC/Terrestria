package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import com.terraformersmc.terrestria.Terrestria;

import net.minecraft.item.ItemGroup;
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


	public static void init() {
		REDWOOD_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "redwood_boat"), () -> REDWOOD_BOAT_TYPE, ItemGroup.TRANSPORTATION);
		HEMLOCK_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "hemlock_boat"), () -> HEMLOCK_BOAT_TYPE, ItemGroup.TRANSPORTATION);
		RUBBER_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "rubber_boat"), () -> RUBBER_BOAT_TYPE, ItemGroup.TRANSPORTATION);
		CYPRESS_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "cypress_boat"), () -> CYPRESS_BOAT_TYPE, ItemGroup.TRANSPORTATION);
		WILLOW_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "willow_boat"), () -> WILLOW_BOAT_TYPE, ItemGroup.TRANSPORTATION);
		JAPANESE_MAPLE_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "japanese_maple_boat"), () -> JAPANESE_MAPLE_BOAT_TYPE, ItemGroup.TRANSPORTATION);
		RAINBOW_EUCALYPTUS_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "rainbow_eucalyptus_boat"), () -> RAINBOW_EUCALYPTUS_BOAT_TYPE, ItemGroup.TRANSPORTATION);
		SAKURA_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "sakura_boat"), () -> SAKURA_BOAT_TYPE, ItemGroup.TRANSPORTATION);
		YUCCA_PALM_BOAT = (TerraformBoatItem) TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrestria.MOD_ID, "yucca_palm_boat"), () -> YUCCA_PALM_BOAT_TYPE, ItemGroup.TRANSPORTATION);

		REDWOOD_BOAT_TYPE = new TerraformBoatType.Builder().item(REDWOOD_BOAT).build();
		HEMLOCK_BOAT_TYPE = new TerraformBoatType.Builder().item(HEMLOCK_BOAT).build();
		RUBBER_BOAT_TYPE = new TerraformBoatType.Builder().item(RUBBER_BOAT).build();
		CYPRESS_BOAT_TYPE = new TerraformBoatType.Builder().item(CYPRESS_BOAT).build();
		WILLOW_BOAT_TYPE = new TerraformBoatType.Builder().item(WILLOW_BOAT).build();
		JAPANESE_MAPLE_BOAT_TYPE = new TerraformBoatType.Builder().item(JAPANESE_MAPLE_BOAT).build();
		RAINBOW_EUCALYPTUS_BOAT_TYPE = new TerraformBoatType.Builder().item(RAINBOW_EUCALYPTUS_BOAT).build();
		SAKURA_BOAT_TYPE = new TerraformBoatType.Builder().item(SAKURA_BOAT).build();
		YUCCA_PALM_BOAT_TYPE = new TerraformBoatType.Builder().item(YUCCA_PALM_BOAT).build();

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
