package net.coderbot.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import net.coderbot.terrestria.feature.TreeDefinition;
import net.coderbot.terrestria.feature.trees.templates.ConiferTreeFeature;
import net.coderbot.terrestria.feature.trees.templates.ConiferTreeFeatureNew;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.function.Function;

public class HemlockTreeFeature extends ConiferTreeFeatureNew {
    public HemlockTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
        super(function, notify, tree);
    }
}
