package com.terraformersmc.terrestria.init.helpers;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
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
	 * @param key Resource key of the block
	 * @param factory Factory function to create {@link Block} from properties
	 * @param properties {@link BlockBehaviour.Properties} of the block
	 * @return Newly registered {@link Block}
	 */
	public static <S extends SignBlock> S registerSignBlock(ResourceKey<Block> key, Function<BlockBehaviour.Properties, S> factory, BlockBehaviour.Properties properties) {
		S block = register(key, factory, properties);

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
	 * Registers a block via its {@linkplain BlockItemId}.
	 *
	 * @param key BlockItemId of the block
	 * @param factory Factory function to create {@link Block} from properties
	 * @param properties {@link BlockBehaviour.Properties} of the block
	 * @return Newly registered {@link Block}
	 */
	public static <B extends Block> B register(BlockItemId key, Function<BlockBehaviour.Properties, B> factory, BlockBehaviour.Properties properties) {
		return register(key.block(), factory, properties);
	}

	/**
	 * Registers a block via its {@linkplain ResourceKey}.
	 *
	 * @param key Resource key of the block
	 * @param factory Factory function to create {@link Block} from properties
	 * @param properties {@link BlockBehaviour.Properties} of the block
	 * @return Newly registered {@link Block}
	 */
	public static <B extends Block> B register(ResourceKey<Block> key, Function<BlockBehaviour.Properties, B> factory, BlockBehaviour.Properties properties) {
		B block = factory.apply(properties.setId(key));

		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

	/**
	 * Registers a block item and associates it with its block.
	 * <br/>
	 * This method applies {@code properties.useBlockDescriptionPrefix()}.
	 *
	 * @param key {@linkplain BlockItemId} of the block item
	 * @param block {@link Block} to associate to the block item
	 * @return Newly created {@link BlockItem}
	 */
	public static BlockItem registerBlockItem(BlockItemId key, Block block) {
		return register(key.item(), properties -> new BlockItem(block, properties), new Item.Properties().useBlockDescriptionPrefix());
	}

	/**
	 * Registers an item via its {@linkplain BlockItemId}.
	 * <br/>
	 * When using this method directly, the caller should apply
	 * {@code properties.useBlockDescriptionPrefix()} if desired.
	 *
	 * @param key BlockItemId of the item
	 * @param factory Factory function to create {@link Item} from properties
	 * @param properties {@link Item.Properties} of the item
	 * @return Newly registered {@link Item}
	 */
	public static <I extends Item> I register(BlockItemId key, Function<Item.Properties, I> factory, Item.Properties properties) {
		return register(key.item(), factory, properties);
	}

	/**
	 * Registers an item via its {@linkplain ResourceKey}.
	 * <br/>
	 * When using this method directly, the caller should apply
	 * {@code properties.useBlockDescriptionPrefix()} if desired.
	 *
	 * @param key Resource key of the item
	 * @param factory Factory function to create {@link Item} from properties
	 * @param properties {@link Item.Properties} of the item
	 * @return Newly registered {@link Item}
	 */
	public static <I extends Item> I register(ResourceKey<Item> key, Function<Item.Properties, I> factory, Item.Properties properties) {
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
	 * @param context A registration-capable abstraction of a registry of features
	 * @param key {@link ResourceKey<Feature>} of the feature
	 * @param feature {@link Feature} to be registered
	 * @return Newly registered {@link Feature}
	 */
	public static Holder.Reference<Feature> register(BootstrapContext<Feature> context, ResourceKey<Feature> key, Feature feature) {
		return context.register(key, feature);
	}

	/**
	 * Creates and registers a placed feature (during datagen).  The placed feature will be created with
	 * the provided placement modifiers and registered to the provided registerable under the provided key.
	 *
	 * @param context A registration-capable abstraction of a registry of placed features
	 * @param key {@link ResourceKey} of {@link PlacedFeature} being registered
	 * @param feature {@link ResourceKey} of {@link Feature} being placed
	 * @param placementModifiers One or more {@link PlacementModifier}(s) to apply to {@link Feature} being registered
	 */
	public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<Feature> feature, PlacementModifier... placementModifiers) {
		PlacementUtils.register(context, key,
			context.lookup(Registries.FEATURE).getOrThrow(feature),
			placementModifiers);
	}

	/**
	 * Creates and registers a placed feature (during datagen).  The placed feature will be created with
	 * the provided placement modifiers and registered to the provided registerable under the provided key.
	 *
	 * @param context A registration-capable abstraction of a registry of placed features
	 * @param key {@link ResourceKey} of {@link PlacedFeature} being registered
	 * @param feature {@link ResourceKey} of {@link Feature} being placed
	 * @param placementModifiers {@link List} of {@link PlacementModifier}(s) to apply to {@link Feature} being registered
	 */
	public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<Feature> feature, List<PlacementModifier> placementModifiers) {
		PlacementUtils.register(context, key,
			context.lookup(Registries.FEATURE).getOrThrow(feature),
			placementModifiers);
	}
}
