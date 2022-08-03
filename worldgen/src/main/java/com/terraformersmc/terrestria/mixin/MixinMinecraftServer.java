package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.biomeperimeters.BiomePerimeters;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
	@Unique
	private long ticksUntilBiomePerimetersCacheClean;

	@Inject(method = "tick", at = @At("TAIL"))
	private void terrestria$biomePerimetersTimerTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
		if (--this.ticksUntilBiomePerimetersCacheClean <= 0L) {
			this.ticksUntilBiomePerimetersCacheClean = BiomePerimeters.compactAll();
		}
	}
}
