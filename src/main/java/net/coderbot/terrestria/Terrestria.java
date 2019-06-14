package net.coderbot.terrestria;

import net.coderbot.terrestria.command.FindBiomeCommand;
import net.coderbot.terrestria.init.TerrestriaBiomes;
import net.coderbot.terrestria.init.TerrestriaBlocks;
import net.coderbot.terrestria.init.TerrestriaItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Terrestria implements ModInitializer {
	public static ItemGroup ITEM_GROUP;

	@Override
	public void onInitialize() {
		ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier("terrestria", "items"), () -> new ItemStack(TerrestriaItems.RUBBER_SAPLING));

		TerrestriaBlocks.init();
		TerrestriaItems.init();
		TerrestriaBiomes.init();

		CommandRegistry.INSTANCE.register(false, FindBiomeCommand::register);
	}
}
