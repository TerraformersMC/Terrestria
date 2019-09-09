package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;

@Mixin(EatGrassGoal.class)
public class MixinEatGrassGoal {
	@Shadow
	@Final
	private MobEntity mob;
	@Shadow
	@Final
	private World world;

	@Shadow
	private int timer;

	@Inject(method = "canStart", at = @At("HEAD"), cancellable = true)
	private void canStart(CallbackInfoReturnable<Boolean> callbackInfo) {
		if (this.mob.getRand().nextInt(this.mob.isBaby() ? 50 : 1000) != 0) {
			callbackInfo.setReturnValue(false);
		} else {
			BlockPos pos = new BlockPos(this.mob);
			callbackInfo.setReturnValue(this.world.getBlockState(pos.down()).getBlock() == TerrestriaBlocks.BASALT_GRASS_BLOCK);
		}
	}

	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	private void tick(CallbackInfo info) {
		if (this.timer == 4) {
			BlockPos pos = new BlockPos(this.mob);
			BlockPos downPos = pos.down();
			if (this.world.getBlockState(downPos).getBlock() == TerrestriaBlocks.BASALT_GRASS_BLOCK) {
				if (this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
					this.world.playLevelEvent(2001, downPos, Block.getRawIdFromState(Blocks.GRASS_BLOCK.getDefaultState()));
					this.world.setBlockState(downPos, TerrestriaBlocks.BASALT_DIRT.getDefaultState(), 2);
				}

				this.mob.onEatingGrass();
			}
		}
	}
}
