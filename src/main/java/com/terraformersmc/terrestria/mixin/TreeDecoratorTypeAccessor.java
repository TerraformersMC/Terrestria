package com.terraformersmc.terrestria.mixin;

import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.gen.decorator.TreeDecorator;
import net.minecraft.world.gen.decorator.TreeDecoratorType;

@Mixin(TreeDecoratorType.class)
public interface TreeDecoratorTypeAccessor {
	@Invoker(value = "<init>")
	static <P extends TreeDecorator> TreeDecoratorType<P> createTreeDecoratorType(Codec<P> codec) {
		throw new UnsupportedOperationException();
	}
}
