package com.terraformersmc.terrestria.data;

import com.terraformersmc.terraform.dirt.DirtBlocks;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.helpers.QuarteredWoodBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.SaplingBlock;

import javax.annotation.Nullable;

public class TerrestriaBlockLootTableProvider extends FabricBlockLootTableProvider {
	protected TerrestriaBlockLootTableProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void generateBlockLootTables() {
		// simple blocks
		addDrop(TerrestriaBlocks.AGAVE);
		addDrop(TerrestriaBlocks.ALOE_VERA);
		addDrop(TerrestriaBlocks.BLACK_SAND);
		addDrop(TerrestriaBlocks.BRYCE_SAPLING);
		addDrop(TerrestriaBlocks.CATTAIL, dropsWithShears(TerrestriaBlocks.CATTAIL));
		addDrop(TerrestriaBlocks.CYPRESS_SAPLING);
		addDrop(TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING);
		addDrop(TerrestriaBlocks.DEAD_GRASS);
		addDrop(TerrestriaBlocks.HEMLOCK_SAPLING);
		addDrop(TerrestriaBlocks.INDIAN_PAINTBRUSH);
		addDrop(TerrestriaBlocks.JAPANESE_MAPLE_SAPLING);
		addDrop(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING);
		addDrop(TerrestriaBlocks.JUNGLE_PALM_SAPLING);
		addDrop(TerrestriaBlocks.MONSTERAS, grassDrops(TerrestriaBlocks.MONSTERAS));
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
		addPottedPlantDrop(TerrestriaBlocks.POTTED_AGAVE);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_ALOE_VERA);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_BRYCE_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_CYPRESS_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_DARK_JAPANESE_MAPLE_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_HEMLOCK_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_INDIAN_PAINTBRUSH);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SHRUB_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_JUNGLE_PALM_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_MONSTERAS);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_RAINBOW_EUCALYPTUS_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_REDWOOD_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_RUBBER_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_SAGUARO_CACTUS_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_SAKURA_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_TINY_CACTUS);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_WILLOW_SAPLING);
		addPottedPlantDrop(TerrestriaBlocks.POTTED_YUCCA_PALM_SAPLING);

		// specialty tree leaves
		addDrop(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, leavesDrop(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));
		addDrop(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, leavesDrop(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));
		addDrop(TerrestriaBlocks.JUNGLE_PALM_LEAVES, leavesDrop(TerrestriaBlocks.JUNGLE_PALM_LEAVES, TerrestriaBlocks.JUNGLE_PALM_SAPLING, 0.07f, 0.0875f, 0.116666667f, 0.14f));
		addDrop(TerrestriaBlocks.SAKURA_LEAF_PILE, leavesDrop(TerrestriaBlocks.SAKURA_LEAF_PILE, TerrestriaBlocks.SAKURA_SAPLING, 0.00625f, 0.0078125f, 0.010416667f, 0.0125f));
		addDrop(TerrestriaBlocks.YUCCA_PALM.leaves, leavesDrop(TerrestriaBlocks.YUCCA_PALM.leaves, TerrestriaBlocks.YUCCA_PALM_SAPLING, 0.15f, 0.1875f, 0.24f, 0.333333333f));
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
			addDrop(stoneBlock.bricks.slab, slabDrops(stoneBlock.bricks.slab));
			addDrop(stoneBlock.bricks.stairs);
			addDrop(stoneBlock.bricks.wall);

			addDrop(stoneBlock.chiseledBricks);
			addDrop(stoneBlock.crackedBricks);
		}
		if (stoneBlock.cobblestone != null) {
			addDrop(stoneBlock.cobblestone.full);
			addDrop(stoneBlock.cobblestone.slab, slabDrops(stoneBlock.cobblestone.slab));
			addDrop(stoneBlock.cobblestone.stairs);
			addDrop(stoneBlock.cobblestone.wall);
		}
		if (stoneBlock.mossyBricks != null) {
			addDrop(stoneBlock.mossyBricks.full);
			addDrop(stoneBlock.mossyBricks.slab, slabDrops(stoneBlock.mossyBricks.slab));
			addDrop(stoneBlock.mossyBricks.stairs);
			addDrop(stoneBlock.mossyBricks.wall);
		}
		if (stoneBlock.mossyCobblestone != null) {
			addDrop(stoneBlock.mossyCobblestone.full);
			addDrop(stoneBlock.mossyCobblestone.slab, slabDrops(stoneBlock.mossyCobblestone.slab));
			addDrop(stoneBlock.mossyCobblestone.stairs);
			addDrop(stoneBlock.mossyCobblestone.wall);
		}
		if (stoneBlock.plain != null) {
			if (stoneBlock.cobblestone != null) {
				addDrop(stoneBlock.plain.full, drops(stoneBlock.plain.full, stoneBlock.cobblestone.full));
			} else {
				addDrop(stoneBlock.plain.full);
			}
			addDrop(stoneBlock.plain.slab, slabDrops(stoneBlock.plain.slab));
			addDrop(stoneBlock.plain.stairs);
			addDrop(stoneBlock.plain.wall);
		}
		if (stoneBlock.smooth != null) {
			addDrop(stoneBlock.smooth.full);
			addDrop(stoneBlock.smooth.slab, slabDrops(stoneBlock.smooth.slab));
			addDrop(stoneBlock.smooth.stairs);
			addDrop(stoneBlock.smooth.wall);
		}

		addDrop(stoneBlock.button);
		addDrop(stoneBlock.pressurePlate);
	}

	private void addWoodDrops(WoodBlocks woodBlock, @Nullable SaplingBlock sapling) {
		addDrop(woodBlock.button);
		addDrop(woodBlock.door, addDoorDrop(woodBlock.door));
		addDrop(woodBlock.fence);
		addDrop(woodBlock.fenceGate);
		addDrop(woodBlock.log);
		addDrop(woodBlock.planks);
		addDrop(woodBlock.pressurePlate);
		addDrop(woodBlock.sign);
		addDrop(woodBlock.slab, slabDrops(woodBlock.slab));
		addDrop(woodBlock.stairs);
		addDrop(woodBlock.strippedLog);
		addDrop(woodBlock.strippedWood);
		addDrop(woodBlock.trapdoor);
		addDrop(woodBlock.wallSign);
		addDrop(woodBlock.wood);

		if (woodBlock instanceof QuarteredWoodBlocks) {
			addDrop(((QuarteredWoodBlocks) woodBlock).quarterLog);
			addDrop(((QuarteredWoodBlocks) woodBlock).strippedQuarterLog);
		}

		if (sapling != null) {
			addDrop(woodBlock.leaves, leavesDrop(woodBlock.leaves, sapling, 0.05f, 0.0625f, 0.083333336f, 0.1f));
		}
	}
}
