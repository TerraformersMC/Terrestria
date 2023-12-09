package com.terraformersmc.terrestria.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LavaFluid.class)
public class MixinLavaFluid {
	@WrapOperation(method = "flow",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/WorldAccess;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z",
					ordinal = 0
			)
	)
	@SuppressWarnings("unused")
	private boolean terrestria$generateVolcanicRock(WorldAccess world, BlockPos pos, BlockState newState, int flags, Operation<Boolean> original) {
		// This is the stone generation pathway; see also: MixinFluidBlock.

		// Search immediately adjacent blocks for Volcanic Rock variants.
		for (Direction towards : Direction.values()) {
			BlockState neighbor = world.getBlockState(pos.offset(towards));

			if (neighbor.isOf(TerrestriaBlocks.VOLCANIC_ROCK.plain.full) ||
					neighbor.isOf(TerrestriaBlocks.VOLCANIC_ROCK.cobblestone.full) ||
					neighbor.isOf(TerrestriaBlocks.VOLCANIC_ROCK.bricks.full)) {

				// If Volcanic neighbor found, convert to Volcanic Rock.
				newState = TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState();
				break;
			}
		}

		return original.call(world, pos, newState, flags);
	}
}
