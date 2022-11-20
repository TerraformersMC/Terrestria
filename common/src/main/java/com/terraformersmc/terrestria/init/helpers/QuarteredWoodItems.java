package com.terraformersmc.terrestria.init.helpers;

import net.minecraft.item.BlockItem;

public class QuarteredWoodItems extends WoodItems {
	public BlockItem quarterLog;
	public BlockItem strippedQuarterLog;

	public static QuarteredWoodItems register(String name, QuarteredWoodBlocks blocks) {
		QuarteredWoodItems items = QuarteredWoodItems.copyOf(WoodItems.register(name, blocks));

		items.quarterLog = TerrestriaRegistry.registerBuildingBlockItem(name + "_quarter_log", blocks.quarterLog);
		items.strippedQuarterLog = TerrestriaRegistry.registerBuildingBlockItem("stripped_" + name + "_quarter_log", blocks.strippedQuarterLog);

		items.addCompostables();
		items.addFuels();

		return items;
	}

	private static QuarteredWoodItems copyOf(WoodItems woodItems) {
		QuarteredWoodItems items = new QuarteredWoodItems();

		items.log = woodItems.log;
		items.wood = woodItems.wood;
		items.leaves = woodItems.leaves;
		items.planks = woodItems.planks;
		items.slab = woodItems.slab;
		items.stairs = woodItems.stairs;
		items.fence = woodItems.fence;
		items.fenceGate = woodItems.fenceGate;
		items.door = woodItems.door;
		items.button = woodItems.button;
		items.pressurePlate = woodItems.pressurePlate;
		items.sign = woodItems.sign;
		items.trapdoor = woodItems.trapdoor;
		items.strippedLog = woodItems.strippedLog;
		items.strippedWood = woodItems.strippedWood;

		items.boat = woodItems.boat;
		items.chestBoat = woodItems.chestBoat;

		return items;
	}

	protected void addCompostables() {
		super.addCompostables();
	}

	protected void addFuels() {
		super.addFuels();
	}
}
