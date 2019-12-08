package com.terraformersmc.terrestria.mixin;

import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.decorator.TreeDecorator;
import net.minecraft.world.gen.decorator.TreeDecoratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Function;

//@Mixin(TreeDecoratorType.class)
public interface MixinTreeDecoratorType {
	//@Invoker("<init>")
	static <P extends TreeDecorator> TreeDecoratorType<P> create(Function<Dynamic<?>, P> function) {
		throw new UnsupportedOperationException();
	}
}
