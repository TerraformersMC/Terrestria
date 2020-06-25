package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.feature.treeconfigs.helpers.SandyTree;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;

@Mixin(TreeFeature.class)
public abstract class MixinTreeFeature<FC extends FeatureConfig> extends Feature<FC> {
	// Bypasses the "no default constructor" error, will never actually get used
	private MixinTreeFeature() {
		super(null);

		throw new UnsupportedOperationException();
	}

	@Inject(method = "isDirtOrGrass", at = @At("RETURN"), cancellable = true)
	private static void hookIsNaturalDirt(TestableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
		if (world.testBlockState(pos, state -> {
			Block block = state.getBlock();
			return block == TerrestriaBlocks.BASALT_GRASS_BLOCK;
		})) {
			callback.setReturnValue(true);
		}
	}

	//TODO make trees grow on sand sometimes
/*	private boolean isSandy(ModifiableTestableWorld world, BlockPos pos) {
		return (world.testBlockState(pos, (state) -> {
			Block block = state.getBlock();
			return block == Blocks.SAND || block == Blocks.RED_SAND || block == TerrestriaBlocks.BASALT_SAND;
		}));
	}

	@Inject(method = "generate(Lnet/minecraft/world/ModifiableTestableWorld;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Ljava/util/Set;Ljava/util/Set;Lnet/minecraft/util/math/BlockBox;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/TreeFeature;isDirtOrGrass(Lnet/minecraft/world/TestableWorld;Lnet/minecraft/util/math/BlockPos;)Z"), locals = LocalCapture.PRINT, cancellable = true)
	private void isSandyBlock(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, TreeFeatureConfig config, CallbackInfoReturnable<Boolean> cir) {
		if ((config instanceof SandyTree) || isSandy(world, blockPos2.down())) {
			OptionalInt optionalInt = config.minimumSize.getMinClippedHeight();
			r = this.method_29963(world, i, blockPos2, config);
			if (r >= i || optionalInt.isPresent() && r >= optionalInt.getAsInt()) {
				List<FoliagePlacer.TreeNode> list = config.trunkPlacer.generate(world, random, r, blockPos2, logPositions, box, config);
				list.forEach((treeNode) -> {
					config.foliagePlacer.generate(world, random, config, r, treeNode, j, l, leavesPositions, box);
				});
				cir.setReturnValue(true);
			} else {
				cir.setReturnValue(false);
			}
		} else {
			cir.setReturnValue(false);
		}
	}*/

	// TODO: Basalt farmland when added
	/*@Inject(method = "isDirtOrGrass", at = @At("HEAD"), cancellable = true)
	private static void hookIsDirtOrGrass(TestableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
		if (world.testBlockState(pos, state -> {
			Block block = state.getBlock();
			return block == TerrestriaBlocks.BASALT_FARMLAND;
		})) {
			callback.setReturnValue(true);
		}
	}*/
}
