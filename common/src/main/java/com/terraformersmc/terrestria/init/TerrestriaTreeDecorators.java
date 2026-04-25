package com.terraformersmc.terrestria.init;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terraform.tree.api.decorator.DecoratorTypes;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.treedecorators.SakuraTreeDecorator;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class TerrestriaTreeDecorators {

	public static TreeDecoratorType<DanglingLeavesTreeDecorator> DANGLING_LEAVES;
	public static TreeDecoratorType<SakuraTreeDecorator> SAKURA;

	public static void init() {
		DANGLING_LEAVES = register("dangling_leaves_tree_decorator", DanglingLeavesTreeDecorator.CODEC);
		SAKURA = register("sakura_tree_decorator", SakuraTreeDecorator.CODEC);
	}

	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String name, MapCodec<P> codec) {
		return DecoratorTypes.registerTreeDecorator(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, name), codec);
	}
}
