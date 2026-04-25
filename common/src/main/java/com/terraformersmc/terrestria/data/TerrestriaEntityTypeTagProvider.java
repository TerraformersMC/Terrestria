package com.terraformersmc.terrestria.data;

import com.terraformersmc.terraform.boat.api.data.TerraformBoatData;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class TerrestriaEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
	protected TerrestriaEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void addTags(HolderLookup.Provider registries) {
		/*
		 * Wood items
		 */
		addWood(TerrestriaItems.CYPRESS);
		addWood(TerrestriaItems.HEMLOCK);
		addWood(TerrestriaItems.JAPANESE_MAPLE);
		addWood(TerrestriaItems.RAINBOW_EUCALYPTUS);
		addWood(TerrestriaItems.REDWOOD);
		addWood(TerrestriaItems.RUBBER);
		addWood(TerrestriaItems.SAKURA);
		addWood(TerrestriaItems.WILLOW);
		addWood(TerrestriaItems.YUCCA_PALM);
	}

	private void addWood(WoodItems woodItem) {
		// Add boats if they exist via the WoodItem.
		TerraformBoatData boatData = TerraformBoatData.get(woodItem.getId());

		if (woodItem.boat != null) {
			getOrCreateRawBuilder(EntityTypeTags.BOAT).addElement(boatData.boatEntityTypeId());
		}
		if (woodItem.chestBoat != null) {
			getOrCreateRawBuilder(ConventionalEntityTypeTags.BOATS).addElement(boatData.chestBoatEntityTypeId());
		}
	}

	@Override
	public String getName() {
		return "Terrestria Entity Type Tags";
	}
}
