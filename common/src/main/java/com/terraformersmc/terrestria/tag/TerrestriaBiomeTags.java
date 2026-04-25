package com.terraformersmc.terrestria.tag;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biome;

public final class TerrestriaBiomeTags {
	public static final TagKey<Biome> CANYON_ARCH_HAS_STRUCTURE = TerrestriaBiomeTags.of("canyon_arch_has_structure");
	public static final TagKey<Biome> OCEAN_VOLCANO_HAS_STRUCTURE = TerrestriaBiomeTags.of("ocean_volcano_has_structure");
	public static final TagKey<Biome> VOLCANO_HAS_STRUCTURE = TerrestriaBiomeTags.of("volcano_has_structure");

	@SuppressWarnings("UnnecessaryReturnStatement")
	private TerrestriaBiomeTags() {
		return;
	}

	private static TagKey<Biome> of(String path) {
		return TerrestriaBiomeTags.of(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path));
	}

	private static TagKey<Biome> of(Identifier id) {
		return TagKey.create(Registries.BIOME, id);
	}
}
