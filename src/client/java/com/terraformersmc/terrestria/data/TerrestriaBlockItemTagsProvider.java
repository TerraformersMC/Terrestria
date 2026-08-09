package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.init.TerrestriaBlockItemIds;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import com.terraformersmc.terrestria.tag.TerrestriaBlockItemTags;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.references.BlockItemId;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import org.jspecify.annotations.NullMarked;

import java.util.function.Function;

@NullMarked
public class TerrestriaBlockItemTagsProvider extends BlockItemTagsProvider {
	protected TerrestriaBlockItemTagsProvider(Function<BlockItemTagId, CombinedAppender> tagSupplier) {
		super(tagSupplier);
	}

	@Override
	protected void run() {
		/*
		 * Basic block tags
		 */
		tag(BlockItemTags.LEAVES)
			.add(TerrestriaBlockItemIds.DARK_JAPANESE_MAPLE_LEAVES)
			.add(TerrestriaBlockItemIds.JAPANESE_MAPLE_SHRUB_LEAVES)
			.add(TerrestriaBlockItemIds.JUNGLE_PALM_LEAVES);

		tag(BlockItemTags.LOGS_THAT_BURN)
			.addTag(TerrestriaBlockItemTags.SMALL_OAK_LOGS);

		tag(BlockItemTags.OAK_LOGS)
			.addTag(TerrestriaBlockItemTags.SMALL_OAK_LOGS);

		tag(BlockItemTags.SAPLINGS)
			.add(TerrestriaBlockItemIds.BRYCE_SAPLING)
			.add(TerrestriaBlockItemIds.CYPRESS_SAPLING)
			.add(TerrestriaBlockItemIds.DARK_JAPANESE_MAPLE_SAPLING)
			.add(TerrestriaBlockItemIds.HEMLOCK_SAPLING)
			.add(TerrestriaBlockItemIds.JAPANESE_MAPLE_SAPLING)
			.add(TerrestriaBlockItemIds.JAPANESE_MAPLE_SHRUB_SAPLING)
			.add(TerrestriaBlockItemIds.JUNGLE_PALM_SAPLING)
			.add(TerrestriaBlockItemIds.RAINBOW_EUCALYPTUS_SAPLING)
			.add(TerrestriaBlockItemIds.REDWOOD_SAPLING)
			.add(TerrestriaBlockItemIds.RUBBER_SAPLING)
			.add(TerrestriaBlockItemIds.SAKURA_SAPLING)
			.add(TerrestriaBlockItemIds.SAGUARO_CACTUS_SAPLING)
			.add(TerrestriaBlockItemIds.WILLOW_SAPLING)
			.add(TerrestriaBlockItemIds.YUCCA_PALM_SAPLING);

		tag(BlockItemTags.SMALL_FLOWERS)
			.add(TerrestriaBlockItemIds.INDIAN_PAINTBRUSH)
			.add(TerrestriaBlockItemIds.MONSTERAS);

		/*
		 * Conventional block tags
		 */
		tag(TerrestriaBlockItemTags.BLACK_SANDS)
			.add(TerrestriaBlockItemIds.VOLCANIC_SAND);

		tag(TerrestriaBlockItemTags.STRIPPED_LOGS)
			.add(TerrestriaBlockItemIds.STRIPPED_SMALL_OAK_LOG);


		/*
		 * Local block tags
		 */
		tag(TerrestriaBlockItemTags.SMALL_OAK_LOGS)
			.add(TerrestriaBlockItemIds.SMALL_OAK_LOG)
			.add(TerrestriaBlockItemIds.STRIPPED_SMALL_OAK_LOG);


		/*
		 * Custom dirt block tags
		 */
		addDirt(TerrestriaBlockItemIds.ANDISOL);

		/*
		 * Custom sand block tags
		 */
		addSand(TerrestriaBlockItemIds.VOLCANIC_SAND);

		/*
		 * Stone building block tags
		 */
		addStone(TerrestriaBlockItemTags.BASALTS, TerrestriaBlocks.VOLCANIC_ROCK, TerrestriaBlockItemIds.VOLCANIC_ROCK);

		/*
		 * Wood building block tags
		 */
		addWood(TerrestriaBlockItemTags.CYPRESS_LOGS, TerrestriaBlocks.CYPRESS, TerrestriaBlockItemIds.CYPRESS);
		addWood(TerrestriaBlockItemTags.HEMLOCK_LOGS, TerrestriaBlocks.HEMLOCK, TerrestriaBlockItemIds.HEMLOCK);
		addWood(TerrestriaBlockItemTags.JAPANESE_MAPLE_LOGS, TerrestriaBlocks.JAPANESE_MAPLE, TerrestriaBlockItemIds.JAPANESE_MAPLE);
		addWood(TerrestriaBlockItemTags.RAINBOW_EUCALYPTUS_LOGS, TerrestriaBlocks.RAINBOW_EUCALYPTUS, TerrestriaBlockItemIds.RAINBOW_EUCALYPTUS);
		addWood(TerrestriaBlockItemTags.REDWOOD_LOGS, TerrestriaBlocks.REDWOOD, TerrestriaBlockItemIds.REDWOOD);
		addWood(TerrestriaBlockItemTags.RUBBER_LOGS, TerrestriaBlocks.RUBBER, TerrestriaBlockItemIds.RUBBER);
		addWood(TerrestriaBlockItemTags.SAKURA_LOGS, TerrestriaBlocks.SAKURA, TerrestriaBlockItemIds.SAKURA);
		addWood(TerrestriaBlockItemTags.WILLOW_LOGS, TerrestriaBlocks.WILLOW, TerrestriaBlockItemIds.WILLOW);
		addWood(TerrestriaBlockItemTags.YUCCA_PALM_LOGS, TerrestriaBlocks.YUCCA_PALM, TerrestriaBlockItemIds.YUCCA_PALM);
	}

	@SuppressWarnings("SameParameterValue")
	private void addDirt(TerrestriaBlockItemIds.DirtBlockItemIds dirtBlock) {
		tag(BlockItemTags.DIRT)
			.add(dirtBlock.dirtBlock());

		tag(BlockItemTags.GRASS_BLOCKS)
			.add(dirtBlock.grassBlock())
			.add(dirtBlock.podzolBlock());


		tag(TerrestriaBlockItemTags.DIRTS)
			.add(dirtBlock.dirtBlock());

		tag(TerrestriaBlockItemTags.PODZOLS)
			.add(dirtBlock.podzolBlock());
	}

	@SuppressWarnings("SameParameterValue")
	private void addSand(BlockItemId sandBlockId) {
		tag(BlockItemTags.SAND).add(sandBlockId);
		tag(BlockItemTags.SMELTS_TO_GLASS).add(sandBlockId);

		tag(TerrestriaBlockItemTags.SANDS).add(sandBlockId);
	}

	@SuppressWarnings("SameParameterValue")
	private void addStone(BlockItemTagId stoneTag, StoneBlocks stoneBlock, TerrestriaBlockItemIds.StoneBlockItemIds stoneBlockIds) {
		CombinedAppender stoneBuilder = tag(stoneTag);
		if (stoneBlock.bricks != null) {
			stoneBuilder.add(stoneBlockIds.bricks().full());
			addStoneVariant(stoneBlockIds.bricks());
			tag(BlockItemTags.STONE_BRICKS).add(stoneBlockIds.bricks().full());

			stoneBuilder.add(stoneBlockIds.chiseledBricks());
			tag(BlockItemTags.STONE_BRICKS).add(stoneBlockIds.chiseledBricks());

			stoneBuilder.add(stoneBlockIds.crackedBricks());
			tag(BlockItemTags.STONE_BRICKS).add(stoneBlockIds.crackedBricks());
		}
		if (stoneBlock.cobblestone != null) {
			stoneBuilder.add(stoneBlockIds.cobblestone().full());
			addStoneVariant(stoneBlockIds.cobblestone());
			tag(TerrestriaBlockItemTags.COBBLESTONES).add(stoneBlockIds.cobblestone().full());
		}
		if (stoneBlock.mossyBricks != null) {
			stoneBuilder.add(stoneBlockIds.mossyBricks().full());
			tag(BlockItemTags.STONE_BRICKS).add(stoneBlockIds.mossyBricks().full());
			addStoneVariant(stoneBlockIds.mossyBricks());
		}
		if (stoneBlock.mossyCobblestone != null) {
			stoneBuilder.add(stoneBlockIds.mossyCobblestone().full());
			addStoneVariant(stoneBlockIds.mossyCobblestone());
		}
		if (stoneBlock.plain != null) {
			stoneBuilder.add(stoneBlockIds.plain().full());
			addStoneVariant(stoneBlockIds.plain());
			tag(TerrestriaBlockItemTags.STONES).add(stoneBlockIds.plain().full());
		}
		if (stoneBlock.smooth != null) {
			stoneBuilder.add(stoneBlockIds.smooth().full());
			addStoneVariant(stoneBlockIds.smooth());
			tag(TerrestriaBlockItemTags.STONES).add(stoneBlockIds.smooth().full());
		}

		tag(BlockItemTags.STONE_BUTTONS).add(stoneBlockIds.button());
	}

	private void addStoneVariant(TerrestriaBlockItemIds.StoneVariantBlockItemIds stoneVariantBlockIds) {
		tag(BlockItemTags.SLABS).add(stoneVariantBlockIds.slab());
		tag(BlockItemTags.STAIRS).add(stoneVariantBlockIds.stairs());
		tag(BlockItemTags.WALLS).add(stoneVariantBlockIds.wall());
	}

	private void addWood(BlockItemTagId logTag, WoodBlocks woodBlock, TerrestriaBlockItemIds.WoodBlockItemIds woodBlockIds) {
		CombinedAppender woodBuilder = tag(logTag);
		woodBuilder
			.add(woodBlockIds.log())
			.add(woodBlockIds.strippedLog());
		tag(TerrestriaBlockItemTags.STRIPPED_LOGS).add(woodBlockIds.strippedLog());

		if (woodBlock.hasWood()) {
			woodBuilder
				.add(woodBlockIds.wood())
				.add(woodBlockIds.strippedWood());
			tag(TerrestriaBlockItemTags.STRIPPED_WOODS).add(woodBlockIds.strippedWood());
		}

		if (woodBlock.hasQuarterLog()) {
			woodBuilder
				.add(woodBlockIds.quarterLog())
				.add(woodBlockIds.strippedQuarterLog());
			tag(TerrestriaBlockItemTags.STRIPPED_LOGS).add(woodBlockIds.strippedQuarterLog());
		}

		tag(BlockItemTags.FENCE_GATES).add(woodBlockIds.fenceGate());
		tag(BlockItemTags.LEAVES).add(woodBlockIds.leaves());
		tag(BlockItemTags.PLANKS).add(woodBlockIds.planks());
		tag(BlockItemTags.WOODEN_BUTTONS).add(woodBlockIds.button());
		tag(BlockItemTags.WOODEN_DOORS).add(woodBlockIds.door());
		tag(BlockItemTags.WOODEN_FENCES).add(woodBlockIds.fence());
		tag(BlockItemTags.WOODEN_PRESSURE_PLATES).add(woodBlockIds.pressurePlate());
		tag(BlockItemTags.WOODEN_SHELVES).add(woodBlockIds.shelf());
		tag(BlockItemTags.WOODEN_SLABS).add(woodBlockIds.slab());
		tag(BlockItemTags.WOODEN_STAIRS).add(woodBlockIds.stairs());
		tag(BlockItemTags.WOODEN_TRAPDOORS).add(woodBlockIds.trapdoor());

		// If the log burns, we assume all the logs, planks, and wood burn.
		if (woodBlock.log.defaultBlockState().ignitedByLava()) {
			tag(BlockItemTags.LOGS_THAT_BURN).addTag(logTag);
			tag(TerrestriaBlockItemTags.PLANKS_THAT_BURN).add(woodBlockIds.planks());
		}
	}
}
