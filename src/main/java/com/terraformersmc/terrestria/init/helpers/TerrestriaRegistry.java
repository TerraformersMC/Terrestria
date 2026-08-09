package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.function.Function;

@NullMarked
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
	 * <br/>
	 * In addition to registering the block, this method also registers the block as a support for its block entity.
	 *
	 * @param name Name ({@link Identifier} path string) of the block
	 * @param factory Factory function to create {@link Block} from properties
	 * @param properties {@link BlockBehaviour.Properties} of the block
	 * @return Newly registered {@link Block}
	 */
	public static <S extends SignBlock> S registerSignBlock(String name, Function<BlockBehaviour.Properties, S> factory, BlockBehaviour.Properties properties) {
		S block = register(name, factory, properties);

		if (block instanceof StandingSignBlock || block instanceof WallSignBlock) {
			BlockEntityTypes.SIGN.addValidBlock(block);
		} else if (block instanceof CeilingHangingSignBlock || block instanceof WallHangingSignBlock) {
			BlockEntityTypes.HANGING_SIGN.addValidBlock(block);
		} else {
			throw new IllegalArgumentException("This method only accepts vanilla sign blocks and descendants!");
		}

		return block;
	}

	/**
	 * Registers a block.
	 *
	 * @param name Name ({@link Identifier} path string) of the block
	 * @param factory Factory function to create {@link Block} from properties
	 * @param properties {@link BlockBehaviour.Properties} of the block
	 * @return Newly registered {@link Block}
	 */
	public static <B extends Block> B register(String name, Function<BlockBehaviour.Properties, B> factory, BlockBehaviour.Properties properties) {
		ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, name));
		B block = factory.apply(properties.setId(key));

		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

	/**
	 * Registers a block item and associates it with its block.
	 * <br/>
	 * This method applies {@code properties.useBlockDescriptionPrefix()}.
	 *
	 * @param name Name ({@link Identifier} path string) of the block item
	 * @param block {@link Block} to associate to the block item
	 * @return Newly created {@link BlockItem}
	 */
	public static BlockItem registerBlockItem(String name, Block block) {
		return register(name, properties -> new BlockItem(block, properties), new Item.Properties().useBlockDescriptionPrefix());
	}

	/**
	 * Registers an item.
	 * <br/>
	 * When using this method directly, the caller should apply
	 * {@code properties.useBlockDescriptionPrefix()} if desired.
	 *
	 * @param name Name ({@link Identifier} path string) of the item
	 * @param factory Factory function to create {@link Item} from properties
	 * @param properties {@link Item.Properties} of the item
	 * @return Newly registered {@link Item}
	 */
	public static <I extends Item> I register(String name, Function<Item.Properties, I> factory, Item.Properties properties) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, name));
		I item = factory.apply(properties.setId(key));

		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, blockItem);
		}

		return Registry.register(BuiltInRegistries.ITEM, key, item);
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
	public static <T extends Feature<FC>, FC extends FeatureConfiguration> T register(String name, T feature) {
		return Registry.register(BuiltInRegistries.FEATURE, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, name), feature);
	}

	/**
	 * Creates and registers a configured feature (during datagen).  The configured feature will be created
	 * with the provided feature config and registered to the provided registerable under the provided key.
	 *
	 * @param registerable A registration-capable abstraction of a registry of configured features
	 * @param key {@link ResourceKey} of {@link ConfiguredFeature} being registered
	 * @param feature {@link Feature} being configured
	 * @param config {@link FeatureConfiguration} to apply to {@link Feature} being registered
	 */
	public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> registerable, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
		FeatureUtils.register(registerable, key, feature, config);
	}

	/**
	 * Creates and registers a placed feature (during datagen).  The placed feature will be created with
	 * the provided placement modifiers and registered to the provided registerable under the provided key.
	 *
	 * @param registerable A registration-capable abstraction of a registry of placed features
	 * @param key {@link ResourceKey} of {@link PlacedFeature} being registered
	 * @param feature {@link ResourceKey} of {@link ConfiguredFeature} being placed
	 * @param placementModifiers Any {@link PlacementModifier}(s) to apply to {@link ConfiguredFeature} being registered
	 */
	public static void register(BootstrapContext<PlacedFeature> registerable, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
		register(registerable, key, feature, List.of(placementModifiers));
	}

	/**
	 * Creates and registers a placed feature (during datagen).  The placed feature will be created with
	 * the provided placement modifiers and registered to the provided registerable under the provided key.
	 *
	 * @param registerable A registration-capable abstraction of a registry of placed features
	 * @param key {@link ResourceKey} of {@link PlacedFeature} being registered
	 * @param feature {@link ResourceKey} of {@link ConfiguredFeature} being placed
	 * @param placementModifiers {@link List} of {@link PlacementModifier}(s) to apply to {@link ConfiguredFeature} being registered
	 */
	public static void register(BootstrapContext<PlacedFeature> registerable, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
		PlacementUtils.register(registerable, key,
			registerable.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(feature),
			placementModifiers);
	}
}
