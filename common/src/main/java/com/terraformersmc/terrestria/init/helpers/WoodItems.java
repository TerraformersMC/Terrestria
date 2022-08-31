package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.SignItem;

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
	public BlockItem trapdoor;
	public BlockItem strippedLog;
	public BlockItem strippedWood;
	public TerraformBoatItem boat;

	private WoodItems() {
	}

	public static WoodItems register(String name, WoodBlocks blocks, TerraformBoatItem boatItem) {
		WoodItems items = new WoodItems();

		items.log = TerrestriaRegistry.registerBuildingBlockItem(name + "_log", blocks.log);
		items.leaves = TerrestriaRegistry.registerDecorationBlockItem(name + "_leaves", blocks.leaves);
		items.planks = TerrestriaRegistry.registerBuildingBlockItem(name + "_planks", blocks.planks);
		items.slab = TerrestriaRegistry.registerBuildingBlockItem(name + "_slab", blocks.slab);
		items.stairs = TerrestriaRegistry.registerBuildingBlockItem(name + "_stairs", blocks.stairs);
		items.fence = TerrestriaRegistry.registerDecorationBlockItem(name + "_fence", blocks.fence);
		items.fenceGate = TerrestriaRegistry.registerRedstoneBlockItem(name + "_fence_gate", blocks.fenceGate);
		items.door = TerrestriaRegistry.registerRedstoneBlockItem(name + "_door", blocks.door);
		items.button = TerrestriaRegistry.registerRedstoneBlockItem(name + "_button", blocks.button);
		items.pressurePlate = TerrestriaRegistry.registerRedstoneBlockItem(name + "_pressure_plate", blocks.pressurePlate);
		items.trapdoor = TerrestriaRegistry.registerRedstoneBlockItem(name + "_trapdoor", blocks.trapdoor);
		items.sign = TerrestriaRegistry.registerSignItem(name + "_sign", blocks.sign, blocks.wallSign);
		items.strippedLog = TerrestriaRegistry.registerBuildingBlockItem("stripped_" + name + "_log", blocks.strippedLog);
		items.boat = boatItem;

		if (blocks.log != blocks.wood) {
			items.wood = TerrestriaRegistry.registerBuildingBlockItem(name + "_wood", blocks.wood);
		} else {
			items.wood = items.log;
		}

		if (blocks.strippedLog != blocks.strippedWood) {
			items.strippedWood = TerrestriaRegistry.registerBuildingBlockItem("stripped_" + name + "_wood", blocks.strippedWood);
		} else {
			items.strippedWood = items.strippedLog;
		}

		return items;
	}
}
