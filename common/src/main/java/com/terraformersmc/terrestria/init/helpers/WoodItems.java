package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;

public class WoodItems {
	public BlockItem log;
	public BlockItem wood;
	public BlockItem leaves;
	public BlockItem planks;
	public BlockItem slab;
	public BlockItem stairs;
	public BlockItem fence;
	public BlockItem fenceGate;
	public BlockItem door;
	public BlockItem button;
	public BlockItem pressurePlate;
	public SignItem sign;
	public HangingSignItem hangingSign;
	public BlockItem trapdoor;
	public BlockItem strippedLog;
	public BlockItem strippedWood;
	public TerraformBoatItem boat;
	public TerraformBoatItem chestBoat;

	public WoodItems() {}

	public static WoodItems register(String name, WoodBlocks blocks) {
		TerraformBoatType boatType;
		WoodItems items = new WoodItems();

		items.log = TerrestriaRegistry.registerBlockItem(name + "_log", blocks.log);
		items.leaves = TerrestriaRegistry.registerBlockItem(name + "_leaves", blocks.leaves);
		items.planks = TerrestriaRegistry.registerBlockItem(name + "_planks", blocks.planks);
		items.slab = TerrestriaRegistry.registerBlockItem(name + "_slab", blocks.slab);
		items.stairs = TerrestriaRegistry.registerBlockItem(name + "_stairs", blocks.stairs);
		items.fence = TerrestriaRegistry.registerBlockItem(name + "_fence", blocks.fence);
		items.fenceGate = TerrestriaRegistry.registerBlockItem(name + "_fence_gate", blocks.fenceGate);
		items.door = TerrestriaRegistry.registerBlockItem(name + "_door", blocks.door);
		items.button = TerrestriaRegistry.registerBlockItem(name + "_button", blocks.button);
		items.pressurePlate = TerrestriaRegistry.registerBlockItem(name + "_pressure_plate", blocks.pressurePlate);
		items.trapdoor = TerrestriaRegistry.registerBlockItem(name + "_trapdoor", blocks.trapdoor);
		items.sign = TerrestriaRegistry.register(name + "_sign", new SignItem(new Item.Settings().maxCount(16), blocks.sign, blocks.wallSign));
		items.hangingSign = TerrestriaRegistry.register(name + "_hanging_sign", new HangingSignItem(blocks.hangingSign, blocks.wallHangingSign, new Item.Settings().maxCount(16)));
		items.strippedLog = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_log", blocks.strippedLog);

		boatType = TerrestriaBoats.register(name, items.planks);
		if (boatType != null) {
			items.boat = (TerraformBoatItem) boatType.getItem();
			items.chestBoat = (TerraformBoatItem) boatType.getChestItem();
		}

		if (blocks.log != blocks.wood) {
			items.wood = TerrestriaRegistry.registerBlockItem(name + "_wood", blocks.wood);
		} else {
			items.wood = items.log;
		}

		if (blocks.strippedLog != blocks.strippedWood) {
			items.strippedWood = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_wood", blocks.strippedWood);
		} else {
			items.strippedWood = items.strippedLog;
		}

		items.addCompostables();
		items.addFuels();

		return items;
	}

	protected void addCompostables() {
		CompostingChanceRegistry compostingRegistry = CompostingChanceRegistry.INSTANCE;

		compostingRegistry.add(leaves, compostingRegistry.get(Items.OAK_LEAVES));
	}

	protected void addFuels() {
		FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;

		fuelRegistry.add(fence, 300);
		fuelRegistry.add(fenceGate, 300);
	}
}
