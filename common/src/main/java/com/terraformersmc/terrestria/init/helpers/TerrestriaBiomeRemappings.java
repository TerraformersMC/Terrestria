package com.terraformersmc.terrestria.init.helpers;

import com.google.common.collect.ImmutableMap;
import com.terraformersmc.terraform.biomeremapper.api.BiomeRemapperApi;
import com.terraformersmc.terraform.biomeremapper.api.DataVersions;
import com.terraformersmc.terrestria.Terrestria;

public class TerrestriaBiomeRemappings implements BiomeRemapperApi {
	public void init() {
		register(Terrestria.MOD_ID, DataVersions.V_1_18_2, ImmutableMap.<String, String>builder()
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
				.build());
	}
}
