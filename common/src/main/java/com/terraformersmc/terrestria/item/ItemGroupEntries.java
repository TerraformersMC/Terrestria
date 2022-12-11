package com.terraformersmc.terrestria.item;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public record ItemGroupEntries(@Nullable ItemConvertible relativeItem, ArrayList<ItemStack> items) {
	ItemGroupEntries(ArrayList<ItemStack> items) {
		this(null, items);
	}

	static ItemGroupEntries empty(@Nullable ItemConvertible relativeItem) {
		return new ItemGroupEntries(relativeItem, new ArrayList<>(64));
	}

	static ItemGroupEntries empty() {
		return new ItemGroupEntries(new ArrayList<>(64));
	}

	void addItem(ItemStack item) {
		items.add(item);
	}

	void addItem(ItemConvertible item) {
		addItem(new ItemStack(item));
	}

	Collection<ItemStack> getCollection() {
		return items;
	}
}
