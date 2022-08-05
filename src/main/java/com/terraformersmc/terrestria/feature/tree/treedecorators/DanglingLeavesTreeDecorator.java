package com.terraformersmc.terrestria.feature.tree.treedecorators;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTreeDecorators;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

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
	public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
		for (BlockPos pos : leavesPositions) {
			if (world.testBlockState(pos.down(), state -> state.isAir())) {
				//if there is air below, then make dangling leaves 33% of the time
				if (random.nextInt(3) == 0) {

					//iterate downwards and place leaves if air is present
					BlockPos.Mutable mutable = pos.mutableCopy();
					for (int i = 0; i < random.nextInt(3) + 1; i++) {
						mutable.move(Direction.DOWN);
						if (world.testBlockState(mutable, state -> state.isAir())) {
							replacer.accept(mutable, state);
						} else {
							break;
						}
					}
				}
			}
		}
	}
}
