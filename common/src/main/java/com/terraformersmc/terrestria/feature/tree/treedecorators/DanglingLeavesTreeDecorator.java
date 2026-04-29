package com.terraformersmc.terrestria.feature.tree.treedecorators;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTreeDecorators;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class DanglingLeavesTreeDecorator extends TreeDecorator {
	public static final MapCodec<DanglingLeavesTreeDecorator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			BlockState.CODEC.fieldOf("state").forGetter(decorator -> decorator.state))
			.apply(instance, DanglingLeavesTreeDecorator::new));
	private final BlockState state;

	public DanglingLeavesTreeDecorator(BlockState state) {
		this.state = state;
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return TerrestriaTreeDecorators.DANGLING_LEAVES;
	}

	@Override
	public void place(Context generator) {
		RandomSource random = generator.random();
		LevelSimulatedReader world = generator.level();

		for (BlockPos pos : generator.leaves()) {
			if (world.isStateAtPosition(pos.below(), BlockBehaviour.BlockStateBase::isAir)) {
				// If there is air below, then make dangling leaves 33% of the time
				if (random.nextInt(3) == 0) {

					// Iterate downwards and place leaves if air is present
					BlockPos.MutableBlockPos mutable = pos.mutable();
					for (int i = 0; i < random.nextInt(3) + 1; i++) {
						mutable.move(Direction.DOWN);
						if (world.isStateAtPosition(mutable, BlockBehaviour.BlockStateBase::isAir)) {
							generator.setBlock(mutable, state);
						} else {
							break;
						}
					}
				}
			}
		}
	}
}
