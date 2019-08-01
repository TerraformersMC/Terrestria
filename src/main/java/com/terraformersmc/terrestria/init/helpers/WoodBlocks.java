package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terraform.block.*;
import com.terraformersmc.terrestria.Terrestria;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class WoodBlocks {
	public Block log;
	public Block wood;
	public Block leaves;
	public Block planks;
	public SlabBlock slab;
	public TerraformStairsBlock stairs;
	public FenceBlock fence;
	public FenceGateBlock fenceGate;
	public TerraformDoorBlock door;
	public TerraformButtonBlock button;
	public TerraformPressurePlateBlock pressurePlate;
	public TerraformSignBlock sign;
	public TerraformWallSignBlock wallSign;
	public TerraformTrapdoorBlock trapdoor;
	public Block strippedLog;
	public Block strippedWood;
	private String name;
	private WoodColors colors;

	public WoodBlocks() {}

	public static WoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry, boolean useExtendedLeaves) {
		WoodBlocks blocks = registerManufactured(name, colors, registry);

		blocks.log = TerrestriaRegistry.register(name + "_log", new StrippableLogBlock(() -> blocks.strippedLog, colors.planks, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.bark).build()));
		blocks.wood = TerrestriaRegistry.register(name + "_wood", new StrippableLogBlock(() -> blocks.strippedWood, colors.bark, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.bark).build()));
		if (useExtendedLeaves) {
			blocks.leaves = TerrestriaRegistry.register(name + "_leaves", new ExtendedLeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).materialColor(colors.leaves).build()));
		} else {
			blocks.leaves = TerrestriaRegistry.register(name + "_leaves", new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).materialColor(colors.leaves).build()));
		}
		blocks.strippedLog = TerrestriaRegistry.register("stripped_" + name + "_log", new LogBlock(colors.planks, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.planks).build()));
		blocks.strippedWood = TerrestriaRegistry.register("stripped_" + name + "_wood", new LogBlock(colors.planks, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.planks).build()));

		blocks.addTreeFireInfo(registry);

		return blocks;
	}

	public static WoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry) {
		return register(name, colors, registry, false);
	}

	public static WoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry, LogSize size) {
		if (size.equals(LogSize.SMALL)) {
			WoodBlocks blocks = registerManufactured(name, colors, registry);

			blocks.leaves = TerrestriaRegistry.register(name + "_leaves", new TransparentLeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).materialColor(colors.leaves).build()));

			blocks.log = TerrestriaRegistry.register(name + "_log", new SmallLogBlock(blocks.leaves, () -> blocks.strippedLog, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.bark).build()));
			blocks.wood = blocks.log; // no need for a wood type

			blocks.strippedLog = TerrestriaRegistry.register("stripped_" + name + "_log", new SmallLogBlock(blocks.leaves, null, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.planks).build()));
			blocks.strippedWood = blocks.strippedLog; // wno need for a stripped wood type

			blocks.addTreeFireInfo(registry);

			return blocks;
		} else {
			return register(name, colors, registry, false);
		}
	}

	public static WoodBlocks registerManufactured(String name, WoodColors colors, FlammableBlockRegistry registry) {
		WoodBlocks blocks = new WoodBlocks();
		blocks.name = name;
		blocks.colors = colors;

		blocks.planks = TerrestriaRegistry.register(name + "_planks", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).materialColor(colors.planks).build()));
		blocks.slab = TerrestriaRegistry.register(name + "_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB).materialColor(colors.planks).build()));
		blocks.stairs = TerrestriaRegistry.register(name + "_stairs", new TerraformStairsBlock(blocks.planks, FabricBlockSettings.copy(Blocks.OAK_STAIRS).materialColor(colors.planks).build()));
		blocks.fence = TerrestriaRegistry.register(name + "_fence", new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE).materialColor(colors.planks).build()));
		blocks.fenceGate = TerrestriaRegistry.register(name + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE).materialColor(colors.planks).build()));
		blocks.door = TerrestriaRegistry.register(name + "_door", new TerraformDoorBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE).materialColor(colors.planks).build()));
		blocks.button = TerrestriaRegistry.register(name + "_button", new TerraformButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON).materialColor(colors.planks).build()));
		blocks.pressurePlate = TerrestriaRegistry.register(name + "_pressure_plate", new TerraformPressurePlateBlock(FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE).materialColor(colors.planks).build()));
		blocks.trapdoor = TerrestriaRegistry.register(name + "_trapdoor", new TerraformTrapdoorBlock(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR).materialColor(colors.planks).build()));

		Identifier signTexture = new Identifier(Terrestria.MOD_ID, "textures/entity/signs/" + name + ".png");

		blocks.sign = TerrestriaRegistry.register(name + "_sign", new TerraformSignBlock(signTexture, FabricBlockSettings.copy(Blocks.OAK_SIGN).materialColor(colors.planks).build()));
		blocks.wallSign = TerrestriaRegistry.register(name + "_wall_sign", new TerraformWallSignBlock(signTexture, FabricBlockSettings.copy(Blocks.OAK_SIGN).materialColor(colors.planks).build()));

		blocks.addManufacturedFireInfo(registry);

		return blocks;
	}

	public QuarterLogBlock registerQuarterLog(Supplier<Block> stripped, FlammableBlockRegistry registry) {
		QuarterLogBlock quarterLog = TerrestriaRegistry.register(name + "_quarter_log", new QuarterLogBlock(stripped, colors.planks, Block.Settings.copy(log)));

		registry.add(quarterLog, 5, 5);

		return quarterLog;
	}

	public QuarterLogBlock registerStrippedQuarterLog(FlammableBlockRegistry registry) {
		QuarterLogBlock quarterLog = TerrestriaRegistry.register("stripped_" + name + "_quarter_log", new QuarterLogBlock(null, colors.planks, Block.Settings.copy(strippedLog)));

		registry.add(quarterLog, 5, 5);

		return quarterLog;
	}

	public void addTreeFireInfo(FlammableBlockRegistry registry) {
		registry.add(log, 5, 5);
		registry.add(strippedLog, 5, 5);

		if (wood != log) {
			registry.add(wood, 5, 5);
		}

		if (strippedWood != strippedLog) {
			registry.add(strippedWood, 5, 5);
		}

		registry.add(leaves, 30, 60);
	}

	public void addManufacturedFireInfo(FlammableBlockRegistry registry) {
		registry.add(planks, 5, 20);
		registry.add(slab, 5, 20);
		registry.add(stairs, 5, 20);
		registry.add(fence, 5, 20);
		registry.add(fenceGate, 5, 20);
	}

	public TreeDefinition.Basic getBasicDefinition() {
		return new TreeDefinition.Basic(this.log.getDefaultState(), this.leaves.getDefaultState());
	}

	public enum LogSize {
		LARGE("large"),
		SMALL("small");

		private final String name;

		LogSize(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}
}
