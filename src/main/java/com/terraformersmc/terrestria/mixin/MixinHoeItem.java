package com.terraformersmc.terrestria.mixin;

import com.google.common.collect.ImmutableMap;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(HoeItem.class)
public class MixinHoeItem {
	@Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
	private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> info) {
		Map<Block, BlockState> BasaltBlocks = new HashMap<Block, BlockState>(ImmutableMap.of(TerrestriaBlocks.BASALT_GRASS_BLOCK, TerrestriaBlocks.BASALT_FARMLAND.getDefaultState(), TerrestriaBlocks.BASALT_DIRT, TerrestriaBlocks.BASALT_FARMLAND.getDefaultState(), TerrestriaBlocks.BASALT_GRASS_PATH, TerrestriaBlocks.BASALT_FARMLAND.getDefaultState()));
		World world_1 = context.getWorld();
		BlockPos blockPos_1 = context.getBlockPos();
		if (context.getSide() != Direction.DOWN && world_1.getBlockState(blockPos_1.up()).isAir()) {
			BlockState blockState_1 = (BlockState)BasaltBlocks.get(world_1.getBlockState(blockPos_1).getBlock());
			if (blockState_1 != null) {
				PlayerEntity playerEntity_1 = context.getPlayer();
				world_1.playSound(playerEntity_1, blockPos_1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world_1.isClient) {
					world_1.setBlockState(blockPos_1, blockState_1, 11);
					if (playerEntity_1 != null) {
						context.getStack().damage(1, playerEntity_1, (playerEntity_1x) -> {
							playerEntity_1x.sendToolBreakStatus(context.getHand());
						});
					}
				}

				info.setReturnValue(ActionResult.SUCCESS);
			}
		}
	}
}
