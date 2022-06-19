package com.terraformersmc.terrestria.mixin;

//import com.terraformersmc.terrestria.init.TerrestriaStructures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.OverworldBiomeCreator;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(OverworldBiomeCreator.class)
public class MixinDefaultBiomeCreator {
	@Inject(method = "createColdOcean(Z)Lnet/minecraft/world/biome/Biome;",
			at = @At(value = "INVOKE_ASSIGN", target = "net/minecraft/world/biome/OverworldBiomeCreator.createOceanGenerationSettings()Lnet/minecraft/world/biome/GenerationSettings$Builder;", shift = At.Shift.AFTER),
			locals = LocalCapture.CAPTURE_FAILSOFT)
	private static void terrestria$addColdOceanVolcanoes(boolean deep, CallbackInfoReturnable<Biome> cir,
														 SpawnSettings.Builder lv, GenerationSettings.Builder lv2) {
		if (deep) {
			//TerrestriaStructures.addOceanVolcanoesToBiome(lv2);
		}
	}
}
