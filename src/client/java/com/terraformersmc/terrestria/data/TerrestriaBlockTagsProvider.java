package com.terraformersmc.terrestria.data;

import com.terraformersmc.terraform.dirt.api.DirtBlocks;
import com.terraformersmc.terraform.dirt.api.TerraformDirtBlockTags;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneVariantBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import com.terraformersmc.terrestria.tag.TerrestriaBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@NullMarked
public class TerrestriaBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
	protected TerrestriaBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void addTags(HolderLookup.Provider registries) {
		/*
		 * Basic block tags
		 */
		valueLookupBuilder(BlockTags.AZALEA_ROOT_REPLACEABLE)
				.add(TerrestriaBlocks.VOLCANIC_ROCK.plain.full);

		valueLookupBuilder(BlockTags.CONVERTABLE_TO_MUD)
				.add(TerrestriaBlocks.ANDISOL.dirtBlock());

		valueLookupBuilder(BlockTags.EDIBLE_FOR_SHEEP)
				.add(TerrestriaBlocks.AGAVE)
				.add(TerrestriaBlocks.DEAD_GRASS)
				.add(TerrestriaBlocks.MONSTERAS);

		valueLookupBuilder(BlockTags.FLOWER_POTS)
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

		valueLookupBuilder(BlockTags.LEAVES)
				.add(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES)
				.add(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES)
				.add(TerrestriaBlocks.JUNGLE_PALM_LEAVES);

		valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
				.addTag(TerrestriaBlockTags.SMALL_OAK_LOGS);

		valueLookupBuilder(BlockTags.MOSS_REPLACEABLE)
				.add(TerrestriaBlocks.VOLCANIC_ROCK.plain.full);

		valueLookupBuilder(BlockTags.OAK_LOGS)
				.addTag(TerrestriaBlockTags.SMALL_OAK_LOGS);

		valueLookupBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
				.add(Blocks.SMOOTH_SANDSTONE)
				.add(TerrestriaBlocks.ANDISOL.dirtBlock())
				.add(Objects.requireNonNull(TerrestriaBlocks.ANDISOL.grassBlock()))
				.add(TerrestriaBlocks.VOLCANIC_ROCK.plain.full);

		valueLookupBuilder(BlockTags.SAPLINGS)
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

		valueLookupBuilder(BlockTags.SMALL_FLOWERS)
				.add(TerrestriaBlocks.INDIAN_PAINTBRUSH)
				.add(TerrestriaBlocks.MONSTERAS);

		valueLookupBuilder(BlockTags.TRIGGERS_AMBIENT_DESERT_DRY_VEGETATION_BLOCK_SOUNDS)
				.add(TerrestriaBlocks.DEAD_GRASS);

		/*
		 * Conventional block tags
		 */
		valueLookupBuilder(TerrestriaBlockTags.BLACK_SANDS)
				.add(TerrestriaBlocks.VOLCANIC_SAND);

		valueLookupBuilder(ConventionalBlockTags.STRIPPED_LOGS)
				.add(TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG);


		/*
		 * Local block tags
		 */
		valueLookupBuilder(TerrestriaBlockTags.SMALL_OAK_LOGS)
				.add(TerrestriaBlocks.SMALL_OAK_LOG)
				.add(TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG);


		/*
		 * Custom dirt block tags
		 */
		addDirt(TerrestriaBlocks.ANDISOL);

		/*
		 * Custom sand block tags
		 */
		addSand(TerrestriaBlocks.VOLCANIC_SAND);

		/*
		 * Stone building block tags
		 */
		addStone(TerrestriaBlockTags.BASALTS, TerrestriaBlocks.VOLCANIC_ROCK);

		/*
		 * Wood building block tags
		 */
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
		Block dirt = Objects.requireNonNull(dirtBlock.dirtBlock());
		Block dirtPath = Objects.requireNonNull(dirtBlock.dirtPathBlock());
		Block grass = Objects.requireNonNull(dirtBlock.grassBlock());
		Block podzol = Objects.requireNonNull(dirtBlock.podzolBlock());
		Block farm = Objects.requireNonNull(dirtBlock.farmBlock());

		valueLookupBuilder(BlockTags.ANIMALS_SPAWNABLE_ON)
				.add(grass);

		valueLookupBuilder(BlockTags.CANNOT_REPLACE_BELOW_TREE_TRUNK)
				.add(podzol);

		valueLookupBuilder(BlockTags.CONVERTABLE_TO_MUD)
				.add(dirt);

		valueLookupBuilder(BlockTags.DIRT)
				.add(dirt);

		valueLookupBuilder(BlockTags.ENDERMAN_HOLDABLE)
				.add(dirt)
				.add(grass)
				.add(podzol);

		valueLookupBuilder(BlockTags.FOXES_SPAWNABLE_ON)
				.add(grass)
				.add(podzol);

		valueLookupBuilder(BlockTags.FROGS_SPAWNABLE_ON)
				.add(grass);

		valueLookupBuilder(BlockTags.GRASS_BLOCKS)
				.add(grass)
				.add(podzol);

		valueLookupBuilder(BlockTags.GROWS_CROPS)
				.add(farm);

		valueLookupBuilder(BlockTags.HUGE_BROWN_MUSHROOM_CAN_PLACE_ON)
				.add(podzol);

		valueLookupBuilder(BlockTags.HUGE_RED_MUSHROOM_CAN_PLACE_ON)
				.add(podzol);

		valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
				.add(dirt)
				.add(dirtPath)
				.add(farm)
				.add(grass)
				.add(podzol);

		valueLookupBuilder(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT)
				.add(podzol);

		valueLookupBuilder(BlockTags.PARROTS_SPAWNABLE_ON)
				.add(grass);

		valueLookupBuilder(BlockTags.RABBITS_SPAWNABLE_ON)
				.add(grass);

		valueLookupBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK)
				.add(dirt)
				.add(grass)
				.add(podzol);

		valueLookupBuilder(BlockTags.SUPPORT_OVERRIDE_CACTUS_FLOWER)
				.add(farm);

		valueLookupBuilder(BlockTags.SUPPORTS_BIG_DRIPLEAF)
				.add(dirt)
				.add(farm)
				.add(grass)
				.add(podzol);

		valueLookupBuilder(BlockTags.SUPPORTS_CROPS)
				.add(farm);

		valueLookupBuilder(BlockTags.SUPPORTS_DRY_VEGETATION)
				.add(farm);

		valueLookupBuilder(BlockTags.SUPPORTS_VEGETATION)
				.add(farm);

		valueLookupBuilder(BlockTags.VALID_SPAWN)
				.add(grass)
				.add(podzol);

		valueLookupBuilder(BlockTags.WOLVES_SPAWNABLE_ON)
				.add(grass)
				.add(podzol);


		valueLookupBuilder(TerrestriaBlockTags.DIRTS)
				.add(dirt);

		valueLookupBuilder(TerraformDirtBlockTags.FARMLAND)
				.add(farm);

		valueLookupBuilder(TerraformDirtBlockTags.GRASS_BLOCKS)
				.add(grass);

		valueLookupBuilder(TerrestriaBlockTags.PODZOLS)
				.add(podzol);

		valueLookupBuilder(TerraformDirtBlockTags.SOIL)
				.add(dirt)
				.add(grass)
				.add(podzol);
	}

	private void addSand(ColoredFallingBlock sandBlock) {
		valueLookupBuilder(BlockTags.AZALEA_ROOT_REPLACEABLE).add(sandBlock);
		valueLookupBuilder(BlockTags.ENDERMAN_HOLDABLE).add(sandBlock);
		valueLookupBuilder(BlockTags.LUSH_GROUND_REPLACEABLE).add(sandBlock);
		valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL).add(sandBlock);
		valueLookupBuilder(BlockTags.RABBITS_SPAWNABLE_ON).add(sandBlock);
		valueLookupBuilder(BlockTags.SAND).add(sandBlock);
		valueLookupBuilder(BlockTags.SCULK_REPLACEABLE).add(sandBlock);
		valueLookupBuilder(BlockTags.SMELTS_TO_GLASS).add(sandBlock);
		valueLookupBuilder(BlockTags.TRIGGERS_AMBIENT_DESERT_SAND_BLOCK_SOUNDS).add(sandBlock);

		valueLookupBuilder(TerrestriaBlockTags.SANDS).add(sandBlock);
	}

	@SuppressWarnings("SameParameterValue")
	private void addStone(TagKey<Block> stoneTag, StoneBlocks stoneBlock) {
		TagAppender<Block, Block> stoneBuilder = valueLookupBuilder(stoneTag);
		if (stoneBlock.bricks != null) {
			stoneBuilder.add(stoneBlock.bricks.full);
			addStoneVariant(stoneBlock.bricks);
			valueLookupBuilder(BlockTags.STONE_BRICKS).add(stoneBlock.bricks.full);

			stoneBuilder.add(stoneBlock.chiseledBricks);
			valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(stoneBlock.chiseledBricks);
			valueLookupBuilder(BlockTags.STONE_BRICKS).add(stoneBlock.chiseledBricks);

			stoneBuilder.add(stoneBlock.crackedBricks);
			valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(stoneBlock.crackedBricks);
			valueLookupBuilder(BlockTags.STONE_BRICKS).add(stoneBlock.crackedBricks);
		}
		if (stoneBlock.cobblestone != null) {
			stoneBuilder.add(stoneBlock.cobblestone.full);
			addStoneVariant(stoneBlock.cobblestone);
			valueLookupBuilder(ConventionalBlockTags.COBBLESTONES).add(stoneBlock.cobblestone.full);
		}
		if (stoneBlock.mossyBricks != null) {
			stoneBuilder.add(stoneBlock.mossyBricks.full);
			valueLookupBuilder(BlockTags.STONE_BRICKS).add(stoneBlock.mossyBricks.full);
			addStoneVariant(stoneBlock.mossyBricks);
		}
		if (stoneBlock.mossyCobblestone != null) {
			stoneBuilder.add(stoneBlock.mossyCobblestone.full);
			addStoneVariant(stoneBlock.mossyCobblestone);
		}
		if (stoneBlock.plain != null) {
			stoneBuilder.add(stoneBlock.plain.full);
			addStoneVariant(stoneBlock.plain);
			valueLookupBuilder(ConventionalBlockTags.STONES).add(stoneBlock.plain.full);
		}
		if (stoneBlock.smooth != null) {
			stoneBuilder.add(stoneBlock.smooth.full);
			addStoneVariant(stoneBlock.smooth);
			valueLookupBuilder(ConventionalBlockTags.STONES).add(stoneBlock.smooth.full);
		}

		valueLookupBuilder(BlockTags.STONE_BUTTONS).add(stoneBlock.button);
		valueLookupBuilder(BlockTags.STONE_PRESSURE_PLATES).add(stoneBlock.pressurePlate);
	}

	private void addStoneVariant(StoneVariantBlocks stoneVariantBlock) {
		valueLookupBuilder(BlockTags.SLABS).add(stoneVariantBlock.slab);
		valueLookupBuilder(BlockTags.STAIRS).add(stoneVariantBlock.stairs);
		valueLookupBuilder(BlockTags.WALLS).add(stoneVariantBlock.wall);

		valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(stoneVariantBlock.full)
				.add(stoneVariantBlock.slab)
				.add(stoneVariantBlock.stairs);
				// Adding to WALLS does this for PICKAXE_MINEABLE.
	}

	private void addWood(TagKey<Block> logTag, WoodBlocks woodBlock) {
		TagAppender<Block, Block> woodBuilder = valueLookupBuilder(logTag);
		woodBuilder
				.add(woodBlock.log)
				.add(woodBlock.strippedLog);
		valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(woodBlock.log);
		valueLookupBuilder(ConventionalBlockTags.STRIPPED_LOGS).add(woodBlock.strippedLog);

		if (woodBlock.hasWood()) {
			woodBuilder
					.add(woodBlock.wood)
					.add(woodBlock.strippedWood);
			valueLookupBuilder(ConventionalBlockTags.STRIPPED_WOODS).add(woodBlock.strippedWood);
		}

		if (woodBlock.hasQuarterLog()) {
			woodBuilder
					.add(woodBlock.quarterLog)
					.add(woodBlock.strippedQuarterLog);
			valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(woodBlock.quarterLog);
			valueLookupBuilder(ConventionalBlockTags.STRIPPED_LOGS).add(woodBlock.strippedQuarterLog);
		}

		valueLookupBuilder(BlockTags.FENCE_GATES).add(woodBlock.fenceGate);
		valueLookupBuilder(BlockTags.LEAVES).add(woodBlock.leaves);
		valueLookupBuilder(BlockTags.PLANKS).add(woodBlock.planks);
		valueLookupBuilder(BlockTags.STANDING_SIGNS).add(woodBlock.sign);
		valueLookupBuilder(BlockTags.WALL_SIGNS).add(woodBlock.wallSign);
		valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS).add(woodBlock.hangingSign);
		valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS).add(woodBlock.wallHangingSign);
		valueLookupBuilder(BlockTags.WOODEN_BUTTONS).add(woodBlock.button);
		valueLookupBuilder(BlockTags.WOODEN_DOORS).add(woodBlock.door);
		valueLookupBuilder(BlockTags.WOODEN_FENCES).add(woodBlock.fence);
		valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(woodBlock.pressurePlate);
		valueLookupBuilder(BlockTags.WOODEN_SHELVES).add(woodBlock.shelf);
		valueLookupBuilder(BlockTags.WOODEN_SLABS).add(woodBlock.slab);
		valueLookupBuilder(BlockTags.WOODEN_STAIRS).add(woodBlock.stairs);
		valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS).add(woodBlock.trapdoor);

		// Adding to FENCE_GATES, PLANKS, or any SIGNS or WOODEN tag does this for AXE_MINEABLE.
		// Adding to LEAVES does this for HOE_MINEABLE.
		if (woodBlock.hasLeafPile()) {
			valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE).add(woodBlock.leafPile);
		}

		// If the log burns, we assume all the logs, planks, and wood burn.
		if (woodBlock.log.defaultBlockState().ignitedByLava()) {
			valueLookupBuilder(BlockTags.LOGS_THAT_BURN).addTag(logTag);
			valueLookupBuilder(TerrestriaBlockTags.PLANKS_THAT_BURN).add(woodBlock.planks);
		}
	}

	@Override
	public String getName() {
		return "Terrestria Block Tags";
	}
}
