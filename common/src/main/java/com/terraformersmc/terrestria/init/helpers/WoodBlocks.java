package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.leaves.block.TransparentLeavesBlock;
import com.terraformersmc.terraform.wood.block.*;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.block.TerrestriaOptiLeavesBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;

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
	protected String name;
	protected WoodColors colors;

	public WoodBlocks() {}

	protected static WoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry, LogSize size, boolean useExtendedLeaves) {
		WoodBlocks blocks = registerManufactured(name, colors, registry);

		if (useExtendedLeaves) {
			if (size.equals(LogSize.SMALL)) {
				throw new IllegalArgumentException("Small log trees are not compatible with extended leaves, I'm not sure how you even did this...");
			}
			blocks.leaves = TerrestriaRegistry.register(name + "_leaves", new TerrestriaOptiLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never)));
		} else {
			if (size.equals(LogSize.SMALL)) {
				blocks.leaves = TerrestriaRegistry.register(name + "_leaves", new TransparentLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never)));
			} else {
				blocks.leaves = TerrestriaRegistry.register(name + "_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never)));
			}
		}

		if (size.equals(LogSize.SMALL)) {
			blocks.strippedLog = TerrestriaRegistry.register("stripped_" + name + "_log", new SmallLogBlock(blocks.leaves, null, FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.planks)));
			blocks.strippedWood = blocks.strippedLog; //No need for a stripped wood type
			blocks.log = TerrestriaRegistry.register(name + "_log", new SmallLogBlock(blocks.leaves, () -> blocks.strippedLog, FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.bark)));
			blocks.wood = blocks.log; //No need for a stripped wood type
		} else {
			blocks.strippedLog = TerrestriaRegistry.register("stripped_" + name + "_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.planks)));
			blocks.strippedWood = TerrestriaRegistry.register("stripped_" + name + "_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.planks)));
			blocks.log = TerrestriaRegistry.register(name + "_log", new StrippableLogBlock(() -> blocks.strippedLog, colors.planks, FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.bark)));
			blocks.wood = TerrestriaRegistry.register(name + "_wood", new StrippableLogBlock(() -> blocks.strippedWood, colors.bark, FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.bark)));
		}

		blocks.addTreeFireInfo(registry);

		return blocks;
	}

	public static WoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry, LogSize size) {
		return register(name, colors, registry, size, false);
	}

	public static WoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry) {
		return register(name, colors, registry, LogSize.NORMAL, false);
	}

	public static WoodBlocks registerManufactured(String name, WoodColors colors, FlammableBlockRegistry registry) {
		WoodBlocks blocks = new WoodBlocks();
		blocks.name = name;
		blocks.colors = colors;

		blocks.planks = TerrestriaRegistry.register(name + "_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).mapColor(colors.planks)));
		blocks.slab = TerrestriaRegistry.register(name + "_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB).mapColor(colors.planks)));
		blocks.stairs = TerrestriaRegistry.register(name + "_stairs", new TerraformStairsBlock(blocks.planks, FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).mapColor(colors.planks)));
		blocks.fence = TerrestriaRegistry.register(name + "_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE).mapColor(colors.planks)));
		blocks.fenceGate = TerrestriaRegistry.register(name + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE).mapColor(colors.planks)));
		blocks.door = TerrestriaRegistry.register(name + "_door", new TerraformDoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR).mapColor(colors.planks)));
		blocks.button = TerrestriaRegistry.register(name + "_button", new TerraformButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).mapColor(colors.planks)));
		blocks.pressurePlate = TerrestriaRegistry.register(name + "_pressure_plate", new TerraformPressurePlateBlock(FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE).mapColor(colors.planks)));
		blocks.trapdoor = TerrestriaRegistry.register(name + "_trapdoor", new TerraformTrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR).mapColor(colors.planks)));

		Identifier signTexture = new Identifier(Terrestria.MOD_ID, "entity/signs/" + name);

		blocks.sign = TerrestriaRegistry.register(name + "_sign", new TerraformSignBlock(signTexture, FabricBlockSettings.copyOf(Blocks.OAK_SIGN).mapColor(colors.planks)));
		blocks.wallSign = TerrestriaRegistry.register(name + "_wall_sign", new TerraformWallSignBlock(signTexture, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN).mapColor(colors.planks)));

		blocks.addManufacturedFireInfo(registry);

		FuelRegistry.INSTANCE.add(blocks.fence, 300);
		FuelRegistry.INSTANCE.add(blocks.fenceGate, 300);

		return blocks;
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

	public enum LogSize {
		NORMAL("normal"),
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
