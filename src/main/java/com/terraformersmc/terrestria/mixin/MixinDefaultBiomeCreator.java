package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaStructures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeCreator;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

@Mixin(DefaultBiomeCreator.class)
public class MixinDefaultBiomeCreator {
	@Inject(method = "createOcean(Lnet/minecraft/world/biome/SpawnSettings$Builder;IIZLnet/minecraft/world/biome/GenerationSettings$Builder;)Lnet/minecraft/world/biome/Biome;",
			at = @At("HEAD"))
	private static void addOceanVolcanoes(SpawnSettings.Builder builder, int waterColor, int waterFogColor, boolean deep,
										  GenerationSettings.Builder generationSettings, CallbackInfoReturnable<Biome> callback) {
		if (deep) {
			TerrestriaStructures.addOceanVolcanoesToBiome(generationSettings);
		}
	}
}
