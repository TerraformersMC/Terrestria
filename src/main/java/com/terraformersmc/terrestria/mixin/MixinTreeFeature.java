package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.feature.tree.treeconfigs.helpers.ExtendedTreeGeneration;
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
	@Shadow
	protected abstract int method_29963(TestableWorld testableWorld, int i, BlockPos blockPos, TreeFeatureConfig treeFeatureConfig);

	// Bypasses the "no default constructor" error, will never actually get used
	private MixinTreeFeature() {
		super(null);
		throw new UnsupportedOperationException();
	}

	@Inject(method = "generate(Lnet/minecraft/world/ModifiableTestableWorld;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Ljava/util/Set;Ljava/util/Set;Lnet/minecraft/util/math/BlockBox;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/TreeFeature;isDirtOrGrass(Lnet/minecraft/world/TestableWorld;Lnet/minecraft/util/math/BlockPos;)Z"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
	private void onGenerate(ModifiableTestableWorld world, Random random, BlockPos pos, Set logPositions, Set leavesPositions, BlockBox box, TreeFeatureConfig config, CallbackInfoReturnable<Boolean> cir, int i, int j, int k, int l, BlockPos blockPos2) {
		if ((config instanceof ExtendedTreeGeneration)) {
			if (((ExtendedTreeGeneration) config).canGenerateOn(world, blockPos2.down())) {
				OptionalInt minClippedHeight = config.minimumSize.getMinClippedHeight();
				int r = this.method_29963(world, i, blockPos2, config);
				if (r >= i || minClippedHeight.isPresent() && r >= minClippedHeight.getAsInt()) {
					List<FoliagePlacer.TreeNode> list = config.trunkPlacer.generate(world, random, r, blockPos2, logPositions, box, config);
					list.forEach((treeNode) -> config.foliagePlacer.generate(world, random, config, r, treeNode, j, l, leavesPositions, box));
					cir.setReturnValue(true);
				} else {
					cir.setReturnValue(false);
				}
			} else {
				cir.setReturnValue(false);
			}
		}
	}
}
