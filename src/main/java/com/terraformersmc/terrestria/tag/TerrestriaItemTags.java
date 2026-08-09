package com.terraformersmc.terrestria.tag;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class TerrestriaItemTags {
	public static final TagKey<Item> MOSSY_INGREDIENTS = TerrestriaItemTags.of("mossy_ingredients");

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
