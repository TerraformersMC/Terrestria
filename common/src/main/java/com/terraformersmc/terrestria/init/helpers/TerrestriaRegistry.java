package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terrestria.Terrestria;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class TerrestriaRegistry {
	@SuppressWarnings("UnnecessaryReturnStatement")
	public TerrestriaRegistry() {
		return;
	}

	/*
	 * Blocks and Items
	 */

	/**
	 * Registers a block item and associates it with its block.
	 *
	 * @param name Name ({@link Identifier} path string) of the block item
	 * @param block {@link Block} to associate to the block item
	 * @return Newly created {@link BlockItem}
	 */
	public static BlockItem registerBlockItem(String name, Block block) {
		BlockItem item = new BlockItem(block, new Item.Settings());
		return register(name, item);
	}

	/**
	 * Registers an item.
	 *
	 * @param name Name ({@link Identifier} path string) of the item
	 * @param item {@link Item} to be registered
	 * @return Newly registered {@link Item}
	 */
	public static <I extends Item> I register(String name, I item) {
		if (item instanceof BlockItem blockItem) {
			blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
		}
		return Registry.register(Registries.ITEM, Identifier.of(Terrestria.MOD_ID, name), item);
	}

	/**
	 * Registers a block.
	 *
	 * @param name Name ({@link Identifier} path string) of the block
	 * @param block {@link Block} to be registered
	 * @return Newly registered {@link Block}
	 */
	public static <B extends Block> B register(String name, B block) {
		return Registry.register(Registries.BLOCK, Identifier.of(Terrestria.MOD_ID, name), block);
	}

	/*
	 * Features
	 */

	/**
	 * Registers a feature.
	 *
	 * @param name Name ({@link Identifier} path string) of the feature
	 * @param feature {@link Feature} to be registered
	 * @return Newly registered {@link Feature}
	 */
	public static <T extends Feature<FC>, FC extends FeatureConfig> T register(String name, T feature) {
		return Registry.register(Registries.FEATURE, Identifier.of(Terrestria.MOD_ID, name), feature);
	}

	/**
	 * Creates and registers a configured feature (during datagen).  The configured feature will be created
	 * with the provided feature config and registered to the provided registerable under the provided key.
	 *
	 * @param registerable A registration-capable abstraction of a registry of configured features
	 * @param key {@link RegistryKey} of {@link ConfiguredFeature} being registered
	 * @param feature {@link Feature} being configured
	 * @param config {@link FeatureConfig} to apply to {@link Feature} being registered
	 */
	public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
		ConfiguredFeatures.register(registerable, key, feature, config);
	}

	/**
	 * Creates and registers a placed feature (during datagen).  The placed feature will be created with
	 * the provided placement modifiers and registered to the provided registerable under the provided key.
	 *
	 * @param registerable A registration-capable abstraction of a registry of placed features
	 * @param key {@link RegistryKey} of {@link PlacedFeature} being registered
	 * @param feature {@link RegistryKey} of {@link ConfiguredFeature} being placed
	 * @param placementModifiers Any {@link PlacementModifier}(s) to apply to {@link ConfiguredFeature} being registered
	 */
	public static void register(Registerable<PlacedFeature> registerable, RegistryKey<PlacedFeature> key, RegistryKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
		register(registerable, key, feature, List.of(placementModifiers));
	}

	/**
	 * Creates and registers a placed feature (during datagen).  The placed feature will be created with
	 * the provided placement modifiers and registered to the provided registerable under the provided key.
	 *
	 * @param registerable A registration-capable abstraction of a registry of placed features
	 * @param key {@link RegistryKey} of {@link PlacedFeature} being registered
	 * @param feature {@link RegistryKey} of {@link ConfiguredFeature} being placed
	 * @param placementModifiers {@link List} of {@link PlacementModifier}(s) to apply to {@link ConfiguredFeature} being registered
	 */
	public static void register(Registerable<PlacedFeature> registerable, RegistryKey<PlacedFeature> key, RegistryKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
		PlacedFeatures.register(registerable, key,
				registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(feature),
				placementModifiers);
	}
}
