package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LavaFluid.class)
public class MixinLavaFluid {
	@Inject(method = "flow",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/WorldAccess;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z",
					ordinal = 0,
					shift = At.Shift.AFTER
			)
	)
	public void terrestria$generateBasaltCobblestone(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci) {
		boolean adjacent = false;

		// Search immediately adjacent blocks for Volcanic (|Cobble)Stone.
		for (Direction towards : Direction.values()) {
			BlockState neighbor = world.getBlockState(pos.offset(towards));

			if (neighbor.isOf(TerrestriaBlocks.VOLCANIC_ROCK.plain.full) ||
					neighbor.isOf(TerrestriaBlocks.VOLCANIC_ROCK.cobblestone.full) ||
					neighbor.isOf(TerrestriaBlocks.VOLCANIC_ROCK.bricks.full)) {

				adjacent = true;
				break;
			}
		}

		// If Volcanic neighbor found, convert formed Cobblestone or Stone to their Volcanic Rock equivalent.
		if (adjacent) {
			BlockState here = world.getBlockState(pos);

			if (here.isOf(Blocks.COBBLESTONE)) {
				// NB: As of 1.19.2 this is unused; Mojang code is funny that way.
				// See also MixinFluidBlock for the currently-effective implementation.
				world.setBlockState(pos, TerrestriaBlocks.VOLCANIC_ROCK.cobblestone.full.getDefaultState(), 3);
			} else if (here.isOf(Blocks.STONE)) {
				world.setBlockState(pos, TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState(), 3);
			}
		}
	}
}
