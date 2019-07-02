package net.coderbot.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import net.coderbot.terrestria.feature.TreeDefinition;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.function.Function;

public class RedwoodTreeFeatureMega extends ConiferTreeFeatureMega {
    public RedwoodTreeFeatureMega(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Mega tree) {
        super(function, notify, tree);
    }
}
