package net.coderbot.terrestria.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.feature.Feature;

/**
 * Extended version of /locate to locate any structure
 */
public class LocateAny {
    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("commands.locate.failed"));

	public static void register() {
		CommandRegistry.INSTANCE.register(false, dispatcher -> {
			LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal("locateany").requires(source ->
					source.hasPermissionLevel(2));
			Feature.STRUCTURES.keySet().forEach(structure -> builder.then(CommandManager.literal(structure)
					.executes(context -> execute(context.getSource(), structure))));
			dispatcher.register(builder);
		});
	}

	private static int execute(ServerCommandSource source, String structure) throws CommandSyntaxException {
        BlockPos position = new BlockPos(source.getPosition());
        BlockPos found = source.getWorld().locateStructure(structure, position, 100, false);
        if (found == null) {
            throw FAILED_EXCEPTION.create();
        } else {
            int blockDistance = MathHelper.floor(getDistance(position.getX(), position.getZ(), found.getX(), found.getZ()));
            Text component_1 = Texts.bracketed(new TranslatableText("chat.coordinates", found.getX(), "~", found.getZ())).styled((style_1) ->
                style_1.setColor(Formatting.GREEN).setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp @s " + found.getX() + " ~ " + found.getZ())).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("chat.coordinates.tooltip")))
            );
            source.sendFeedback(new TranslatableText("commands.locate.success", structure, component_1, blockDistance), false);
            return blockDistance;
        }
	}

    private static double getDistance(int posX, int posZ, int biomeX, int biomeZ) {
        return MathHelper.sqrt(Math.pow(biomeX - posX, 2) + Math.pow(biomeZ - posZ, 2));
    }
}