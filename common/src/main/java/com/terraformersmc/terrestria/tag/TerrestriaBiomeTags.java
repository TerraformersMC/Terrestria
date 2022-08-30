package com.terraformersmc.terrestria.tag;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public final class TerrestriaBiomeTags {
	public static final TagKey<Biome> CANYON_ARCH_HAS_STRUCTURE = TerrestriaBiomeTags.of("canyon_arch_has_structure");
	public static final TagKey<Biome> OCEAN_VOLCANO_HAS_STRUCTURE = TerrestriaBiomeTags.of("ocean_volcano_has_structure");
	public static final TagKey<Biome> VOLCANO_HAS_STRUCTURE = TerrestriaBiomeTags.of("volcano_has_structure");

	private TerrestriaBiomeTags() {
		return;
	}

	private static TagKey<Biome> of(String path) {
		return TerrestriaBiomeTags.of(new Identifier(Terrestria.MOD_ID, path));
	}

	private static TagKey<Biome> of(Identifier id) {
		return TagKey.of(Registry.BIOME_KEY, id);
	}
}
