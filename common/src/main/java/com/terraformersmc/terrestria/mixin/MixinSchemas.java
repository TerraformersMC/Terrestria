package com.terraformersmc.terrestria.mixin;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.datafixer.Schemas;
import net.minecraft.datafixer.fix.BiomeRenameFix;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiFunction;

@Mixin(Schemas.class)
public class MixinSchemas {
	@Shadow
	@Final
	private static BiFunction<Integer, Schema, Schema> EMPTY_IDENTIFIER_NORMALIZE;

	@Inject(method = "build", at = @At("TAIL"))
	private static void injectTerrestriaDataFixers(DataFixerBuilder builder, CallbackInfo ci) {
		Schema terrestria1_18_2 = builder.addSchema(2970, EMPTY_IDENTIFIER_NORMALIZE);
		ImmutableMap<String, String> terrestria1_18_2_biome_map = ImmutableMap.<String, String>builder()
				.put("terrestria:dense_woodlands_edge", "minecraft:forest")
				.put("terrestria:dunes_edge", "minecraft:desert")
				.put("terrestria:hemlock_clearing", "terrestria:hemlock_treeline")
				.put("terrestria:lush_redwood_clearing", "terrestria:lush_redwood_forest")
				.put("terrestria:lush_redwood_forest_edge", "terrestria:lush_redwood_forest")
				.put("terrestria:outback_bushland", "terrestria:outback")
				.put("terrestria:outback_uluru", "terrestria:outback")
				.put("terrestria:rainbow_rainforest_lake", "terrestria:rainbow_rainforest")
				.put("terrestria:rainbow_rainforest_mountains", "terrestria:rainbow_rainforest")
				.put("terrestria:redwood_clearing", "terrestria:redwood_forest")
				.put("terrestria:redwood_forest_edge", "terrestria:redwood_forest")
				.put("terrestria:snowy_hemlock_clearing", "terrestria:snowy_hemlock_treeline")
				.put("terrestria:wooded_cypress_hills", "terrestria:cypress_forest")
				.put("terrestria:wooded_japanese_maple_hills", "terrestria:japanese_maple_forest")
				.put("terrestria:wooded_sakura_hills", "terrestria:sakura_forest")
				.build();
		builder.addFixer(new BiomeRenameFix(terrestria1_18_2, false, "Terrestria 1.18.2 remove biome variants and superseded biomes", terrestria1_18_2_biome_map));
	}
}
