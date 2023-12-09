package com.terraformersmc.terrestria.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FluidBlock.class)
public class MixinFluidBlock {
	@WrapOperation(method = "receiveNeighborFluids",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z",
					ordinal = 0
			)
	)
	@SuppressWarnings("unused")
	private boolean terrestria$generateVolcanicCobblestone(World world, BlockPos pos, BlockState newState, Operation<Boolean> original) {
		// This is the cobble generation pathway; see also: MixinLavaFluid.

		// In this method, newState can alternatively be Obsidian.
		if (newState.isOf(Blocks.COBBLESTONE)) {
			// Search immediately adjacent blocks for Volcanic Rock variants.
			for (Direction towards : Direction.values()) {
				BlockState neighbor = world.getBlockState(pos.offset(towards));

				if (neighbor.isOf(TerrestriaBlocks.VOLCANIC_ROCK.plain.full) ||
						neighbor.isOf(TerrestriaBlocks.VOLCANIC_ROCK.cobblestone.full) ||
						neighbor.isOf(TerrestriaBlocks.VOLCANIC_ROCK.bricks.full)) {

					// If Volcanic neighbor found, convert to Volcanic Cobblestone.
					newState = TerrestriaBlocks.VOLCANIC_ROCK.cobblestone.full.getDefaultState();
					break;
				}
			}
		}

		return original.call(world, pos, newState);
	}
}
