package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
@SuppressWarnings("unused")
public class TerrestriaItemIds {
	public static final ResourceKey<Item> LOG_TURNER = create("log_turner");

	public static final WoodItemIds CYPRESS = WoodItemIds.ofBoat(id("cypress"));
	public static final WoodItemIds DARK_JAPANESE_MAPLE = WoodItemIds.ofBoat(id("dark_japanese_maple"));
	public static final WoodItemIds HEMLOCK = WoodItemIds.ofBoat(id("hemlock"));
	public static final WoodItemIds JAPANESE_MAPLE = WoodItemIds.ofBoat(id("japanese_maple"));
	public static final WoodItemIds JUNGLE_PALM = WoodItemIds.ofBoat(id("jungle_palm"));
	public static final WoodItemIds RAINBOW_EUCALYPTUS = WoodItemIds.ofBoat(id("rainbow_eucalyptus"));
	public static final WoodItemIds REDWOOD = WoodItemIds.ofBoat(id("redwood"));
	public static final WoodItemIds RUBBER = WoodItemIds.ofBoat(id("rubber"));
	public static final WoodItemIds SAKURA = WoodItemIds.ofBoat(id("sakura"));
	public static final WoodItemIds WILLOW = WoodItemIds.ofBoat(id("willow"));
	public static final WoodItemIds YUCCA_PALM = WoodItemIds.ofBoat(id("yucca_palm"));


	public record WoodItemIds(@Nullable ResourceKey<Item> boat, @Nullable ResourceKey<Item> chestBoat, @Nullable ResourceKey<Item> raft, @Nullable ResourceKey<Item> chestRaft) {
		public static WoodItemIds ofBoat(Identifier id) {
			return new WoodItemIds(
				create(id.withSuffix("_boat")),
				create(id.withSuffix("_chest_boat")),
				null,
				null
			);
		}

		public static WoodItemIds ofRaft(Identifier id) {
			return new WoodItemIds(
				null,
				null,
				create(id.withSuffix("_raft")),
				create(id.withSuffix("_chest_raft"))
			);
		}
	}


	private static ResourceKey<Item> create(Identifier id) {
		return ResourceKey.create(Registries.ITEM, id);
	}

	private static ResourceKey<Item> create(String name) {
		return create(id(name));
	}

	private static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path);
	}
}
