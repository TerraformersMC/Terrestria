package com.terraformersmc.terrestria.data;

import com.terraformersmc.terraform.dirt.DirtBlocks;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneVariantBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import com.terraformersmc.terrestria.tag.TerrestriaBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SandBlock;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class TerrestriaBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	protected TerrestriaBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void configure(RegistryWrapper.WrapperLookup registries) {
		// basic block tags
		getOrCreateTagBuilder(BlockTags.AZALEA_ROOT_REPLACEABLE)
			.add(TerrestriaBlocks.VOLCANIC_ROCK.plain.full);

		getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
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

		getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
			.add(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES)
			.add(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES)
			.add(TerrestriaBlocks.JUNGLE_PALM_LEAVES);

		getOrCreateTagBuilder(BlockTags.LEAVES)
			.add(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES)
			.add(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES)
			.add(TerrestriaBlocks.JUNGLE_PALM_LEAVES);

		getOrCreateTagBuilder(BlockTags.MOSS_REPLACEABLE)
			.add(TerrestriaBlocks.VOLCANIC_ROCK.plain.full);

		getOrCreateTagBuilder(BlockTags.OAK_LOGS)
			.addTag(TerrestriaBlockTags.SMALL_OAK_LOGS);

		getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
			.add(Blocks.SMOOTH_SANDSTONE)
			.add(TerrestriaBlocks.ANDISOL.getDirt())
			.add(TerrestriaBlocks.ANDISOL.getGrassBlock())
			.add(TerrestriaBlocks.VOLCANIC_ROCK.plain.full);

		getOrCreateTagBuilder(BlockTags.SAPLINGS)
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

		getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
			.add(TerrestriaBlocks.INDIAN_PAINTBRUSH)
			.add(TerrestriaBlocks.MONSTERAS);


		getOrCreateTagBuilder(TerrestriaBlockTags.BLACK_SAND)
			.add(TerrestriaBlocks.BLACK_SAND);

		getOrCreateTagBuilder(TerrestriaBlockTags.SMALL_OAK_LOGS)
			.add(TerrestriaBlocks.SMALL_OAK_LOG)
			.add(TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG);

		getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
			.addTag(TerrestriaBlockTags.SMALL_OAK_LOGS);


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
	}

	private void addDirt(DirtBlocks dirtBlock) {
		getOrCreateTagBuilder(BlockTags.ANIMALS_SPAWNABLE_ON)
			.add(dirtBlock.getGrassBlock());

		getOrCreateTagBuilder(BlockTags.BIG_DRIPLEAF_PLACEABLE)
			.add(dirtBlock.getFarmland());

		getOrCreateTagBuilder(BlockTags.CONVERTABLE_TO_MUD)
			.add(dirtBlock.getDirt());

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


		getOrCreateTagBuilder(TerrestriaBlockTags.DIRT)
			.add(dirtBlock.getDirt());

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

		getOrCreateTagBuilder(TerrestriaBlockTags.SAND).add(sandBlock);
	}

	@SuppressWarnings("SameParameterValue")
	private void addStone(TagKey<Block> stoneTag, StoneBlocks stoneBlock) {
		FabricTagBuilder stoneBuilder = getOrCreateTagBuilder(stoneTag);
		if (stoneBlock.bricks != null) {
			stoneBuilder.add(stoneBlock.bricks.full);
			addStoneVariant(stoneBlock.bricks);
			getOrCreateTagBuilder(BlockTags.STONE_BRICKS).add(stoneBlock.bricks.full);

			stoneBuilder.add(stoneBlock.chiseledBricks);
			getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(stoneBlock.chiseledBricks);
			getOrCreateTagBuilder(BlockTags.STONE_BRICKS).add(stoneBlock.chiseledBricks);

			stoneBuilder.add(stoneBlock.crackedBricks);
			getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(stoneBlock.crackedBricks);
			getOrCreateTagBuilder(BlockTags.STONE_BRICKS).add(stoneBlock.crackedBricks);
		}
		if (stoneBlock.cobblestone != null) {
			stoneBuilder.add(stoneBlock.cobblestone.full);
			addStoneVariant(stoneBlock.cobblestone);
			getOrCreateTagBuilder(TerrestriaBlockTags.COBBLESTONE).add(stoneBlock.cobblestone.full);
		}
		if (stoneBlock.mossyBricks != null) {
			stoneBuilder.add(stoneBlock.mossyBricks.full);
			getOrCreateTagBuilder(BlockTags.STONE_BRICKS).add(stoneBlock.mossyBricks.full);
			addStoneVariant(stoneBlock.mossyBricks);
		}
		if (stoneBlock.mossyCobblestone != null) {
			stoneBuilder.add(stoneBlock.mossyCobblestone.full);
			addStoneVariant(stoneBlock.mossyCobblestone);
		}
		if (stoneBlock.plain != null) {
			stoneBuilder.add(stoneBlock.plain.full);
			addStoneVariant(stoneBlock.plain);
			getOrCreateTagBuilder(TerrestriaBlockTags.STONE).add(stoneBlock.plain.full);
		}
		if (stoneBlock.smooth != null) {
			stoneBuilder.add(stoneBlock.smooth.full);
			addStoneVariant(stoneBlock.smooth);
			getOrCreateTagBuilder(TerrestriaBlockTags.STONE).add(stoneBlock.smooth.full);
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
		FabricTagBuilder woodBuilder = getOrCreateTagBuilder(logTag);
		woodBuilder
			.add(woodBlock.log)
			.add(woodBlock.strippedLog);
		getOrCreateTagBuilder(TerrestriaBlockTags.STRIPPED_LOGS).add(woodBlock.strippedLog);

		if (woodBlock.hasWood()) {
			woodBuilder
				.add(woodBlock.wood)
				.add(woodBlock.strippedWood);
			getOrCreateTagBuilder(TerrestriaBlockTags.STRIPPED_WOOD).add(woodBlock.strippedWood);
		}

		if (woodBlock.hasQuarterLog()) {
			woodBuilder
				.add(woodBlock.quarterLog)
				.add(woodBlock.strippedQuarterLog);
			getOrCreateTagBuilder(TerrestriaBlockTags.STRIPPED_LOGS).add(woodBlock.strippedQuarterLog);
		}

		getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(woodBlock.fenceGate);
		getOrCreateTagBuilder(BlockTags.LEAVES).add(woodBlock.leaves);
		getOrCreateTagBuilder(BlockTags.PLANKS).add(woodBlock.planks);
		getOrCreateTagBuilder(BlockTags.SLABS).add(woodBlock.slab);
		getOrCreateTagBuilder(BlockTags.STAIRS).add(woodBlock.stairs);
		getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(woodBlock.sign);
		getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(woodBlock.wallSign);
		getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(woodBlock.hangingSign);
		getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(woodBlock.wallHangingSign);
		getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(woodBlock.button);
		getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(woodBlock.door);
		getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(woodBlock.fence);
		getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(woodBlock.pressurePlate);
		getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(woodBlock.slab);
		getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(woodBlock.stairs);
		getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(woodBlock.trapdoor);

		// Adding to FENCE_GATES or any WOODEN tag does this for AXE_MINEABLE.
		getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(woodBlock.leaves);
		if (woodBlock.hasLeafPile()) {
			getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(woodBlock.leafPile);
		}

		// If the log burns, we assume all the logs, planks, and wood burn.
		if (woodBlock.log.getDefaultState().isBurnable()) {
			getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(logTag);
			getOrCreateTagBuilder(TerrestriaBlockTags.PLANKS_THAT_BURN).add(woodBlock.planks);
		}
	}
}
