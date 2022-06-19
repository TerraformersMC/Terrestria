package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.wood.block.*;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;

public class QuarteredWoodBlocks extends WoodBlocks {

	public Block quarterLog;
	public Block strippedQuarterLog;

	public QuarteredWoodBlocks() {
	}

	public static QuarteredWoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry, boolean useExtendedLeaves) {
		QuarteredWoodBlocks blocks = QuarteredWoodBlocks.copyOf(WoodBlocks.register(name, colors, registry, LogSize.NORMAL, useExtendedLeaves));
		blocks.strippedQuarterLog = TerrestriaRegistry.register("stripped_" + name + "_quarter_log", new QuarterLogBlock(null, colors.planks, Block.Settings.copy(blocks.strippedLog)));
		blocks.quarterLog = TerrestriaRegistry.register(name + "_quarter_log", new QuarterLogBlock(() -> blocks.strippedQuarterLog, colors.planks, Block.Settings.copy(blocks.log)));
		return blocks;
	}

	public static QuarteredWoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry) {
		QuarteredWoodBlocks blocks = QuarteredWoodBlocks.copyOf(WoodBlocks.register(name, colors, registry));
		blocks.strippedQuarterLog = TerrestriaRegistry.register("stripped_" + name + "_quarter_log", new QuarterLogBlock(null, colors.planks, Block.Settings.copy(blocks.strippedLog)));
		blocks.quarterLog = TerrestriaRegistry.register(name + "_quarter_log", new QuarterLogBlock(() -> blocks.strippedQuarterLog, colors.planks, Block.Settings.copy(blocks.log)));
		return blocks;
	}

	private static QuarteredWoodBlocks copyOf(WoodBlocks woodBlocks) {
		QuarteredWoodBlocks blocks = new QuarteredWoodBlocks();
		blocks.name = woodBlocks.name;
		blocks.colors = woodBlocks.colors;
		blocks.log = woodBlocks.log;
		blocks.wood = woodBlocks.wood;
		blocks.leaves = woodBlocks.leaves;
		blocks.planks = woodBlocks.planks;
		blocks.slab = woodBlocks.slab;
		blocks.stairs = woodBlocks.stairs;
		blocks.fence = woodBlocks.fence;
		blocks.fenceGate = woodBlocks.fenceGate;
		blocks.door = woodBlocks.door;
		blocks.button = woodBlocks.button;
		blocks.pressurePlate = woodBlocks.pressurePlate;
		blocks.sign = woodBlocks.sign;
		blocks.wallSign = woodBlocks.wallSign;
		blocks.trapdoor = woodBlocks.trapdoor;
		blocks.strippedLog = woodBlocks.strippedLog;
		blocks.strippedWood = woodBlocks.strippedWood;
		return blocks;
	}
}
