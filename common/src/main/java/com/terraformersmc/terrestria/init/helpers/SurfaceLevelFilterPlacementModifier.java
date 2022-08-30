package com.terraformersmc.terrestria.init.helpers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class SurfaceLevelFilterPlacementModifier extends AbstractConditionalPlacementModifier {
	public static final Codec<SurfaceLevelFilterPlacementModifier> MODIFIER_CODEC =
			RecordCodecBuilder.create(instance -> instance.group(
					Heightmap.Type.CODEC.fieldOf("heightmap").forGetter(arg -> arg.heightmap),
					Codec.INT.optionalFieldOf("min_inclusive", Integer.MIN_VALUE).forGetter(arg -> arg.min),
					Codec.INT.optionalFieldOf("max_inclusive", Integer.MAX_VALUE).forGetter(arg -> arg.max))
				.apply(instance, SurfaceLevelFilterPlacementModifier::new));
	private final Heightmap.Type heightmap;
	private final int min;
	private final int max;

	private SurfaceLevelFilterPlacementModifier(Heightmap.Type heightmap, int min, int max) {
		this.heightmap = heightmap;
		this.min = min;
		this.max = max;
	}

	public static SurfaceLevelFilterPlacementModifier of(Heightmap.Type heightmap, int min, int max) {
		return new SurfaceLevelFilterPlacementModifier(heightmap, min, max);
	}

	@Override
	protected boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
		long l = context.getTopY(this.heightmap, pos.getX(), pos.getZ());
		return this.min <= l && this.max >= l;
	}

	@Override
	public PlacementModifierType<?> getType() {
		return TerrestriaPlacementModifierType.SURFACE_LEVEL_FILTER;
	}
}
