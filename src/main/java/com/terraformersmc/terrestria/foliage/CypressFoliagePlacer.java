package com.terraformersmc.terrestria.foliage;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;

import java.util.Random;
import java.util.Set;

public class CypressFoliagePlacer extends FoliagePlacer {
	public CypressFoliagePlacer(int radius, int randomRadius) {
		super(radius, randomRadius, TerrestriaFoliagePlacers.CYPRESS_FOLIAGE_PLACER);
	}

	public <T> CypressFoliagePlacer(Dynamic<T> dynamic) {
		this(dynamic.get("radius").asInt(0), dynamic.get("radius_random").asInt(0));
	}

	// generate
	public void method_23448(ModifiableTestableWorld modifiableTestableWorld, Random random, BranchedTreeFeatureConfig branchedBranchedTreeFeatureConfig, int height, int trunkHeight, int radius, BlockPos blockPos, Set<BlockPos> set) {
		this.method_23449(modifiableTestableWorld, random, branchedBranchedTreeFeatureConfig, height, blockPos, 0, radius, set);
		this.method_23449(modifiableTestableWorld, random, branchedBranchedTreeFeatureConfig, height, blockPos, 1, 1, set);
		BlockPos blockPos2 = blockPos.up();

		for(int n = -1; n <= 1; n++) {
			for(int m = -1; m <= 1; m++) {
				this.method_23450(modifiableTestableWorld, random, blockPos2.add(n, 0, m), branchedBranchedTreeFeatureConfig, set);
			}
		}

		for(int n = 2; n <= radius - 1; n++) {
			this.method_23450(modifiableTestableWorld, random, blockPos2.east(n), branchedBranchedTreeFeatureConfig, set);
			this.method_23450(modifiableTestableWorld, random, blockPos2.west(n), branchedBranchedTreeFeatureConfig, set);
			this.method_23450(modifiableTestableWorld, random, blockPos2.south(n), branchedBranchedTreeFeatureConfig, set);
			this.method_23450(modifiableTestableWorld, random, blockPos2.north(n), branchedBranchedTreeFeatureConfig, set);
		}

	}

	// radius
	public int method_23452(Random random, int i, int j, BranchedTreeFeatureConfig branchedBranchedTreeFeatureConfig) {
		return this.radius + random.nextInt(this.randomRadius + 1);
	}

	// shouldSkip
	protected boolean method_23451(Random random, int height, int offsetX, int offsetY, int offsetZ, int radius) {
		return Math.abs(offsetX) == radius && Math.abs(offsetZ) == radius && radius > 0;
	}

	// boundingRadius
	public int method_23447(int i, int j, int k, int l) {
		return l == 0 ? 0 : 2;
	}
}
