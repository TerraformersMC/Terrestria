package com.terraformersmc.terrestria.feature.helpers.shapes;

import java.util.Set;

import com.terraformersmc.terraform.shapes.api.Filler;

import com.terraformersmc.terraform.shapes.api.Position;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableWorld;

public class SetFiller implements Filler {
    private final ModifiableWorld world;
    private final BlockState state;
    private final int flags;
	private final Set<BlockPos> set;

	public SetFiller(ModifiableWorld world, BlockState state, int flags, Set<BlockPos> set) {
        this.world = world;
        this.state = state;
        this.flags = flags;
		this.set = set;
	}

    public SetFiller(ModifiableWorld world, BlockState state, Set<BlockPos> set) {
        this(world, state, 3, set);
    }

    public static SetFiller of(ModifiableWorld world, BlockState state, int flags, Set<BlockPos> set) {
        return new SetFiller(world, state, flags, set);
    }

    public static SetFiller of(ModifiableWorld world, BlockState state, Set<BlockPos> set) {
        return new SetFiller(world, state, set);
    }

    public void accept(Position position) {
		BlockPos pos = position.toBlockPos();
		set.add(pos);
        this.world.setBlockState(pos, this.state, this.flags);
    }
}
