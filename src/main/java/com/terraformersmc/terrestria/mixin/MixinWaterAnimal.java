package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.fish.WaterAnimal;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NullMarked;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@NullMarked
@Mixin(WaterAnimal.class)
public class MixinWaterAnimal {
	@Inject(method = "checkSurfaceWaterAnimalSpawnRules(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)Z", at = @At(value = "HEAD"), cancellable = true)
	private static void terrestria$canSpawnFish(EntityType<Slime> type, LevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random, CallbackInfoReturnable<Boolean> info) {
		if (level.getBiome(pos).is(TerrestriaBiomes.CALDERA)) {
			info.setReturnValue(
				pos.getY() >= 80 &&
				pos.getY() <= 100 &&
				level.getFluidState(pos.below()).is(FluidTags.WATER) &&
				level.getBlockState(pos.above()).is(Blocks.WATER)
			);
		}
	}
}
