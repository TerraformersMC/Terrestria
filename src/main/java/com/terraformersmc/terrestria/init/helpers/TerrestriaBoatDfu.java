package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.boat.api.data.TerraformBoatDfuApi;

import java.util.Collection;
import java.util.Set;

public class TerrestriaBoatDfu implements TerraformBoatDfuApi {
	@Override
	public Collection<String> getDfuBoatIds() {
		return Set.of(
				"terrestria:cypress_boat", "terrestria:cypress_chest_boat",
				"terrestria:hemlock_boat", "terrestria:hemlock_chest_boat",
				"terrestria:japanese_maple_boat", "terrestria:japanese_maple_chest_boat",
				"terrestria:rainbow_eucalyptus_boat", "terrestria:rainbow_eucalyptus_chest_boat",
				"terrestria:redwood_boat", "terrestria:redwood_chest_boat",
				"terrestria:rubber_boat", "terrestria:rubber_chest_boat",
				"terrestria:sakura_boat", "terrestria:sakura_chest_boat",
				"terrestria:willow_boat", "terrestria:willow_chest_boat",
				"terrestria:yucca_palm_boat", "terrestria:yucca_palm_chest_boat"
		);
	}
}
