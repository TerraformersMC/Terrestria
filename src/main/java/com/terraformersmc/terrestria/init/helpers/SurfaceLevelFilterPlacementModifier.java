package com.terraformersmc.terrestria.init.helpers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import org.jspecify.annotations.NullMarked;

import java.util.function.Consumer;

@NullMarked
public class SurfaceLevelFilterPlacementModifier implements PlacementFilter {
	public static final MapCodec<SurfaceLevelFilterPlacementModifier> CODEC =
			RecordCodecBuilder.mapCodec(instance -> instance.group(
					Heightmap.Types.CODEC.fieldOf("heightmap").forGetter(arg -> arg.heightmap),
					Codec.INT.optionalFieldOf("min_inclusive", Integer.MIN_VALUE).forGetter(arg -> arg.min),
					Codec.INT.optionalFieldOf("max_inclusive", Integer.MAX_VALUE).forGetter(arg -> arg.max))
				.apply(instance, SurfaceLevelFilterPlacementModifier::new));
	private final Heightmap.Types heightmap;
	private final int min;
	private final int max;

	private SurfaceLevelFilterPlacementModifier(Heightmap.Types heightmap, int min, int max) {
		this.heightmap = heightmap;
		this.min = min;
		this.max = max;
	}

	public static SurfaceLevelFilterPlacementModifier of(Heightmap.Types heightmap, int min, int max) {
		return new SurfaceLevelFilterPlacementModifier(heightmap, min, max);
	}

	@Override
	public MapCodec<? extends PlacementFilter> codec() {
		return CODEC;
	}

	@Override
	public void modify(PlacementContext context, RandomSource random, BlockPos origin, Consumer<BlockPos> output) {
		PlacementFilter.super.modify(context, random, origin, output);
	}

	@Override
	public boolean shouldPlace(PlacementContext context, RandomSource random, BlockPos pos) {
		long height = context.getHeight(this.heightmap, pos.getX(), pos.getZ());

		return this.min <= height && this.max >= height;
	}
}
