package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.leaves.api.block.ColoredParticleLeavesBlock;
import com.terraformersmc.terraform.leaves.api.block.ExtendedLeavesBlock;
import com.terraformersmc.terraform.leaves.api.block.LeafPileBlock;
import com.terraformersmc.terraform.wood.api.block.PillarLogHelper;
import com.terraformersmc.terraform.wood.api.block.QuarterLogBlock;
import com.terraformersmc.terraform.wood.api.block.SmallLogBlock;
import com.terraformersmc.terrestria.block.TerrestriaOptiLeavesBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlockIds;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.sounds.AmbientLeavesBlockSoundPlayer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// TODO: Consider reverting to a record with builder object to enable config of things like
//       BlockSetType, WoodType, flammability, and simplify the 'has', 'is, 'uses' args.
public class WoodBlocks {
	public static final Map<Block, WoodBlocks> WOOD_BLOCKS_FROM_LOG = new ConcurrentHashMap<>();

	private final TerrestriaBlockIds.WoodBlockIds blockIds;
	private final WoodColors colors;
	private final LogSize size;

	public final BlockSetType blockSetType;
	public final WoodType woodType;

	private final boolean tintable;

	public final Block log;
	public final Block quarterLog;
	public final Block wood;
	public final Block leaves;
	public final LeafPileBlock leafPile;
	public final Block planks;
	public final SlabBlock slab;
	public final StairBlock stairs;
	public final FenceBlock fence;
	public final FenceGateBlock fenceGate;
	public final DoorBlock door;
	public final ButtonBlock button;
	public final PressurePlateBlock pressurePlate;
	public final StandingSignBlock sign;
	public final WallSignBlock wallSign;
	public final CeilingHangingSignBlock hangingSign;
	public final WallHangingSignBlock wallHangingSign;
	public final TrapDoorBlock trapdoor;
	public final ShelfBlock shelf;
	public final Block strippedLog;
	public final Block strippedQuarterLog;
	public final Block strippedWood;

	private WoodBlocks(TerrestriaBlockIds.WoodBlockIds blockIds, WoodColors colors, LogSize size, boolean hasLeafPile, boolean hasQuarterLog, boolean usesExtendedLeaves, boolean isTintable) {
		this.blockIds = blockIds;

		this.tintable = isTintable;
		this.colors = colors;
		this.size = size;

		this.blockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(blockIds.id());
		this.woodType = WoodTypeBuilder.copyOf(WoodType.OAK).register(blockIds.id(), this.blockSetType);

		// register manufactured blocks

		planks = TerrestriaRegistry.register(blockIds.planks(), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(colors.planks));
		slab = TerrestriaRegistry.register(blockIds.slab(), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).mapColor(colors.planks));
		stairs = TerrestriaRegistry.register(blockIds.stairs(), settings -> new StairBlock(planks.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS).mapColor(colors.planks));
		fence = TerrestriaRegistry.register(blockIds.fence(), FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).mapColor(colors.planks));
		fenceGate = TerrestriaRegistry.register(blockIds.fenceGate(), settings -> new FenceGateBlock(WoodType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).mapColor(colors.planks));
		door = TerrestriaRegistry.register(blockIds.door(), settings -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).mapColor(colors.planks));
		button = TerrestriaRegistry.register(blockIds.button(), settings -> new ButtonBlock(BlockSetType.OAK, 30, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).mapColor(colors.planks));
		pressurePlate = TerrestriaRegistry.register(blockIds.pressurePlate(), settings -> new PressurePlateBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).mapColor(colors.planks));
		trapdoor = TerrestriaRegistry.register(blockIds.trapdoor(), settings -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).mapColor(colors.planks));
		shelf = TerrestriaRegistry.register(blockIds.shelf(), ShelfBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(colors.planks));
		sign = TerrestriaRegistry.registerSignBlock(blockIds.sign(), settings -> new StandingSignBlock(woodType, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN).mapColor(colors.planks));
		wallSign = TerrestriaRegistry.registerSignBlock(blockIds.wallSign(), settings -> new WallSignBlock(woodType, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN).mapColor(colors.planks).overrideLootTable(sign.getLootTable()));
		hangingSign = TerrestriaRegistry.registerSignBlock(blockIds.hangingSign(), settings -> new CeilingHangingSignBlock(woodType, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN).mapColor(colors.planks));
		wallHangingSign = TerrestriaRegistry.registerSignBlock(blockIds.wallHangingSign(), settings -> new WallHangingSignBlock(woodType, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN).mapColor(colors.planks).overrideLootTable(hangingSign.getLootTable()));

		// register natural and stripped blocks

		if (usesExtendedLeaves) {
			leaves = TerrestriaRegistry.register(blockIds.leaves(), TerrestriaOptiLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(colors.leaves).isValidSpawn(TerrestriaBlocks::canSpawnOnLeaves).isSuffocating(TerrestriaBlocks::never).isViewBlocking(TerrestriaBlocks::never));
		} else {
			if (size.equals(LogSize.SMALL)) {
				leaves = TerrestriaRegistry.register(blockIds.leaves(), settings -> new ExtendedLeavesBlock(0.01f, tintable ? Optional.empty() : Optional.of(ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, colors.leaves.col)), false, true, AmbientLeavesBlockSoundPlayer.noAmbientSound(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(colors.leaves).isValidSpawn(TerrestriaBlocks::canSpawnOnLeaves).isSuffocating(TerrestriaBlocks::never).isViewBlocking(TerrestriaBlocks::never));
			} else {
				if (tintable) {
					leaves = TerrestriaRegistry.register(blockIds.leaves(), settings -> new TintedParticleLeavesBlock(0.01f, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(colors.leaves).isValidSpawn(TerrestriaBlocks::canSpawnOnLeaves).isSuffocating(TerrestriaBlocks::never).isViewBlocking(TerrestriaBlocks::never));
				} else {
					leaves = TerrestriaRegistry.register(blockIds.leaves(), settings -> new ColoredParticleLeavesBlock(0.01f, colors.leaves.col, AmbientLeavesBlockSoundPlayer.noAmbientSound(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(colors.leaves).isValidSpawn(TerrestriaBlocks::canSpawnOnLeaves).isSuffocating(TerrestriaBlocks::never).isViewBlocking(TerrestriaBlocks::never));
				}
			}
		}

		if (hasLeafPile) {
			leafPile = TerrestriaRegistry.register(blockIds.leafPile(), LeafPileBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LEAF_LITTER).mapColor(colors.leaves));
		} else {
			leafPile = null;
		}

		if (size.equals(LogSize.SMALL)) {
			// Small logs have neither wood nor quarter logs.
			log = TerrestriaRegistry.register(blockIds.log(), settings -> new SmallLogBlock(leaves, settings), PillarLogHelper.createSmallLogProperties(leaves, colors.planks, colors.bark));
			strippedLog = TerrestriaRegistry.register(blockIds.strippedLog(), settings -> new SmallLogBlock(leaves, settings), PillarLogHelper.createSmallLogProperties(leaves, colors.planks));

			wood = null;
			strippedWood = null;

			quarterLog = null;
			strippedQuarterLog = null;
		} else {
			log = TerrestriaRegistry.register(blockIds.log(), RotatedPillarBlock::new, PillarLogHelper.createProperties(colors.planks, colors.bark));
			strippedLog = TerrestriaRegistry.register(blockIds.strippedLog(), RotatedPillarBlock::new, PillarLogHelper.createProperties(colors.planks));

			wood = TerrestriaRegistry.register(blockIds.wood(), RotatedPillarBlock::new, PillarLogHelper.createProperties(colors.bark));
			strippedWood = TerrestriaRegistry.register(blockIds.strippedWood(), RotatedPillarBlock::new, PillarLogHelper.createProperties(colors.planks));

			if (hasQuarterLog) {
				quarterLog = TerrestriaRegistry.register(blockIds.quarterLog(), QuarterLogBlock::new, PillarLogHelper.createQuarterLogProperties(colors.planks, colors.bark));
				strippedQuarterLog = TerrestriaRegistry.register(blockIds.strippedQuarterLog(), QuarterLogBlock::new, PillarLogHelper.createProperties(colors.planks));
			} else {
				quarterLog = null;
				strippedQuarterLog = null;
			}
		}

		if (WOOD_BLOCKS_FROM_LOG.containsKey(log)) {
			throw new IllegalStateException("Attempted to add a second WoodBlocks for '" + log + "'");
		}
		WOOD_BLOCKS_FROM_LOG.put(log, this);
	}

	public static WoodBlocks register(TerrestriaBlockIds.WoodBlockIds blockIds, WoodColors colors, LogSize size, boolean hasLeafPile, boolean hasQuarteredLog, boolean usesExtendedLeaves, boolean isTintable) {
		WoodBlocks blocks = new WoodBlocks(blockIds, colors, size, hasLeafPile, hasQuarteredLog, usesExtendedLeaves, isTintable);

		blocks.addBlockEntityTypes();
		blocks.addFlammables();
		blocks.addStrippables();

		return blocks;
	}

	public static WoodBlocks register(TerrestriaBlockIds.WoodBlockIds blockIds, WoodColors colors, LogSize size) {
		return register(blockIds, colors, size, false, false, false, true);
	}

	public static WoodBlocks register(TerrestriaBlockIds.WoodBlockIds blockIds, WoodColors colors) {
		return register(blockIds, colors, LogSize.NORMAL);
	}

	private void addBlockEntityTypes() {
		BlockEntityTypes.SHELF.addValidBlock(shelf);
	}

	private void addFlammables() {
		FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();

		// manufactured
		flammableRegistry.add(fence, 5, 20);
		flammableRegistry.add(fenceGate, 5, 20);
		flammableRegistry.add(planks, 5, 20);
		flammableRegistry.add(shelf, 30, 20);
		flammableRegistry.add(slab, 5, 20);
		flammableRegistry.add(stairs, 5, 20);

		// tree
		flammableRegistry.add(log, 5, 5);
		flammableRegistry.add(strippedLog, 5, 5);
		if (hasWood()) {
			flammableRegistry.add(wood, 5, 5);
			flammableRegistry.add(strippedWood, 5, 5);
		}
		if (hasQuarterLog()) {
			flammableRegistry.add(quarterLog, 5, 5);
			flammableRegistry.add(strippedQuarterLog, 5, 5);
		}

		flammableRegistry.add(leaves, 30, 60);
		if (hasLeafPile()) {
			flammableRegistry.add(leafPile, 30, 60);
		}
	}

	private void addStrippables() {
		if (log != null && strippedLog != null) {
			BlockTransformerHelper.registerStripping(log, strippedLog);
		}
		if (wood != null && strippedWood != null) {
			BlockTransformerHelper.registerStripping(wood, strippedWood);
		}
		if (quarterLog != null && strippedQuarterLog != null) {
			BlockTransformerHelper.registerStripping(quarterLog, strippedQuarterLog);
		}
	}

	public String getName() {
		return blockIds.id().getPath();
	}

	public Identifier getId() {
		return blockIds.id();
	}

	public WoodColors getColors() {
		return colors;
	}

	public LogSize getSize() {
		return size;
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

	public boolean isTintable() {
		return tintable;
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

	public static Optional<WoodBlocks> getFromLog(Block log) {
		return Optional.ofNullable(WOOD_BLOCKS_FROM_LOG.get(log));
	}
}
