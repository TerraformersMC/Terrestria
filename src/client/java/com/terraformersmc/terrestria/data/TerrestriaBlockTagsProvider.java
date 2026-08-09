package com.terraformersmc.terrestria.data;

import com.terraformersmc.terraform.dirt.api.TerraformDirtBlockTags;
import com.terraformersmc.terrestria.init.TerrestriaBlockIds;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
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
		tag(BlockTags.AZALEA_ROOT_REPLACEABLE)
				.add(TerrestriaBlockIds.VOLCANIC_ROCK.plain().full());

		tag(BlockTags.CONVERTABLE_TO_MUD)
				.add(TerrestriaBlockIds.ANDISOL.dirtBlock());

		tag(BlockTags.EDIBLE_FOR_SHEEP)
				.add(TerrestriaBlockIds.AGAVE)
				.add(TerrestriaBlockIds.DEAD_GRASS)
				.add(TerrestriaBlockIds.MONSTERAS);

		tag(BlockTags.FLOWER_POTS)
				.add(TerrestriaBlockIds.POTTED_AGAVE)
				.add(TerrestriaBlockIds.POTTED_ALOE_VERA)
				.add(TerrestriaBlockIds.POTTED_BRYCE_SAPLING)
				.add(TerrestriaBlockIds.POTTED_CYPRESS_SAPLING)
				.add(TerrestriaBlockIds.POTTED_DARK_JAPANESE_MAPLE_SAPLING)
				.add(TerrestriaBlockIds.POTTED_HEMLOCK_SAPLING)
				.add(TerrestriaBlockIds.POTTED_INDIAN_PAINTBRUSH)
				.add(TerrestriaBlockIds.POTTED_JAPANESE_MAPLE_SAPLING)
				.add(TerrestriaBlockIds.POTTED_JAPANESE_MAPLE_SHRUB_SAPLING)
				.add(TerrestriaBlockIds.POTTED_JUNGLE_PALM_SAPLING)
				.add(TerrestriaBlockIds.POTTED_MONSTERAS)
				.add(TerrestriaBlockIds.POTTED_RAINBOW_EUCALYPTUS_SAPLING)
				.add(TerrestriaBlockIds.POTTED_REDWOOD_SAPLING)
				.add(TerrestriaBlockIds.POTTED_RUBBER_SAPLING)
				.add(TerrestriaBlockIds.POTTED_SAGUARO_CACTUS_SAPLING)
				.add(TerrestriaBlockIds.POTTED_SAKURA_SAPLING)
				.add(TerrestriaBlockIds.POTTED_TINY_CACTUS)
				.add(TerrestriaBlockIds.POTTED_WILLOW_SAPLING)
				.add(TerrestriaBlockIds.POTTED_YUCCA_PALM_SAPLING);

		tag(BlockTags.MOSS_REPLACEABLE)
				.add(TerrestriaBlockIds.VOLCANIC_ROCK.plain().full());

		tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
				.add(BlockItemIds.SMOOTH_SANDSTONE.block())
				.add(TerrestriaBlockIds.ANDISOL.dirtBlock())
				.add(Objects.requireNonNull(TerrestriaBlockIds.ANDISOL.grassBlock()))
				.add(TerrestriaBlockIds.VOLCANIC_ROCK.plain().full());

		tag(BlockTags.OVERWORLD_NATURAL_LOGS)
				.add(TerrestriaBlockIds.SMALL_OAK_LOG);

		tag(BlockTags.TRIGGERS_AMBIENT_DESERT_DRY_VEGETATION_BLOCK_SOUNDS)
				.add(TerrestriaBlockIds.DEAD_GRASS);


		/*
		 * Custom dirt block tags
		 */
		addDirt(TerrestriaBlockIds.ANDISOL);

		/*
		 * Custom sand block tags
		 */
		addSand(TerrestriaBlockIds.VOLCANIC_SAND);

		/*
		 * Stone building block tags
		 */
		addStone(TerrestriaBlocks.VOLCANIC_ROCK, TerrestriaBlockIds.VOLCANIC_ROCK);

		/*
		 * Wood building block tags
		 */
		addWood(TerrestriaBlocks.CYPRESS, TerrestriaBlockIds.CYPRESS);
		addWood(TerrestriaBlocks.HEMLOCK, TerrestriaBlockIds.HEMLOCK);
		addWood(TerrestriaBlocks.JAPANESE_MAPLE, TerrestriaBlockIds.JAPANESE_MAPLE);
		addWood(TerrestriaBlocks.RAINBOW_EUCALYPTUS, TerrestriaBlockIds.RAINBOW_EUCALYPTUS);
		addWood(TerrestriaBlocks.REDWOOD, TerrestriaBlockIds.REDWOOD);
		addWood(TerrestriaBlocks.RUBBER, TerrestriaBlockIds.RUBBER);
		addWood(TerrestriaBlocks.SAKURA, TerrestriaBlockIds.SAKURA);
		addWood(TerrestriaBlocks.WILLOW, TerrestriaBlockIds.WILLOW);
		addWood(TerrestriaBlocks.YUCCA_PALM, TerrestriaBlockIds.YUCCA_PALM);

		/*
		 * Run BlockItem block tags
		 */
		new TerrestriaBlockItemTagsProvider(tagId -> BlockItemTagsProvider.wrapForBlocks(this.tag(tagId.block()))).run();
	}

	@SuppressWarnings("SameParameterValue")
	private void addDirt(TerrestriaBlockIds.DirtBlockIds dirtBlock) {
		tag(BlockTags.ANIMALS_SPAWNABLE_ON)
				.add(dirtBlock.grassBlock());

		tag(BlockTags.CANNOT_REPLACE_BELOW_TREE_TRUNK)
				.add(dirtBlock.podzolBlock());

		tag(BlockTags.CONVERTABLE_TO_MUD)
				.add(dirtBlock.dirtBlock());

		tag(BlockTags.ENDERMAN_HOLDABLE)
				.add(dirtBlock.dirtBlock())
				.add(dirtBlock.grassBlock())
				.add(dirtBlock.podzolBlock());

		tag(BlockTags.FOXES_SPAWNABLE_ON)
				.add(dirtBlock.grassBlock())
				.add(dirtBlock.podzolBlock());

		tag(BlockTags.FROGS_SPAWNABLE_ON)
				.add(dirtBlock.grassBlock());

		tag(BlockTags.GROWS_CROPS)
				.add(dirtBlock.farmBlock());

		tag(BlockTags.HUGE_BROWN_MUSHROOM_CAN_PLACE_ON)
				.add(dirtBlock.podzolBlock());

		tag(BlockTags.HUGE_RED_MUSHROOM_CAN_PLACE_ON)
				.add(dirtBlock.podzolBlock());

		tag(BlockTags.MINEABLE_WITH_SHOVEL)
				.add(dirtBlock.dirtBlock())
				.add(dirtBlock.dirtPathBlock())
				.add(dirtBlock.farmBlock())
				.add(dirtBlock.grassBlock())
				.add(dirtBlock.podzolBlock());

		tag(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT)
				.add(dirtBlock.podzolBlock());

		tag(BlockTags.PARROTS_SPAWNABLE_ON)
				.add(dirtBlock.grassBlock());

		tag(BlockTags.RABBITS_SPAWNABLE_ON)
				.add(dirtBlock.grassBlock());

		tag(BlockTags.SNIFFER_DIGGABLE_BLOCK)
				.add(dirtBlock.dirtBlock())
				.add(dirtBlock.grassBlock())
				.add(dirtBlock.podzolBlock());

		tag(BlockTags.SUPPORT_OVERRIDE_CACTUS_FLOWER)
				.add(dirtBlock.farmBlock());

		tag(BlockTags.SUPPORTS_BIG_DRIPLEAF)
				.add(dirtBlock.dirtBlock())
				.add(dirtBlock.farmBlock())
				.add(dirtBlock.grassBlock())
				.add(dirtBlock.podzolBlock());

		tag(BlockTags.SUPPORTS_CROPS)
				.add(dirtBlock.farmBlock());

		tag(BlockTags.SUPPORTS_DRY_VEGETATION)
				.add(dirtBlock.farmBlock());

		tag(BlockTags.SUPPORTS_VEGETATION)
				.add(dirtBlock.farmBlock());

		tag(BlockTags.VALID_SPAWN)
				.add(dirtBlock.grassBlock())
				.add(dirtBlock.podzolBlock());

		tag(BlockTags.WOLVES_SPAWNABLE_ON)
				.add(dirtBlock.grassBlock())
				.add(dirtBlock.podzolBlock());


		tag(TerraformDirtBlockTags.FARMLAND)
				.add(dirtBlock.farmBlock());

		tag(TerraformDirtBlockTags.GRASS_BLOCKS)
				.add(dirtBlock.grassBlock());

		tag(TerraformDirtBlockTags.SOIL)
				.add(dirtBlock.dirtBlock())
				.add(dirtBlock.grassBlock())
				.add(dirtBlock.podzolBlock());
	}

	@SuppressWarnings("SameParameterValue")
	private void addSand(ResourceKey<Block> sandBlockId) {
		tag(BlockTags.AZALEA_ROOT_REPLACEABLE).add(sandBlockId);
		tag(BlockTags.ENDERMAN_HOLDABLE).add(sandBlockId);
		tag(BlockTags.LUSH_GROUND_REPLACEABLE).add(sandBlockId);
		tag(BlockTags.MINEABLE_WITH_SHOVEL).add(sandBlockId);
		tag(BlockTags.RABBITS_SPAWNABLE_ON).add(sandBlockId);
		tag(BlockTags.SCULK_REPLACEABLE).add(sandBlockId);
		tag(BlockTags.TRIGGERS_AMBIENT_DESERT_SAND_BLOCK_SOUNDS).add(sandBlockId);
	}

	@SuppressWarnings("SameParameterValue")
	private void addStone(StoneBlocks stoneBlock, TerrestriaBlockIds.StoneBlockIds stoneBlockIds) {
		if (stoneBlock.bricks != null) {
			addStoneVariant(stoneBlockIds.bricks());

			tag(BlockTags.MINEABLE_WITH_PICKAXE).add(stoneBlockIds.chiseledBricks());
			tag(BlockTags.MINEABLE_WITH_PICKAXE).add(stoneBlockIds.crackedBricks());
		}
		if (stoneBlock.cobblestone != null) {
			addStoneVariant(stoneBlockIds.cobblestone());
		}
		if (stoneBlock.mossyBricks != null) {
			addStoneVariant(stoneBlockIds.mossyBricks());
		}
		if (stoneBlock.mossyCobblestone != null) {
			addStoneVariant(stoneBlockIds.mossyCobblestone());
		}
		if (stoneBlock.plain != null) {
			addStoneVariant(stoneBlockIds.plain());
		}
		if (stoneBlock.smooth != null) {
			addStoneVariant(stoneBlockIds.smooth());
		}

		tag(BlockTags.STONE_PRESSURE_PLATES).add(stoneBlockIds.pressurePlate());
	}

	private void addStoneVariant(TerrestriaBlockIds.StoneVariantBlockIds stoneVariantBlockIds) {
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(stoneVariantBlockIds.full())
				.add(stoneVariantBlockIds.slab())
				.add(stoneVariantBlockIds.stairs());
				// Adding to WALLS does this for PICKAXE_MINEABLE.
	}

	private void addWood(WoodBlocks woodBlock, TerrestriaBlockIds.WoodBlockIds woodBlockIds) {
		tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(woodBlockIds.log());

		if (woodBlock.hasQuarterLog()) {
			tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(woodBlockIds.quarterLog());
		}

		tag(BlockTags.STANDING_SIGNS).add(woodBlockIds.sign());
		tag(BlockTags.WALL_SIGNS).add(woodBlockIds.wallSign());
		tag(BlockTags.CEILING_HANGING_SIGNS).add(woodBlockIds.hangingSign());
		tag(BlockTags.WALL_HANGING_SIGNS).add(woodBlockIds.wallHangingSign());

		// Adding to FENCE_GATES, PLANKS, or any SIGNS or WOODEN tag does this for AXE_MINEABLE.
		// Adding to LEAVES does this for HOE_MINEABLE.
		if (woodBlock.hasLeafPile()) {
			tag(BlockTags.MINEABLE_WITH_HOE).add(woodBlockIds.leafPile());
		}
	}

	@Override
	public String getName() {
		return "Terrestria Block Tags";
	}
}
