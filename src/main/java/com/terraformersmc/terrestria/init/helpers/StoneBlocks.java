package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terrestria.init.TerrestriaBlockIds;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

public class StoneBlocks {
	private final TerrestriaBlockIds.StoneBlockIds blockIds;

	public StoneVariantBlocks plain;
	public StoneVariantBlocks smooth;
	public StoneVariantBlocks cobblestone;
	public StoneVariantBlocks mossyCobblestone;
	public StoneVariantBlocks bricks;
	public StoneVariantBlocks mossyBricks;

	public ButtonBlock button;
	public PressurePlateBlock pressurePlate;
	public Block chiseledBricks;
	public Block crackedBricks;

	private StoneBlocks(TerrestriaBlockIds.StoneBlockIds blockIds, MapColor color) {
		this.blockIds = blockIds;

		plain = StoneVariantBlocks.register(blockIds.plain(), color, Blocks.STONE);
		smooth = StoneVariantBlocks.register(blockIds.smooth(), color, Blocks.SMOOTH_STONE);
		cobblestone = StoneVariantBlocks.register(blockIds.cobblestone(), color, Blocks.COBBLESTONE);
		mossyCobblestone = StoneVariantBlocks.register(blockIds.mossyCobblestone(), color, Blocks.MOSSY_COBBLESTONE);
		bricks = StoneVariantBlocks.register(blockIds.bricks(), color, Blocks.STONE_BRICKS);
		mossyBricks = StoneVariantBlocks.register(blockIds.mossyBricks(), color, Blocks.MOSSY_STONE_BRICKS);

		button = TerrestriaRegistry.register(blockIds.button(), settings -> new ButtonBlock(BlockSetType.STONE, 20, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).mapColor(color));
		pressurePlate = TerrestriaRegistry.register(blockIds.pressurePlate(), settings -> new PressurePlateBlock(BlockSetType.STONE, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_PRESSURE_PLATE).mapColor(color));
		chiseledBricks = TerrestriaRegistry.register(blockIds.chiseledBricks(), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS).mapColor(color));
		crackedBricks = TerrestriaRegistry.register(blockIds.crackedBricks(), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).mapColor(color));
	}

	public static StoneBlocks register(TerrestriaBlockIds.StoneBlockIds blockIds, MapColor color) {
		return new StoneBlocks(blockIds, color);
	}

	public String getName() {
		return blockIds.id().getPath();
	}

	public Identifier getId() {
		return blockIds.id();
	}
}
