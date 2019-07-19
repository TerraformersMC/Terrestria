package net.coderbot.terrestria.init.helpers;

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

	private WoodItems() {
	}

	public static WoodItems register(String name, WoodBlocks blocks) {
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
		items.sign = TerrestriaRegistry.registerSign(name + "_sign", blocks.sign, blocks.wallSign);
		items.strippedLog = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_log", blocks.strippedLog);

		if (blocks.log != blocks.wood) {
			items.wood = TerrestriaRegistry.registerBlockItem(name + "_wood", blocks.wood);
		}
		if (blocks.strippedLog != blocks.strippedWood) {
			items.strippedWood = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_wood", blocks.strippedWood);
		}

		return items;
	}
}
