package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import org.jspecify.annotations.NullMarked;

@NullMarked
@SuppressWarnings("unused")
public class TerrestriaBlockIds {
	public static final WoodBlockIds REDWOOD = WoodBlockIds.of(id("redwood"));
	public static final WoodBlockIds HEMLOCK = WoodBlockIds.of(id("hemlock"));
	public static final WoodBlockIds RUBBER = WoodBlockIds.of(id("rubber"));
	public static final WoodBlockIds CYPRESS = WoodBlockIds.of(id("cypress"));
	public static final WoodBlockIds WILLOW = WoodBlockIds.of(id("willow"));
	public static final WoodBlockIds JAPANESE_MAPLE = WoodBlockIds.of(id("japanese_maple"));
	public static final WoodBlockIds RAINBOW_EUCALYPTUS = WoodBlockIds.of(id("rainbow_eucalyptus"));
	public static final WoodBlockIds SAKURA = WoodBlockIds.of(id("sakura"));
	public static final WoodBlockIds YUCCA_PALM = WoodBlockIds.of(id("yucca_palm"));

	public static final ResourceKey<Block> SMALL_OAK_LOG = create("small_oak_log");
	public static final ResourceKey<Block> STRIPPED_SMALL_OAK_LOG = create("stripped_small_oak_log");
	public static final ResourceKey<Block> SAGUARO_CACTUS = create("saguaro_cactus");

	public static final ResourceKey<Block> JAPANESE_MAPLE_SHRUB_LEAVES = create("japanese_maple_shrub_leaves");
	public static final ResourceKey<Block> DARK_JAPANESE_MAPLE_LEAVES = create("dark_japanese_maple_shrub_leaves");
	public static final ResourceKey<Block> JUNGLE_PALM_LEAVES = create("jungle_palm_leaves");

	public static final ResourceKey<Block> CATTAIL = create("cattail");
	public static final ResourceKey<Block> TALL_CATTAIL = create("tall_cattail");

	public static final ResourceKey<Block> BRYCE_SAPLING = create("bryce_sapling");
	public static final ResourceKey<Block> REDWOOD_SAPLING = create("redwood_sapling");
	public static final ResourceKey<Block> HEMLOCK_SAPLING = create("hemlock_sapling");
	public static final ResourceKey<Block> RUBBER_SAPLING = create("rubber_sapling");
	public static final ResourceKey<Block> CYPRESS_SAPLING = create("cypress_sapling");
	public static final ResourceKey<Block> WILLOW_SAPLING = create("willow_sapling");
	public static final ResourceKey<Block> JAPANESE_MAPLE_SAPLING = create("japanese_maple_sapling");
	public static final ResourceKey<Block> JAPANESE_MAPLE_SHRUB_SAPLING = create("japanese_maple_shrub_sapling");
	public static final ResourceKey<Block> DARK_JAPANESE_MAPLE_SAPLING = create("dark_japanese_maple_sapling");
	public static final ResourceKey<Block> RAINBOW_EUCALYPTUS_SAPLING = create("rainbow_eucalyptus_sapling");
	public static final ResourceKey<Block> SAKURA_SAPLING = create("sakura_sapling");
	public static final ResourceKey<Block> JUNGLE_PALM_SAPLING = create("jungle_palm_sapling");
	public static final ResourceKey<Block> SAGUARO_CACTUS_SAPLING = create("saguaro_cactus_sapling");
	public static final ResourceKey<Block> YUCCA_PALM_SAPLING = create("yucca_palm_sapling");

	public static final ResourceKey<Block> POTTED_BRYCE_SAPLING = create("potted_bryce_sapling");
	public static final ResourceKey<Block> POTTED_REDWOOD_SAPLING = create("potted_redwood_sapling");
	public static final ResourceKey<Block> POTTED_HEMLOCK_SAPLING = create("potted_hemlock_sapling");
	public static final ResourceKey<Block> POTTED_JUNGLE_PALM_SAPLING = create("potted_jungle_palm_sapling");
	public static final ResourceKey<Block> POTTED_RUBBER_SAPLING = create("potted_rubber_sapling");
	public static final ResourceKey<Block> POTTED_CYPRESS_SAPLING = create("potted_cypress_sapling");
	public static final ResourceKey<Block> POTTED_WILLOW_SAPLING = create("potted_willow_sapling");
	public static final ResourceKey<Block> POTTED_JAPANESE_MAPLE_SAPLING = create("potted_japanese_maple_sapling");
	public static final ResourceKey<Block> POTTED_JAPANESE_MAPLE_SHRUB_SAPLING = create("potted_japanese_maple_shrub_sapling");
	public static final ResourceKey<Block> POTTED_DARK_JAPANESE_MAPLE_SAPLING = create("potted_dark_japanese_maple_sapling");
	public static final ResourceKey<Block> POTTED_RAINBOW_EUCALYPTUS_SAPLING = create("potted_rainbow_eucalyptus_sapling");
	public static final ResourceKey<Block> POTTED_SAKURA_SAPLING = create("potted_sakura_sapling");
	public static final ResourceKey<Block> POTTED_SAGUARO_CACTUS_SAPLING = create("potted_saguaro_cactus_sapling");
	public static final ResourceKey<Block> POTTED_YUCCA_PALM_SAPLING = create("potted_yucca_palm_sapling");

	// Volcanic Island blocks
	public static final ResourceKey<Block> VOLCANIC_SAND = create("volcanic_sand");
	public static final DirtBlockIds ANDISOL = DirtBlockIds.of(id("andisol"));
	public static final StoneBlockIds VOLCANIC_ROCK = StoneBlockIds.of(id("volcanic_rock"));
	public static final ResourceKey<Block> INDIAN_PAINTBRUSH = create("indian_paintbrush");
	public static final ResourceKey<Block> MONSTERAS = create("monsteras");
	public static final ResourceKey<Block> POTTED_INDIAN_PAINTBRUSH = create("potted_indian_paintbrush");
	public static final ResourceKey<Block> POTTED_MONSTERAS = create("potted_monsteras");

	// Desert Plants
	public static final ResourceKey<Block> TINY_CACTUS = create("tiny_cactus");
	public static final ResourceKey<Block> AGAVE = create("agave");
	public static final ResourceKey<Block> ALOE_VERA = create("aloe_vera");
	public static final ResourceKey<Block> DEAD_GRASS = create("dead_grass");
	public static final ResourceKey<Block> POTTED_TINY_CACTUS = create("potted_tiny_cactus");
	public static final ResourceKey<Block> POTTED_AGAVE = create("potted_agave");
	public static final ResourceKey<Block> POTTED_ALOE_VERA = create("potted_aloe_vera");


	public record DirtBlockIds(ResourceKey<Block> dirtBlock, ResourceKey<Block> dirtPathBlock, ResourceKey<Block> farmBlock, ResourceKey<Block> grassBlock, ResourceKey<Block> podzolBlock) {
		public static DirtBlockIds of(Identifier id) {
			return new DirtBlockIds(
				create(id),
				create(id.withSuffix("_dirt_path")),
				create(id.withSuffix("_farmland")),
				create(id.withSuffix("_grass_block")),
				create(id.withSuffix("_podzol"))
			);
		}
	}

	public record StoneBlockIds(StoneVariantBlockIds plain, StoneVariantBlockIds smooth, StoneVariantBlockIds cobblestone, StoneVariantBlockIds mossyCobblestone, StoneVariantBlockIds bricks, StoneVariantBlockIds mossyBricks, ResourceKey<Block> button, ResourceKey<Block> pressurePlate, ResourceKey<Block> chiseledBricks, ResourceKey<Block> crackedBricks) {
		public static StoneBlockIds of(Identifier id) {
			Identifier brickId = id.withSuffix("_brick");
			Identifier bricksId = id.withSuffix("_bricks");

			return new StoneBlockIds(
				StoneVariantBlockIds.of(id),
				StoneVariantBlockIds.of(id.withPrefix("smooth_")),
				StoneVariantBlockIds.of(id.withSuffix("_cobblestone")),
				StoneVariantBlockIds.of(id.withPath(name -> "mossy_" + name + "_cobblestone")),
				StoneVariantBlockIds.of(bricksId, brickId),
				StoneVariantBlockIds.of(bricksId.withPrefix("mossy_"), brickId.withPrefix("mossy_")),
				create(id.withSuffix("_button")),
				create(id.withSuffix("_pressure_plate")),
				create(bricksId.withPrefix("chiseled_")),
				create(bricksId.withPrefix("cracked_"))
			);
		}
	}

	public record StoneVariantBlockIds(ResourceKey<Block> full, ResourceKey<Block> slab, ResourceKey<Block> stairs, ResourceKey<Block> wall) {
		public static StoneVariantBlockIds of(Identifier id, Identifier shapedId) {
			return new StoneVariantBlockIds(
				create(id),
				create(shapedId.withSuffix("_slab")),
				create(shapedId.withSuffix("_stairs")),
				create(shapedId.withSuffix("_wall"))
			);
		}

		public static StoneVariantBlockIds of(Identifier id) {
			return of(id, id);
		}
	}

	public record WoodBlockIds(ResourceKey<Block> log, ResourceKey<Block> quarterLog, ResourceKey<Block> wood, ResourceKey<Block> leaves, ResourceKey<Block> leafPile, ResourceKey<Block> planks, ResourceKey<Block> slab, ResourceKey<Block> stairs, ResourceKey<Block> fence, ResourceKey<Block> fenceGate, ResourceKey<Block> door, ResourceKey<Block> button, ResourceKey<Block> pressurePlate, ResourceKey<Block> sign, ResourceKey<Block> wallSign, ResourceKey<Block> hangingSign, ResourceKey<Block> wallHangingSign, ResourceKey<Block> trapdoor, ResourceKey<Block> shelf, ResourceKey<Block> strippedLog, ResourceKey<Block> strippedQuarterLog, ResourceKey<Block> strippedWood) {
		public static WoodBlockIds of(Identifier id) {
			return new WoodBlockIds(
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
				create(id.withSuffix("_stripped_log")),
				create(id.withSuffix("_stripped_quarter_log")),
				create(id.withSuffix("_stripped_wood"))
			);
		}
	}


	private static ResourceKey<Block> create(final Identifier id) {
		return ResourceKey.create(Registries.BLOCK, id);
	}

	private static ResourceKey<Block> create(final String name) {
		return create(id(name));
	}

	private static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path);
	}
}
