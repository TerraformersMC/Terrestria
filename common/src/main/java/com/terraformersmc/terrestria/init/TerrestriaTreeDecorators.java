package com.terraformersmc.terrestria.init;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.treedecorators.SakuraTreeDecorator;
import com.terraformersmc.terrestria.mixin.TreeDecoratorTypeAccessor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class TerrestriaTreeDecorators {

	public static TreeDecoratorType<DanglingLeavesTreeDecorator> DANGLING_LEAVES;
	public static TreeDecoratorType<SakuraTreeDecorator> SAKURA;

	public static void init() {
		DANGLING_LEAVES = register("dangling_leaves_tree_decorator", DanglingLeavesTreeDecorator.CODEC);
		SAKURA = register("sakura_tree_decorator", SakuraTreeDecorator.CODEC);
	}

	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String name, MapCodec<P> codec) {
		return Registry.register(Registries.TREE_DECORATOR_TYPE, new Identifier(Terrestria.MOD_ID, name), TreeDecoratorTypeAccessor.createTreeDecoratorType(codec));
	}
}
