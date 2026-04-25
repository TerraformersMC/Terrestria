package com.terraformersmc.terrestria.feature.helpers.shapes;

import java.util.Set;

import com.terraformersmc.terraform.shapes.api.Filler;

import com.terraformersmc.terraform.shapes.api.Position;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelWriter;

public class SetFiller implements Filler {
    private final LevelWriter world;
    private final BlockState state;
    private final int flags;
	private final Set<BlockPos> set;

	public SetFiller(LevelWriter world, BlockState state, int flags, Set<BlockPos> set) {
        this.world = world;
        this.state = state;
        this.flags = flags;
		this.set = set;
	}

    public SetFiller(LevelWriter world, BlockState state, Set<BlockPos> set) {
        this(world, state, 3, set);
    }

    public static SetFiller of(LevelWriter world, BlockState state, int flags, Set<BlockPos> set) {
        return new SetFiller(world, state, flags, set);
    }

    public static SetFiller of(LevelWriter world, BlockState state, Set<BlockPos> set) {
        return new SetFiller(world, state, set);
    }

    public void accept(Position position) {
		BlockPos pos = position.toBlockPos();
		set.add(pos);
        this.world.setBlock(pos, this.state, this.flags);
    }
}
