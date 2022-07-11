package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.surfacebuilders.TerrestriaSurfaceBuilder;
import com.terraformersmc.terrestria.surfacebuilders.TerrestriaSurfaceBuilders;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.chunk.BlockColumn;
import net.minecraft.world.gen.chunk.ChunkNoiseSampler;
import net.minecraft.world.gen.random.AbstractRandom;
import net.minecraft.world.gen.random.RandomDeriver;
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
	private RandomDeriver randomDeriver;

	@Shadow
	@Final
	private int seaLevel;

	@Inject(method = "buildSurface",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/util/registry/RegistryEntry;matchesKey(Lnet/minecraft/util/registry/RegistryKey;)Z",
					ordinal = 1,
					shift = At.Shift.BEFORE),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void terrestria$injectSurfaceBuilders(BiomeAccess biomeAccess, Registry<Biome> biomeRegistry, boolean useLegacyRandom, HeightContext context, final Chunk chunk, ChunkNoiseSampler chunkNoiseSampler, MaterialRules.MaterialRule surfaceRule, CallbackInfo ci, BlockPos.Mutable lv, ChunkPos lv2, int i, int j, BlockColumn lv3, MaterialRules.MaterialRuleContext lv4, MaterialRules.BlockStateRule lv5, BlockPos.Mutable lv6, int k, int l, int m, int n, int o, RegistryEntry<Biome> lv7) {
		AbstractRandom random = randomDeriver.createRandom(m, o, n);

		for (TerrestriaSurfaceBuilder builder : TerrestriaSurfaceBuilders.getBuilders()) {
			if (builder.filterBiome(lv7)) {
				builder.generate(biomeAccess, lv3, random, chunk, lv7.value(), m, n, o, seaLevel);
			}
		}
	}

	@Inject(method = "buildSurface",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/util/registry/RegistryEntry;matchesKey(Lnet/minecraft/util/registry/RegistryKey;)Z",
					ordinal = 2,
					shift = At.Shift.BEFORE),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void terrestria$injectLateSurfaceBuilders(BiomeAccess biomeAccess, Registry<Biome> biomeRegistry, boolean useLegacyRandom, HeightContext context, final Chunk chunk, ChunkNoiseSampler chunkNoiseSampler, MaterialRules.MaterialRule surfaceRule, CallbackInfo ci, BlockPos.Mutable lv, ChunkPos lv2, int i, int j, BlockColumn lv3, MaterialRules.MaterialRuleContext lv4, MaterialRules.BlockStateRule lv5, BlockPos.Mutable lv6, int k, int l, int m, int n, int o, RegistryEntry<Biome> lv7) {
		AbstractRandom random = randomDeriver.createRandom(m, o, n);
		int surfaceMinY = lv4.method_39551();

		for (TerrestriaSurfaceBuilder builder : TerrestriaSurfaceBuilders.getBuilders()) {
			if (builder.filterBiome(lv7)) {
				builder.generateLate(biomeAccess, lv3, random, chunk, lv7.value(), m, n, o, seaLevel, surfaceMinY);
			}
		}
	}
}
