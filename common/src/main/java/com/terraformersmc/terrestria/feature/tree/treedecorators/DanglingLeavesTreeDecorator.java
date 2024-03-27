package com.terraformersmc.terrestria.feature.tree.treedecorators;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTreeDecorators;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class DanglingLeavesTreeDecorator extends TreeDecorator {
	public static final MapCodec<DanglingLeavesTreeDecorator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
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
	public void generate(Generator generator) {
		Random random = generator.getRandom();
		TestableWorld world = generator.getWorld();

		for (BlockPos pos : generator.getLeavesPositions()) {
			if (world.testBlockState(pos.down(), AbstractBlock.AbstractBlockState::isAir)) {
				// If there is air below, then make dangling leaves 33% of the time
				if (random.nextInt(3) == 0) {

					// Iterate downwards and place leaves if air is present
					BlockPos.Mutable mutable = pos.mutableCopy();
					for (int i = 0; i < random.nextInt(3) + 1; i++) {
						mutable.move(Direction.DOWN);
						if (world.testBlockState(mutable, AbstractBlock.AbstractBlockState::isAir)) {
							generator.replace(mutable, state);
						} else {
							break;
						}
					}
				}
			}
		}
	}
}
