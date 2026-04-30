package com.terraformersmc.terrestria.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.UnmodifiableView;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

@NullMarked
@SuppressWarnings("unused")
public record CreativeModeTabEntries(@Nullable ItemLike relativeItem, ArrayList<ItemLike> items) {
	CreativeModeTabEntries(ArrayList<ItemLike> items) {
		this(null, items);
	}

	static CreativeModeTabEntries empty(@Nullable ItemLike relativeItem) {
		return new CreativeModeTabEntries(relativeItem, new ArrayList<>(64));
	}

	static CreativeModeTabEntries empty() {
		return new CreativeModeTabEntries(new ArrayList<>(64));
	}

	void addItem(ItemStack item) {
		items.add(item.getItem());
	}

	void addItem(ItemLike item) {
		items.add(item);
	}

	Stream<ItemLike> getItemStream() {
		return items.stream();
	}

	@UnmodifiableView
	Collection<ItemStack> getStackCollection() {
		return items.stream().map(ItemStack::new).toList();
	}
}
