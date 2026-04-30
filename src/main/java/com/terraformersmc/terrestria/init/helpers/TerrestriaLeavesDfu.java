package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.leaves.api.data.TerraformLeavesDfuApi;

import java.util.Collection;
import java.util.Set;

public class TerrestriaLeavesDfu implements TerraformLeavesDfuApi {
	@Override
	public Collection<String> getDfuExtendedLeavesIds() {
		return Set.of(
				"terrestria:hemlock_leaves",
				"terrestria:redwood_leaves",
				"terrestria:sakura_leaves",
				"terrestria:yucca_palm_leaves"
		);
	}
}
