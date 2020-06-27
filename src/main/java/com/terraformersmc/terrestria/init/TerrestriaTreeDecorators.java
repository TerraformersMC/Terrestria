package com.terraformersmc.terrestria.init;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.terraformersmc.terrestria.mixin.TreeDecoratorTypeAccessor;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.TreeDecorator;
import net.minecraft.world.gen.decorator.TreeDecoratorType;

public class TerrestriaTreeDecorators {

	public static TreeDecoratorType<DanglingLeavesTreeDecorator> DANGLING_LEAVES;

	public static void init() {
		DANGLING_LEAVES = register("dangling_leaves", DanglingLeavesTreeDecorator.CODEC);
	}

	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String name, Codec<P> codec) {
		return Registry.register(Registry.TREE_DECORATOR_TYPE, new Identifier(Terrestria.MOD_ID, name), TreeDecoratorTypeAccessor.createTreeDecoratorType(codec));
	}
}
