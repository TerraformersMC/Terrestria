package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(AnimalEntity.class)
public class MixinAnimalEntity {
	@Inject(method = "isValidNaturalSpawn", at = @At("HEAD"), cancellable = true)
	private static void canSpawnAt(EntityType<? extends AnimalEntity> entity, IWorld world, SpawnType spawnType, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> callbackInfo) {
		if(world.getBlockState(pos.down()).getBlock() == TerrestriaBlocks.BASALT_GRASS_BLOCK && world.getBaseLightLevel(pos, 0) > 8) {
			callbackInfo.setReturnValue(true);
		}
	}
}
