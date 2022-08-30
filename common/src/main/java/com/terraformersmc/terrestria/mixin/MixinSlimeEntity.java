package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SlimeEntity.class)
public abstract class MixinSlimeEntity extends MobEntity implements Monster {
	public MixinSlimeEntity(EntityType<? extends MobEntity> type, World world) {
		super(type, world);
	}

	@Inject(method = "canSpawn", at = @At(value = "HEAD"), cancellable = true)
	private static void terrestria$canSpawnSlimes(EntityType<SlimeEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> info) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			return; // Delegate back to vanilla
		}

		if (world.getBiome(pos).matchesKey(TerrestriaBiomes.CYPRESS_SWAMP) && pos.getY() > 50 && pos.getY() < 70 && random.nextFloat() < 0.5f && random.nextFloat() < world.getMoonSize() && world.getLightLevel(pos) <= random.nextInt(8)) {
			info.setReturnValue(SlimeEntity.canMobSpawn(type, world, reason, pos, random));
		}
	}
}
