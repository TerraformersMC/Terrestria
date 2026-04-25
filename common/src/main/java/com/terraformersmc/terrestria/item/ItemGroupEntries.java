package com.terraformersmc.terrestria.item;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public record ItemGroupEntries(@Nullable ItemLike relativeItem, ArrayList<ItemStack> items) {
	ItemGroupEntries(ArrayList<ItemStack> items) {
		this(null, items);
	}

	static ItemGroupEntries empty(@Nullable ItemLike relativeItem) {
		return new ItemGroupEntries(relativeItem, new ArrayList<>(64));
	}

	static ItemGroupEntries empty() {
		return new ItemGroupEntries(new ArrayList<>(64));
	}

	void addItem(ItemStack item) {
		items.add(item);
	}

	void addItem(ItemLike item) {
		addItem(new ItemStack(item));
	}

	Collection<ItemStack> getCollection() {
		return items;
	}
}
