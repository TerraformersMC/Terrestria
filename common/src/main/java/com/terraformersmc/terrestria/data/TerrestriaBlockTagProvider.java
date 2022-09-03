package com.terraformersmc.terrestria.data;

import com.terraformersmc.terraform.dirt.DirtBlocks;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.helpers.QuarteredWoodBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneVariantBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import com.terraformersmc.terrestria.tag.TerrestriaBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SandBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;

public class TerrestriaBlockTagProvider extends FabricTagProvider.BlockTagProvider {

	public TerrestriaBlockTagProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void generateTags() {
		// basic block tags
		this.getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
			.add(TerrestriaBlocks.POTTED_AGAVE)
			.add(TerrestriaBlocks.POTTED_ALOE_VERA)
			.add(TerrestriaBlocks.POTTED_BRYCE_SAPLING)
			.add(TerrestriaBlocks.POTTED_CYPRESS_SAPLING)
			.add(TerrestriaBlocks.POTTED_DARK_JAPANESE_MAPLE_SAPLING)
			.add(TerrestriaBlocks.POTTED_HEMLOCK_SAPLING)
			.add(TerrestriaBlocks.POTTED_INDIAN_PAINTBRUSH)
			.add(TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SAPLING)
			.add(TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SHRUB_SAPLING)
			.add(TerrestriaBlocks.POTTED_JUNGLE_PALM_SAPLING)
			.add(TerrestriaBlocks.POTTED_MONSTERAS)
			.add(TerrestriaBlocks.POTTED_RAINBOW_EUCALYPTUS_SAPLING)
			.add(TerrestriaBlocks.POTTED_REDWOOD_SAPLING)
			.add(TerrestriaBlocks.POTTED_RUBBER_SAPLING)
			.add(TerrestriaBlocks.POTTED_SAGUARO_CACTUS_SAPLING)
			.add(TerrestriaBlocks.POTTED_SAKURA_SAPLING)
			.add(TerrestriaBlocks.POTTED_TINY_CACTUS)
			.add(TerrestriaBlocks.POTTED_WILLOW_SAPLING)
			.add(TerrestriaBlocks.POTTED_YUCCA_PALM_SAPLING);

		this.getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
			.add(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES)
			.add(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES)
			.add(TerrestriaBlocks.JUNGLE_PALM_LEAVES)
			.add(TerrestriaBlocks.SAKURA_LEAF_PILE);

		this.getOrCreateTagBuilder(BlockTags.LEAVES)
			.add(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES)
			.add(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES)
			.add(TerrestriaBlocks.JUNGLE_PALM_LEAVES);

		this.getOrCreateTagBuilder(BlockTags.OAK_LOGS)
			.addTag(TerrestriaBlockTags.SMALL_OAK_LOGS);

		this.getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
			.add(Blocks.SMOOTH_SANDSTONE)
			.add(TerrestriaBlocks.ANDISOL.getDirt())
			.add(TerrestriaBlocks.ANDISOL.getGrassBlock())
			.add(TerrestriaBlocks.VOLCANIC_ROCK.plain.full);

		this.getOrCreateTagBuilder(BlockTags.SAPLINGS)
			.add(TerrestriaBlocks.BRYCE_SAPLING)
			.add(TerrestriaBlocks.CYPRESS_SAPLING)
			.add(TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING)
			.add(TerrestriaBlocks.HEMLOCK_SAPLING)
			.add(TerrestriaBlocks.JAPANESE_MAPLE_SAPLING)
			.add(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING)
			.add(TerrestriaBlocks.JUNGLE_PALM_SAPLING)
			.add(TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING)
			.add(TerrestriaBlocks.REDWOOD_SAPLING)
			.add(TerrestriaBlocks.RUBBER_SAPLING)
			.add(TerrestriaBlocks.SAKURA_SAPLING)
			.add(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING)
			.add(TerrestriaBlocks.WILLOW_SAPLING)
			.add(TerrestriaBlocks.YUCCA_PALM_SAPLING);

		this.getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
			.add(TerrestriaBlocks.INDIAN_PAINTBRUSH)
			.add(TerrestriaBlocks.MONSTERAS);


		this.getOrCreateTagBuilder(TerrestriaBlockTags.BLACK_SAND)
			.add(TerrestriaBlocks.BLACK_SAND);

		this.getOrCreateTagBuilder(TerrestriaBlockTags.SMALL_OAK_LOGS)
			.add(TerrestriaBlocks.SMALL_OAK_LOG)
			.add(TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG);


		// custom dirt block tags
		addDirt(TerrestriaBlocks.ANDISOL);

		// custom sand block tags
		addSand(TerrestriaBlocks.BLACK_SAND);

		// stone building block tags
		addStone(TerrestriaBlockTags.BASALT, TerrestriaBlocks.VOLCANIC_ROCK);

		// wood building block tags
		addWood(TerrestriaBlockTags.CYPRESS_LOGS, TerrestriaBlocks.CYPRESS);
		addWood(TerrestriaBlockTags.HEMLOCK_LOGS, TerrestriaBlocks.HEMLOCK);
		addWood(TerrestriaBlockTags.JAPANESE_MAPLE_LOGS, TerrestriaBlocks.JAPANESE_MAPLE);
		addWood(TerrestriaBlockTags.RAINBOW_EUCALYPTUS_LOGS, TerrestriaBlocks.RAINBOW_EUCALYPTUS);
		addWood(TerrestriaBlockTags.REDWOOD_LOGS, TerrestriaBlocks.REDWOOD);
		addWood(TerrestriaBlockTags.RUBBER_LOGS, TerrestriaBlocks.RUBBER);
		addWood(TerrestriaBlockTags.SAKURA_LOGS, TerrestriaBlocks.SAKURA);
		addWood(TerrestriaBlockTags.WILLOW_LOGS, TerrestriaBlocks.WILLOW);
		addWood(TerrestriaBlockTags.YUCCA_PALM_LOGS, TerrestriaBlocks.YUCCA_PALM);

		this.getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
			.addTag(TerrestriaBlockTags.CYPRESS_LOGS)
			.addTag(TerrestriaBlockTags.HEMLOCK_LOGS)
			.addTag(TerrestriaBlockTags.JAPANESE_MAPLE_LOGS)
			.addTag(TerrestriaBlockTags.RAINBOW_EUCALYPTUS_LOGS)
			.addTag(TerrestriaBlockTags.REDWOOD_LOGS)
			.addTag(TerrestriaBlockTags.RUBBER_LOGS)
			.addTag(TerrestriaBlockTags.SAKURA_LOGS)
			.addTag(TerrestriaBlockTags.SMALL_OAK_LOGS)
			.addTag(TerrestriaBlockTags.WILLOW_LOGS)
			.addTag(TerrestriaBlockTags.YUCCA_PALM_LOGS);
	}

	private void addDirt(DirtBlocks dirtBlock) {
		getOrCreateTagBuilder(BlockTags.DIRT)
			.add(dirtBlock.getDirt())
			.add(dirtBlock.getGrassBlock())
			.add(dirtBlock.getPodzol());

		getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE)
			.add(dirtBlock.getDirt())
			.add(dirtBlock.getGrassBlock())
			.add(dirtBlock.getPodzol());

		getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK)
			.add(dirtBlock.getPodzol());

		getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
			.add(dirtBlock.getDirt())
			.add(dirtBlock.getDirtPath())
			.add(dirtBlock.getFarmland())
			.add(dirtBlock.getGrassBlock())
			.add(dirtBlock.getPodzol());

		getOrCreateTagBuilder(BlockTags.VALID_SPAWN)
			.add(dirtBlock.getGrassBlock())
			.add(dirtBlock.getPodzol());


		getOrCreateTagBuilder(TerrestriaBlockTags.FARMLAND)
			.add(dirtBlock.getFarmland());

		getOrCreateTagBuilder(TerrestriaBlockTags.GRASS_BLOCKS)
			.add(dirtBlock.getGrassBlock());

		getOrCreateTagBuilder(TerrestriaBlockTags.PODZOL)
			.add(dirtBlock.getPodzol());

		getOrCreateTagBuilder(TerrestriaBlockTags.SOIL)
			.add(dirtBlock.getDirt())
			.add(dirtBlock.getGrassBlock())
			.add(dirtBlock.getPodzol());
	}

	private void addSand(SandBlock sandBlock) {
		getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(sandBlock);
		getOrCreateTagBuilder(BlockTags.SAND).add(sandBlock);
		getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(sandBlock);
	}

	private void addStone(TagKey<Block> stoneTag, StoneBlocks stoneBlock) {
		FabricTagBuilder<Block> stoneBuilder = getOrCreateTagBuilder(stoneTag);
		if (stoneBlock.bricks != null) {
			stoneBuilder
				.add(stoneBlock.bricks.full)

				.add(stoneBlock.chiseledBricks)
				.add(stoneBlock.crackedBricks);

			addStoneVariant(stoneBlock.bricks);
		}
		if (stoneBlock.cobblestone != null) {
			stoneBuilder.add(stoneBlock.cobblestone.full);
			addStoneVariant(stoneBlock.cobblestone);
		}
		if (stoneBlock.mossyBricks != null) {
			stoneBuilder.add(stoneBlock.mossyBricks.full);
			addStoneVariant(stoneBlock.mossyBricks);
		}
		if (stoneBlock.mossyCobblestone != null) {
			stoneBuilder.add(stoneBlock.mossyCobblestone.full);
			addStoneVariant(stoneBlock.mossyCobblestone);
		}
		if (stoneBlock.plain != null) {
			stoneBuilder.add(stoneBlock.plain.full);
			addStoneVariant(stoneBlock.plain);
		}
		if (stoneBlock.smooth != null) {
			stoneBuilder.add(stoneBlock.smooth.full);
			addStoneVariant(stoneBlock.smooth);
		}

		getOrCreateTagBuilder(BlockTags.BUTTONS).add(stoneBlock.button);
		getOrCreateTagBuilder(BlockTags.PRESSURE_PLATES).add(stoneBlock.pressurePlate);
	}

	private void addStoneVariant(StoneVariantBlocks stoneVariantBlock) {
		getOrCreateTagBuilder(BlockTags.SLABS).add(stoneVariantBlock.slab);
		getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneVariantBlock.stairs);
		getOrCreateTagBuilder(BlockTags.WALLS).add(stoneVariantBlock.wall);

		getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
				.add(stoneVariantBlock.full)
				.add(stoneVariantBlock.slab)
				.add(stoneVariantBlock.stairs);
				// Adding to WALLS does this for PICKAXE_MINEABLE.
	}

	private void addWood(TagKey<Block> logTag, WoodBlocks woodBlock) {
		FabricTagBuilder<Block> woodBuilder = getOrCreateTagBuilder(logTag);
		woodBuilder
			.add(woodBlock.log)
			.add(woodBlock.strippedLog);
		if (woodBlock.strippedWood != null) {
			woodBuilder.add(woodBlock.strippedWood);
		}
		if (woodBlock.wood != null) {
			woodBuilder.add(woodBlock.wood);
		}
		if(woodBlock instanceof QuarteredWoodBlocks) {
			woodBuilder
				.add(((QuarteredWoodBlocks) woodBlock).quarterLog)
				.add(((QuarteredWoodBlocks) woodBlock).strippedQuarterLog);
		}

		getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(woodBlock.fenceGate);
		getOrCreateTagBuilder(BlockTags.LEAVES).add(woodBlock.leaves);
		getOrCreateTagBuilder(BlockTags.PLANKS).add(woodBlock.planks);
		getOrCreateTagBuilder(BlockTags.SLABS).add(woodBlock.slab);
		getOrCreateTagBuilder(BlockTags.STAIRS).add(woodBlock.stairs);
		getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(woodBlock.sign);
		getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(woodBlock.wallSign);
		getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(woodBlock.button);
		getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(woodBlock.door);
		getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(woodBlock.fence);
		getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(woodBlock.pressurePlate);
		getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(woodBlock.slab);
		getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(woodBlock.stairs);
		getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(woodBlock.trapdoor);

		// Adding to FENCE_GATES or any WOODEN tag does this for AXE_MINEABLE.
		getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(woodBlock.leaves);
	}
}
