package com.terraformersmc.terrestria.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TrunkPlacerType.class)
public interface TrunkPlacerTypeAccessor {

	@Invoker(value = "<init>")
	static <P extends TrunkPlacer> TrunkPlacerType<P> createTrunkPlacerType(Codec<P> codec) {
		throw new UnsupportedOperationException();
	}
}
