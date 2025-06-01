package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;
import java.util.function.Function;

public class TerrestriaRegistry {
	@SuppressWarnings("UnnecessaryReturnStatement")
	private TerrestriaRegistry() {
		return;
	}

	/*
	 * Blocks and Items
	 */

	/**
	 * Registers a sign block.
	 *
	 * In addition to registering the block, this method also registers the block as a support for its block entity.
	 *
	 * @param name Name ({@link Identifier} path string) of the block
	 * @param factory Factory function to create {@link Block} from settings
	 * @param settings {@link AbstractBlock.Settings} of the block
	 * @return Newly registered {@link Block}
	 */
	public static <S extends AbstractSignBlock> S registerSignBlock(String name, Function<AbstractBlock.Settings, S> factory, AbstractBlock.Settings settings) {
		S block = register(name, factory, settings);

		if (block instanceof SignBlock || block instanceof WallSignBlock) {
			BlockEntityType.SIGN.addSupportedBlock(block);
		} else if (block instanceof HangingSignBlock || block instanceof WallHangingSignBlock) {
			BlockEntityType.HANGING_SIGN.addSupportedBlock(block);
		} else {
			throw new IllegalArgumentException("This method only accepts vanilla sign blocks and descendants!");
		}

		return block;
	}

	/**
	 * Registers a block.
	 *
	 * @param name Name ({@link Identifier} path string) of the block
	 * @param factory Factory function to create {@link Block} from settings
	 * @param settings {@link AbstractBlock.Settings} of the block
	 * @return Newly registered {@link Block}
	 */
	public static <B extends Block> B register(String name, Function<AbstractBlock.Settings, B> factory, AbstractBlock.Settings settings) {
		RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Terrestria.MOD_ID, name));
		B block = factory.apply(settings.registryKey(key));

		return Registry.register(Registries.BLOCK, key, block);
	}

	/**
	 * Registers a block item and associates it with its block.
	 *
	 * This method applies {@code settings.useBlockPrefixedTranslationKey()}.
	 *
	 * @param name Name ({@link Identifier} path string) of the block item
	 * @param block {@link Block} to associate to the block item
	 * @return Newly created {@link BlockItem}
	 */
	public static BlockItem registerBlockItem(String name, Block block) {
		return register(name, settings -> new BlockItem(block, settings), new Item.Settings().useBlockPrefixedTranslationKey());
	}

	/**
	 * Registers an item.
	 *
	 * When using this method directly, the caller should apply
	 * {@code settings.useBlockPrefixedTranslationKey()} if desired.
	 *
	 * @param name Name ({@link Identifier} path string) of the item
	 * @param factory Factory function to create {@link Item} from settings
	 * @param settings {@link Item.Settings} of the item
	 * @return Newly registered {@link Item}
	 */
	public static <I extends Item> I register(String name, Function<Item.Settings, I> factory, Item.Settings settings) {
		RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Terrestria.MOD_ID, name));
		I item = factory.apply(settings.registryKey(key));

		if (item instanceof BlockItem blockItem) {
			blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
		}

		return Registry.register(Registries.ITEM, key, item);
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
