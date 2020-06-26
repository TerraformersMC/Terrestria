package com.terraformersmc.terrestria.feature.tree.treedecorators;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTreeDecorators;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.decorator.TreeDecorator;
import net.minecraft.world.gen.decorator.TreeDecoratorType;

public class DanglingLeavesTreeDecorator extends TreeDecorator {
	public static final Codec<DanglingLeavesTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockState.CODEC.fieldOf("state").forGetter(decorator -> decorator.state))
			.apply(instance, DanglingLeavesTreeDecorator::new));
	private final BlockState state;

	public DanglingLeavesTreeDecorator(BlockState state) {
		this.state = state;
	}

	@Override
	protected TreeDecoratorType<?> getType() {
		return TerrestriaTreeDecorators.DANGLING_LEAVES;
	}

	@Override
	public void generate(WorldAccess world, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions, Set<BlockPos> set, BlockBox box) {
		for (BlockPos pos : leavesPositions) {
			if (world.getBlockState(pos.down()).isAir()) {
				//if there is air below, then make dangling leaves 33% of the time
				if (random.nextInt(3) == 0) {

					//iterate downwards and place leaves if air is present
					BlockPos.Mutable mutable = pos.mutableCopy();
					for (int i = 0; i < random.nextInt(3) + 1; i++) {
						mutable.move(Direction.DOWN);
						if (world.getBlockState(mutable).isAir()) {
							world.setBlockState(mutable, state, 3);
						} else {
							break;
						}
					}
				}
			}
		}
	}
}
