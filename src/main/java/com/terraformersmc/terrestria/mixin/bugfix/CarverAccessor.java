package com.terraformersmc.terrestria.mixin.bugfix;

import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.Block;
import net.minecraft.world.gen.carver.Carver;

@Mixin(Carver.class)
public interface CarverAccessor {
	@Accessor
	Set<Block> getAlwaysCarvableBlocks();

	@Accessor
	void setAlwaysCarvableBlocks(Set<Block> alwaysCarvable);
}
