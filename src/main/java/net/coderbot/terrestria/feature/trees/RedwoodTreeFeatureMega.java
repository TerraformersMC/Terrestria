package net.coderbot.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import net.coderbot.terrestria.feature.TreeDefinition;
import net.coderbot.terrestria.feature.trees.templates.ConiferTreeFeatureMega;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.function.Function;

public class RedwoodTreeFeatureMega extends ConiferTreeFeatureMega {
	public RedwoodTreeFeatureMega(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Mega tree) {
		super(function, notify, tree);
	}
}
