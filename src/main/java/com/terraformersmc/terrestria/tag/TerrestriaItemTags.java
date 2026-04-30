package com.terraformersmc.terrestria.tag;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class TerrestriaItemTags {
	public static final TagKey<Item> BASALTS = TerrestriaItemTags.of(Identifier.fromNamespaceAndPath("c", "basalts"));
	public static final TagKey<Item> BLACK_SANDS = TerrestriaItemTags.of(Identifier.fromNamespaceAndPath("c", "black_sands"));
	public static final TagKey<Item> DIRTS = TerrestriaItemTags.of(Identifier.fromNamespaceAndPath("c", "dirts"));
	public static final TagKey<Item> PLANKS_THAT_BURN = TerrestriaItemTags.of(Identifier.fromNamespaceAndPath("c", "planks_that_burn"));
	public static final TagKey<Item> PODZOLS = TerrestriaItemTags.of(Identifier.fromNamespaceAndPath("c", "podzols"));
	public static final TagKey<Item> SANDS = TerrestriaItemTags.of(Identifier.fromNamespaceAndPath("c", "sands"));

	public static final TagKey<Item> CYPRESS_LOGS = TerrestriaItemTags.of("cypress_logs");
	public static final TagKey<Item> HEMLOCK_LOGS = TerrestriaItemTags.of("hemlock_logs");
	public static final TagKey<Item> JAPANESE_MAPLE_LOGS = TerrestriaItemTags.of("japanese_maple_logs");
	public static final TagKey<Item> MOSSY_INGREDIENTS = TerrestriaItemTags.of("mossy_ingredients");
	public static final TagKey<Item> RAINBOW_EUCALYPTUS_LOGS = TerrestriaItemTags.of("rainbow_eucalyptus_logs");
	public static final TagKey<Item> REDWOOD_LOGS = TerrestriaItemTags.of("redwood_logs");
	public static final TagKey<Item> RUBBER_LOGS = TerrestriaItemTags.of("rubber_logs");
	public static final TagKey<Item> SAKURA_LOGS = TerrestriaItemTags.of("sakura_logs");
	public static final TagKey<Item> SMALL_OAK_LOGS = TerrestriaItemTags.of("small_oak_logs");
	public static final TagKey<Item> WILLOW_LOGS = TerrestriaItemTags.of("willow_logs");
	public static final TagKey<Item> YUCCA_PALM_LOGS = TerrestriaItemTags.of("yucca_palm_logs");

	@SuppressWarnings("UnnecessaryReturnStatement")
	private TerrestriaItemTags() {
		return;
	}

	private static TagKey<Item> of(String path) {
		return TerrestriaItemTags.of(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path));
	}

	private static TagKey<Item> of(Identifier id) {
		return TagKey.create(Registries.ITEM, id);
	}
}
