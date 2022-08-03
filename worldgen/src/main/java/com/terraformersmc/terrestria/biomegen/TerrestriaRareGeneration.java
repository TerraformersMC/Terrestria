package com.terraformersmc.terrestria.biomegen;

import com.mojang.datafixers.util.Pair;
import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.*;

import java.util.function.Consumer;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.*;

public class TerrestriaRareGeneration extends Region implements TerraBlenderApi {

	public TerrestriaRareGeneration() {
		super(new Identifier(Terrestria.MOD_ID, "overworld_rare"), RegionType.OVERWORLD, 7);
	}

	@Override
	public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
		this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
			builder.replaceBiome(BiomeKeys.DESERT, DUNES);
			builder.replaceBiome(BiomeKeys.MEADOW, HEMLOCK_TREELINE);
			builder.replaceBiome(BiomeKeys.JUNGLE, RAINBOW_RAINFOREST);
			builder.replaceBiome(BiomeKeys.DEEP_LUKEWARM_OCEAN, VOLCANIC_ISLAND);
		});
	}
}
