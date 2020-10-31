package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.boat.TerraformBoat;
import com.terraformersmc.terraform.boat.TerraformBoatEntity;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerrestriaEntities {
	public static EntityType<TerraformBoatEntity> REDWOOD_BOAT;
	public static EntityType<TerraformBoatEntity> HEMLOCK_BOAT;
	public static EntityType<TerraformBoatEntity> RUBBER_BOAT;
	public static EntityType<TerraformBoatEntity> CYPRESS_BOAT;
	public static EntityType<TerraformBoatEntity> WILLOW_BOAT;
	public static EntityType<TerraformBoatEntity> JAPANESE_MAPLE_BOAT;
	public static EntityType<TerraformBoatEntity> RAINBOW_EUCALYPTUS_BOAT;
	public static EntityType<TerraformBoatEntity> SAKURA_BOAT;
	public static EntityType<TerraformBoatEntity> YUCCA_PALM_BOAT;

	public static void init() {
		REDWOOD_BOAT = registerBoat("redwood", TerrestriaItems.REDWOOD, BoatEntity.Type.DARK_OAK);
		HEMLOCK_BOAT = registerBoat("hemlock", TerrestriaItems.HEMLOCK, BoatEntity.Type.OAK);
		RUBBER_BOAT = registerBoat("rubber", TerrestriaItems.RUBBER, BoatEntity.Type.BIRCH);
		CYPRESS_BOAT = registerBoat("cypress", TerrestriaItems.CYPRESS, BoatEntity.Type.BIRCH);
		WILLOW_BOAT = registerBoat("willow", TerrestriaItems.WILLOW, BoatEntity.Type.SPRUCE);
		JAPANESE_MAPLE_BOAT = registerBoat("japanese_maple", TerrestriaItems.JAPANESE_MAPLE, BoatEntity.Type.ACACIA);
		RAINBOW_EUCALYPTUS_BOAT = registerBoat("rainbow_eucalyptus", TerrestriaItems.RAINBOW_EUCALYPTUS, BoatEntity.Type.JUNGLE);
		SAKURA_BOAT = registerBoat("sakura", TerrestriaItems.SAKURA, BoatEntity.Type.DARK_OAK);
		YUCCA_PALM_BOAT = registerBoat("yucca_palm", TerrestriaItems.YUCCA_PALM, BoatEntity.Type.BIRCH);
	}

	private static EntityType<TerraformBoatEntity> registerBoat(String name, WoodItems wood, BoatEntity.Type vanilla) {
		Identifier skin = new Identifier(Terrestria.MOD_ID, "textures/entity/boat/" + name + ".png");
		TerraformBoat boat = new TerraformBoat(wood.boat, wood.planks, skin, vanilla);

		EntityType<TerraformBoatEntity> type = FabricEntityTypeBuilder.<TerraformBoatEntity>create(
			SpawnGroup.MISC, (entity, world) -> new TerraformBoatEntity(entity, world, boat))
			.dimensions(EntityDimensions.fixed(1.375F, 0.5625F))
			.build();

		return Registry.register(Registry.ENTITY_TYPE, new Identifier(Terrestria.MOD_ID, name + "_boat"), type);
	}
}
