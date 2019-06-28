package net.coderbot.terrestria;

import net.coderbot.terrestria.command.LocateAny;
import net.coderbot.terrestria.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Terrestria implements ModInitializer {
	public static final String MOD_ID = "terrestria";
	public static ItemGroup ITEM_GROUP;

	@Override
	public void onInitialize() {
		ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "items"), () -> new ItemStack(TerrestriaItems.RUBBER_SAPLING));

		TerrestriaBlocks.init();
		TerrestriaItems.init();
		TerrestriaFeatures.init();
		TerrestriaSurfaces.init();
		TerrestriaBiomes.init();

		LocateAny.register();

		Registry.BIOME.forEach(TerrestriaBiomes::addVolcanoStructure);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((rawId, id, biome) -> TerrestriaBiomes.addVolcanoStructure(biome));
	}
}
