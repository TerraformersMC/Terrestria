package com.terraformersmc.terrestria.data;

import com.terraformersmc.terraform.dirt.DirtBlocks;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.SaplingBlock;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import org.jetbrains.annotations.Nullable;

public class TerrestriaBlockLootTableProvider extends FabricBlockLootTableProvider {
	protected TerrestriaBlockLootTableProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		// simple blocks
		addDrop(TerrestriaBlocks.AGAVE);
		addDrop(TerrestriaBlocks.ALOE_VERA);
		addDrop(TerrestriaBlocks.BLACK_SAND);
		addDrop(TerrestriaBlocks.BRYCE_SAPLING);
		addDrop(TerrestriaBlocks.CATTAIL, VanillaBlockLootTableGenerator::dropsWithShears);
		addDrop(TerrestriaBlocks.CYPRESS_SAPLING);
		addDrop(TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING);
		addDrop(TerrestriaBlocks.DEAD_GRASS);
		addDrop(TerrestriaBlocks.HEMLOCK_SAPLING);
		addDrop(TerrestriaBlocks.INDIAN_PAINTBRUSH);
		addDrop(TerrestriaBlocks.JAPANESE_MAPLE_SAPLING);
		addDrop(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING);
		addDrop(TerrestriaBlocks.JUNGLE_PALM_SAPLING);
		addDrop(TerrestriaBlocks.MONSTERAS, this::grassDrops);
		addDrop(TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		addDrop(TerrestriaBlocks.REDWOOD_SAPLING);
		addDrop(TerrestriaBlocks.RUBBER_SAPLING);
		addDrop(TerrestriaBlocks.SAGUARO_CACTUS);
		addDrop(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING);
		addDrop(TerrestriaBlocks.SAKURA_SAPLING);
		addDrop(TerrestriaBlocks.SMALL_OAK_LOG);
		addDrop(TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG);
		addDrop(TerrestriaBlocks.TALL_CATTAIL, dropsWithShears(TerrestriaBlocks.CATTAIL));
		addDrop(TerrestriaBlocks.TINY_CACTUS);
		addDrop(TerrestriaBlocks.WILLOW_SAPLING);
		addDrop(TerrestriaBlocks.YUCCA_PALM_SAPLING);

		// dirt blocks
		addDirtDrops(TerrestriaBlocks.ANDISOL);

		// stone building blocks
		addStoneDrops(TerrestriaBlocks.VOLCANIC_ROCK);

		// wood building blocks
		addWoodDrops(TerrestriaBlocks.CYPRESS, TerrestriaBlocks.CYPRESS_SAPLING);
		addWoodDrops(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING);
		addWoodDrops(TerrestriaBlocks.JAPANESE_MAPLE, TerrestriaBlocks.JAPANESE_MAPLE_SAPLING);
		addWoodDrops(TerrestriaBlocks.RAINBOW_EUCALYPTUS, TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		addWoodDrops(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING);
		addWoodDrops(TerrestriaBlocks.RUBBER, TerrestriaBlocks.RUBBER_SAPLING);
		addWoodDrops(TerrestriaBlocks.SAKURA, TerrestriaBlocks.SAKURA_SAPLING);
		addWoodDrops(TerrestriaBlocks.WILLOW, TerrestriaBlocks.WILLOW_SAPLING);
		addWoodDrops(TerrestriaBlocks.YUCCA_PALM, null);

		// potted things
		addPottedPlantDrops(TerrestriaBlocks.POTTED_AGAVE);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_ALOE_VERA);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_BRYCE_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_CYPRESS_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_DARK_JAPANESE_MAPLE_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_HEMLOCK_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_INDIAN_PAINTBRUSH);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SHRUB_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_JUNGLE_PALM_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_MONSTERAS);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_RAINBOW_EUCALYPTUS_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_REDWOOD_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_RUBBER_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_SAGUARO_CACTUS_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_SAKURA_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_TINY_CACTUS);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_WILLOW_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_YUCCA_PALM_SAPLING);

		// specialty tree leaves
		addDrop(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, leavesDrops(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));
		addDrop(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, leavesDrops(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));
		addDrop(TerrestriaBlocks.JUNGLE_PALM_LEAVES, leavesDrops(TerrestriaBlocks.JUNGLE_PALM_LEAVES, TerrestriaBlocks.JUNGLE_PALM_SAPLING, 0.07f, 0.0875f, 0.116666667f, 0.14f));
		addDrop(TerrestriaBlocks.YUCCA_PALM.leaves, leavesDrops(TerrestriaBlocks.YUCCA_PALM.leaves, TerrestriaBlocks.YUCCA_PALM_SAPLING, 0.15f, 0.1875f, 0.24f, 0.333333333f));
	}

	private void addDirtDrops(DirtBlocks dirtBlock) {
		if (dirtBlock.getDirt() != null) {
			addDrop(dirtBlock.getDirt());
			if (dirtBlock.getDirtPath() != null) {
				addDrop(TerrestriaBlocks.ANDISOL.getDirtPath(), TerrestriaBlocks.ANDISOL.getDirt());
			}
			if (dirtBlock.getFarmland() != null) {
				addDrop(TerrestriaBlocks.ANDISOL.getFarmland(), TerrestriaBlocks.ANDISOL.getDirt());
			}
			if (dirtBlock.getGrassBlock() != null) {
				addDrop(TerrestriaBlocks.ANDISOL.getGrassBlock(), TerrestriaBlocks.ANDISOL.getDirt());
			}
		}
		if (dirtBlock.getPodzol() != null) {
			addDrop(TerrestriaBlocks.ANDISOL.getPodzol());
		}
	}

	private void addStoneDrops(StoneBlocks stoneBlock) {
		if (stoneBlock.bricks != null) {
			addDrop(stoneBlock.bricks.full);
			addDrop(stoneBlock.bricks.slab, this::slabDrops);
			addDrop(stoneBlock.bricks.stairs);
			addDrop(stoneBlock.bricks.wall);

			addDrop(stoneBlock.chiseledBricks);
			addDrop(stoneBlock.crackedBricks);
		}
		if (stoneBlock.cobblestone != null) {
			addDrop(stoneBlock.cobblestone.full);
			addDrop(stoneBlock.cobblestone.slab, this::slabDrops);
			addDrop(stoneBlock.cobblestone.stairs);
			addDrop(stoneBlock.cobblestone.wall);
		}
		if (stoneBlock.mossyBricks != null) {
			addDrop(stoneBlock.mossyBricks.full);
			addDrop(stoneBlock.mossyBricks.slab, this::slabDrops);
			addDrop(stoneBlock.mossyBricks.stairs);
			addDrop(stoneBlock.mossyBricks.wall);
		}
		if (stoneBlock.mossyCobblestone != null) {
			addDrop(stoneBlock.mossyCobblestone.full);
			addDrop(stoneBlock.mossyCobblestone.slab, this::slabDrops);
			addDrop(stoneBlock.mossyCobblestone.stairs);
			addDrop(stoneBlock.mossyCobblestone.wall);
		}
		if (stoneBlock.plain != null) {
			if (stoneBlock.cobblestone != null) {
				addDrop(stoneBlock.plain.full, drops(stoneBlock.plain.full, stoneBlock.cobblestone.full));
			} else {
				addDrop(stoneBlock.plain.full);
			}
			addDrop(stoneBlock.plain.slab, this::slabDrops);
			addDrop(stoneBlock.plain.stairs);
			addDrop(stoneBlock.plain.wall);
		}
		if (stoneBlock.smooth != null) {
			addDrop(stoneBlock.smooth.full);
			addDrop(stoneBlock.smooth.slab, this::slabDrops);
			addDrop(stoneBlock.smooth.stairs);
			addDrop(stoneBlock.smooth.wall);
		}

		addDrop(stoneBlock.button);
		addDrop(stoneBlock.pressurePlate);
	}

	private void addWoodDrops(WoodBlocks woodBlock, @Nullable SaplingBlock sapling) {
		addDrop(woodBlock.button);
		addDrop(woodBlock.door, this::doorDrops);
		addDrop(woodBlock.fence);
		addDrop(woodBlock.fenceGate);
		addDrop(woodBlock.hangingSign);
		addDrop(woodBlock.log);
		addDrop(woodBlock.planks);
		addDrop(woodBlock.pressurePlate);
		addDrop(woodBlock.sign);
		addDrop(woodBlock.slab, this::slabDrops);
		addDrop(woodBlock.stairs);
		addDrop(woodBlock.strippedLog);
		addDrop(woodBlock.trapdoor);
		addDrop(woodBlock.wallHangingSign);
		addDrop(woodBlock.wallSign);

		if (woodBlock.hasWood()) {
			addDrop(woodBlock.wood);
			addDrop(woodBlock.strippedWood);
		}

		if (woodBlock.hasQuarterLog()) {
			addDrop(woodBlock.quarterLog);
			addDrop(woodBlock.strippedQuarterLog);
		}

		if (sapling != null) {
			addDrop(woodBlock.leaves, leavesDrops(woodBlock.leaves, sapling, 0.05f, 0.0625f, 0.083333336f, 0.1f));
			if (woodBlock.hasLeafPile()) {
				addDrop(woodBlock.leafPile, leavesDrops(woodBlock.leafPile, sapling, 0.00625f, 0.0078125f, 0.010416667f, 0.0125f));
			}
		}
	}
}
