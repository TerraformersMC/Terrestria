package net.coderbot.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import net.coderbot.terrestria.feature.TreeDefinition;
import net.coderbot.terrestria.feature.trees.templates.ShrubFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.function.Function;

public class JapaneseMapleShrubFeature extends ShrubFeature {
    public JapaneseMapleShrubFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
        super(function, notify, tree);
    }
}
