package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

@SuppressWarnings("unused")
public class TerrestriaBlockItemIds {
	public static WoodBlockItemIds REDWOOD = WoodBlockItemIds.of(id("redwood"));
	public static WoodBlockItemIds HEMLOCK = WoodBlockItemIds.of(id("hemlock"));
	public static WoodBlockItemIds RUBBER = WoodBlockItemIds.of(id("rubber"));
	public static WoodBlockItemIds CYPRESS = WoodBlockItemIds.of(id("cypress"));
	public static WoodBlockItemIds WILLOW = WoodBlockItemIds.of(id("willow"));
	public static WoodBlockItemIds JAPANESE_MAPLE = WoodBlockItemIds.of(id("japanese_maple"));
	public static WoodBlockItemIds RAINBOW_EUCALYPTUS = WoodBlockItemIds.of(id("rainbow_eucalyptus"));
	public static WoodBlockItemIds SAKURA = WoodBlockItemIds.of(id("sakura"));
	public static WoodBlockItemIds YUCCA_PALM = WoodBlockItemIds.of(id("yucca_palm"));

	public static BlockItemId SMALL_OAK_LOG = create("small_oak_log");
	public static BlockItemId STRIPPED_SMALL_OAK_LOG = create("stripped_small_oak_log");
	public static BlockItemId SAGUARO_CACTUS = create("saguaro_cactus");

	public static BlockItemId JAPANESE_MAPLE_SHRUB_LEAVES = create("japanese_maple_shrub_leaves");
	public static BlockItemId DARK_JAPANESE_MAPLE_LEAVES = create("dark_japanese_maple_leaves");
	public static BlockItemId JUNGLE_PALM_LEAVES = create("jungle_palm_leaves");

	public static BlockItemId CATTAIL = create("cattail");

	public static BlockItemId BRYCE_SAPLING = create("bryce_sapling");
	public static BlockItemId REDWOOD_SAPLING = create("redwood_sapling");
	public static BlockItemId HEMLOCK_SAPLING = create("hemlock_sapling");
	public static BlockItemId RUBBER_SAPLING = create("rubber_sapling");
	public static BlockItemId CYPRESS_SAPLING = create("cypress_sapling");
	public static BlockItemId WILLOW_SAPLING = create("willow_sapling");
	public static BlockItemId JAPANESE_MAPLE_SAPLING = create("japanese_maple_sapling");
	public static BlockItemId JAPANESE_MAPLE_SHRUB_SAPLING = create("japanese_maple_shrub_sapling");
	public static BlockItemId DARK_JAPANESE_MAPLE_SAPLING = create("dark_japanese_maple_sapling");
	public static BlockItemId RAINBOW_EUCALYPTUS_SAPLING = create("rainbow_eucalyptus_sapling");
	public static BlockItemId SAKURA_SAPLING = create("sakura_sapling");
	public static BlockItemId JUNGLE_PALM_SAPLING = create("jungle_palm_sapling");
	public static BlockItemId SAGUARO_CACTUS_SAPLING = create("saguaro_cactus_sapling");
	public static BlockItemId YUCCA_PALM_SAPLING = create("yucca_palm_sapling");

	public static StoneBlockItemIds VOLCANIC_ROCK = StoneBlockItemIds.of(id("volcanic_rock"));
	public static BlockItemId VOLCANIC_SAND = create("volcanic_sand");
	public static DirtBlockItemIds ANDISOL = DirtBlockItemIds.of(id("andisol"));
	public static BlockItemId INDIAN_PAINTBRUSH = create("indian_paintbrush");
	public static BlockItemId MONSTERAS = create("monsteras");

	public static BlockItemId TINY_CACTUS = create("tiny_cactus");
	public static BlockItemId AGAVE = create("agave");
	public static BlockItemId ALOE_VERA = create("aloe_vera");
	public static BlockItemId DEAD_GRASS = create("dead_grass");


	public record DirtBlockItemIds(BlockItemId dirtBlock, BlockItemId dirtPathBlock, BlockItemId farmBlock, BlockItemId grassBlock, BlockItemId podzolBlock) {
		public static DirtBlockItemIds of(Identifier id) {
			return new DirtBlockItemIds(
				create(id),
				create(id.withSuffix("_dirt_path")),
				create(id.withSuffix("_farmland")),
				create(id.withSuffix("_grass_block")),
				create(id.withSuffix("_podzol"))
			);
		}
	}

	public record StoneBlockItemIds(StoneVariantBlockItemIds plain, StoneVariantBlockItemIds smooth, StoneVariantBlockItemIds cobblestone, StoneVariantBlockItemIds mossyCobblestone, StoneVariantBlockItemIds bricks, StoneVariantBlockItemIds mossyBricks, BlockItemId button, BlockItemId pressurePlate, BlockItemId chiseledBricks, BlockItemId crackedBricks) {
		public static StoneBlockItemIds of(Identifier id) {
			Identifier brickId = id.withSuffix("_brick");
			Identifier bricksId = id.withSuffix("_bricks");

			return new StoneBlockItemIds(
				StoneVariantBlockItemIds.of(id),
				StoneVariantBlockItemIds.of(id.withPrefix("smooth_")),
				StoneVariantBlockItemIds.of(id.withSuffix("_cobblestone")),
				StoneVariantBlockItemIds.of(id.withPath(name -> "mossy_" + name + "_cobblestone")),
				StoneVariantBlockItemIds.of(bricksId, brickId),
				StoneVariantBlockItemIds.of(bricksId.withPrefix("mossy_"), brickId.withPrefix("mossy_")),
				create(id.withSuffix("_button")),
				create(id.withSuffix("_pressure_plate")),
				create(bricksId.withPrefix("chiseled_")),
				create(bricksId.withPrefix("cracked_"))
			);
		}
	}

	public record StoneVariantBlockItemIds(BlockItemId full, BlockItemId slab, BlockItemId stairs, BlockItemId wall) {
		public static StoneVariantBlockItemIds of(Identifier id, Identifier shapedId) {
			return new StoneVariantBlockItemIds(
				create(id),
				create(shapedId.withSuffix("_slab")),
				create(shapedId.withSuffix("_stairs")),
				create(shapedId.withSuffix("_wall"))
			);
		}

		public static StoneVariantBlockItemIds of(Identifier id) {
			return of(id, id);
		}
	}

	public record WoodBlockItemIds(BlockItemId log, BlockItemId quarterLog, BlockItemId wood, BlockItemId leaves, BlockItemId leafPile, BlockItemId planks, BlockItemId slab, BlockItemId stairs, BlockItemId fence, BlockItemId fenceGate, BlockItemId door, BlockItemId button, BlockItemId pressurePlate, BlockItemId sign, BlockItemId wallSign, BlockItemId hangingSign, BlockItemId wallHangingSign, BlockItemId trapdoor, BlockItemId shelf, BlockItemId strippedLog, BlockItemId strippedQuarterLog, BlockItemId strippedWood) {
		public static WoodBlockItemIds of(Identifier id) {
			return new WoodBlockItemIds(
				create(id.withSuffix("_log")),
				create(id.withSuffix("_quarter_log")),
				create(id.withSuffix("_wood")),
				create(id.withSuffix("_leaves")),
				create(id.withSuffix("_leaf_pile")),
				create(id.withSuffix("_planks")),
				create(id.withSuffix("_slab")),
				create(id.withSuffix("_stairs")),
				create(id.withSuffix("_fence")),
				create(id.withSuffix("_fence_gate")),
				create(id.withSuffix("_door")),
				create(id.withSuffix("_button")),
				create(id.withSuffix("_pressure_plate")),
				create(id.withSuffix("_sign")),
				create(id.withSuffix("_wall_sign")),
				create(id.withSuffix("_hanging_sign")),
				create(id.withSuffix("_wall_hanging_sign")),
				create(id.withSuffix("_trapdoor")),
				create(id.withSuffix("_shelf")),
				create(id.withPath(name -> "stripped_" + name + "_log")),
				create(id.withPath(name -> "stripped_" + name + "_quarter_log")),
				create(id.withPath(name -> "stripped_" + name + "_wood"))
			);
		}
	}


	public static BlockItemId create(final Identifier blockId, final Identifier itemId) {
		return BlockItemId.create(blockId, itemId);
	}

	public static BlockItemId create(final String blockName, final String itemName) {
		return BlockItemId.create(id(blockName), id(itemName));
	}

	public static BlockItemId create(final Identifier id) {
		return BlockItemId.create(id, id);
	}

	public static BlockItemId create(final String name) {
		Identifier id = id(name);
		return BlockItemId.create(id, id);
	}

	private static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path);
	}
}
