package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terrestria.init.TerrestriaBlockItemIds;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Compostable;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class WoodItems {
	private final TerrestriaBlockItemIds.WoodBlockItemIds itemIds;

	public final BlockItem log;
	public final BlockItem quarterLog;
	public final BlockItem wood;
	public final BlockItem leaves;
	public final BlockItem leafPile;
	public final BlockItem planks;
	public final BlockItem slab;
	public final BlockItem stairs;
	public final BlockItem fence;
	public final BlockItem fenceGate;
	public final BlockItem door;
	public final BlockItem button;
	public final BlockItem pressurePlate;
	public final HangingSignItem hangingSign;
	public final BlockItem trapdoor;
	public final BlockItem shelf;
	public final BlockItem sign;
	public final BlockItem strippedLog;
	public final BlockItem strippedQuarterLog;
	public final BlockItem strippedWood;
	public final Item boat;
	public final Item chestBoat;

	private WoodItems(TerrestriaBlockItemIds.WoodBlockItemIds itemIds, WoodBlocks blocks) {
		this.itemIds = itemIds;

		log = TerrestriaRegistry.registerBlockItem(itemIds.log(), blocks.log);
		leaves = TerrestriaRegistry.registerBlockItem(itemIds.leaves(), blocks.leaves);
		if (blocks.hasLeafPile()) {
			leafPile = TerrestriaRegistry.registerBlockItem(itemIds.leafPile(), blocks.leafPile);
		} else {
			leafPile = null;
		}
		planks = TerrestriaRegistry.registerBlockItem(itemIds.planks(), blocks.planks);
		slab = TerrestriaRegistry.registerBlockItem(itemIds.slab(), blocks.slab);
		stairs = TerrestriaRegistry.registerBlockItem(itemIds.stairs(), blocks.stairs);
		fence = TerrestriaRegistry.register(itemIds.fence(), settings -> new BlockItem(blocks.fence, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		fenceGate = TerrestriaRegistry.register(itemIds.fenceGate(), settings -> new BlockItem(blocks.fenceGate, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		door = TerrestriaRegistry.registerBlockItem(itemIds.door(), blocks.door);
		button = TerrestriaRegistry.registerBlockItem(itemIds.button(), blocks.button);
		pressurePlate = TerrestriaRegistry.registerBlockItem(itemIds.pressurePlate(), blocks.pressurePlate);
		trapdoor = TerrestriaRegistry.registerBlockItem(itemIds.trapdoor(), blocks.trapdoor);
		shelf = TerrestriaRegistry.registerBlockItem(itemIds.shelf(), blocks.shelf);
		sign = TerrestriaRegistry.register(itemIds.sign(), settings -> new StandingAndWallBlockItem(blocks.sign, blocks.wallSign, Direction.DOWN, settings), new Item.Properties().stacksTo(16).useBlockDescriptionPrefix());
		hangingSign = TerrestriaRegistry.register(itemIds.hangingSign(), settings -> new HangingSignItem(blocks.hangingSign, blocks.wallHangingSign, settings), new Item.Properties().stacksTo(16).useBlockDescriptionPrefix());
		strippedLog = TerrestriaRegistry.registerBlockItem(itemIds.strippedLog(), blocks.strippedLog);

		Identifier family = getId();
		boat = TerraformBoatItemHelper.registerBoatItem(family, false);
		chestBoat = TerraformBoatItemHelper.registerBoatItem(family, true);

		if (blocks.hasWood()) {
			wood = TerrestriaRegistry.registerBlockItem(itemIds.wood(), blocks.wood);
			strippedWood = TerrestriaRegistry.registerBlockItem(itemIds.strippedWood(), blocks.strippedWood);
		} else {
			wood = null;
			strippedWood = null;
		}

		if (blocks.hasQuarterLog()) {
			quarterLog = TerrestriaRegistry.registerBlockItem(itemIds.quarterLog(), blocks.quarterLog);
			strippedQuarterLog = TerrestriaRegistry.registerBlockItem(itemIds.strippedQuarterLog(), blocks.strippedQuarterLog);
		} else {
			quarterLog = null;
			strippedQuarterLog = null;
		}
	}


	public static WoodItems register(TerrestriaBlockItemIds.WoodBlockItemIds itemIds, WoodBlocks blocks) {
		WoodItems items = new WoodItems(itemIds, blocks);

		items.addCompostables();

		return items;
	}

	protected void addCompostables() {
		DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
			modifyContext.modify(leaves, builder ->
				builder.set(DataComponents.COMPOSTABLE,
					new Compostable(ContextIntProviders.COMPOSTABLE_LOW))
			);
			if (hasLeafPile()) {
				modifyContext.modify(leafPile, builder ->
					builder.set(DataComponents.COMPOSTABLE,
						new Compostable(ContextIntProviders.COMPOSTABLE_LOW))
				);
			}
		});
	}

	public String getName() {
		return itemIds.id().getPath();
	}

	public Identifier getId() {
		return itemIds.id();
	}

	public boolean hasQuarterLog() {
		return (quarterLog != null && strippedQuarterLog != null);
	}

	public boolean hasLeafPile() {
		return (leafPile != null);
	}

	public boolean hasWood() {
		return (wood != null && strippedWood != null);
	}

	public boolean hasBoat() {
		return (boat != null && chestBoat != null);
	}
}
