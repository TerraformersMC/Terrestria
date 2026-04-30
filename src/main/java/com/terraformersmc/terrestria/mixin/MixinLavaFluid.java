package com.terraformersmc.terrestria.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LavaFluid.class)
public class MixinLavaFluid {
	@WrapOperation(method = "spreadTo",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/LevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z",
					ordinal = 0
			)
	)
	@SuppressWarnings("unused")
	private boolean terrestria$generateVolcanicRock(LevelAccessor level, BlockPos pos, BlockState newState, int flags, Operation<Boolean> original) {
		// This is the stone generation pathway; see also: MixinFluidBlock.

		// Search immediately adjacent blocks for Volcanic Rock variants.
		for (Direction towards : Direction.values()) {
			BlockState neighbor = level.getBlockState(pos.relative(towards));

			if (neighbor.is(TerrestriaBlocks.VOLCANIC_ROCK.plain.full) ||
					neighbor.is(TerrestriaBlocks.VOLCANIC_ROCK.cobblestone.full) ||
					neighbor.is(TerrestriaBlocks.VOLCANIC_ROCK.bricks.full)) {

				// If Volcanic neighbor found, convert to Volcanic Rock.
				newState = TerrestriaBlocks.VOLCANIC_ROCK.plain.full.defaultBlockState();
				break;
			}
		}

		return original.call(level, pos, newState, flags);
	}
}
