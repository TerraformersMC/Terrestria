package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.surfacebuilders.TerrestriaSurfaceBuilder;
import com.terraformersmc.terrestria.surfacebuilders.TerrestriaSurfaceBuilders;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSplitter;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.chunk.BlockColumn;
import net.minecraft.world.gen.chunk.ChunkNoiseSampler;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SurfaceBuilder.class)
public class MixinSurfaceBuilder {
	@Shadow
	@Final
	private RandomSplitter randomDeriver;

	@Shadow
	@Final
	private int seaLevel;

	@Inject(method = "buildSurface",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/registry/entry/RegistryEntry;matchesKey(Lnet/minecraft/registry/RegistryKey;)Z",
					ordinal = 1,
					shift = At.Shift.BEFORE),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void terrestria$injectSurfaceBuilders(NoiseConfig noiseConfig, BiomeAccess biomeAccess, Registry<Biome> biomeRegistry, boolean useLegacyRandom, HeightContext heightContext, Chunk chunk, ChunkNoiseSampler chunkNoiseSampler, MaterialRules.MaterialRule materialRule, CallbackInfo ci, BlockPos.Mutable lv, ChunkPos lv2, int i, int j, BlockColumn lv3, MaterialRules.MaterialRuleContext lv4, MaterialRules.BlockStateRule lv5, BlockPos.Mutable lv6, int k, int l, int m, int n, int o, RegistryEntry<Biome> lv7) {
		Random random = randomDeriver.split(m, o, n);

		for (TerrestriaSurfaceBuilder builder : TerrestriaSurfaceBuilders.getBuilders()) {
			if (builder.filterBiome(lv7)) {
				builder.generate(biomeAccess, lv3, random, chunk, lv7.value(), m, n, o, seaLevel);
			}
		}
	}

	@Inject(method = "buildSurface",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/registry/entry/RegistryEntry;matchesKey(Lnet/minecraft/registry/RegistryKey;)Z",
					ordinal = 2,
					shift = At.Shift.BEFORE),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void terrestria$injectLateSurfaceBuilders(NoiseConfig noiseConfig, BiomeAccess biomeAccess, Registry<Biome> biomeRegistry, boolean useLegacyRandom, HeightContext heightContext, Chunk chunk, ChunkNoiseSampler chunkNoiseSampler, MaterialRules.MaterialRule materialRule, CallbackInfo ci, BlockPos.Mutable lv, ChunkPos lv2, int i, int j, BlockColumn lv3, MaterialRules.MaterialRuleContext lv4, MaterialRules.BlockStateRule lv5, BlockPos.Mutable lv6, int k, int l, int m, int n, int o, RegistryEntry<Biome> lv7) {
		Random random = randomDeriver.split(m, o, n);
		int surfaceMinY = lv4.method_39551();

		for (TerrestriaSurfaceBuilder builder : TerrestriaSurfaceBuilders.getBuilders()) {
			if (builder.filterBiome(lv7)) {
				builder.generateLate(biomeAccess, lv3, random, chunk, lv7.value(), m, n, o, seaLevel, surfaceMinY);
			}
		}
	}
}
