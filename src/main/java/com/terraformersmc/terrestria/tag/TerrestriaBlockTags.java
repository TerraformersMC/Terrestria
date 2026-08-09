package com.terraformersmc.terrestria.tag;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class TerrestriaBlockTags {
	@SuppressWarnings("UnnecessaryReturnStatement")
	private TerrestriaBlockTags() {
		return;
	}

	private static TagKey<Block> of(String path) {
		return TerrestriaBlockTags.of(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path));
	}

	private static TagKey<Block> of(Identifier id) {
		return TagKey.create(Registries.BLOCK, id);
	}
}
