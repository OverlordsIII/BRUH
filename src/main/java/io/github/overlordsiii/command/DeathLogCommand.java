package io.github.overlordsiii.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import io.github.overlordsiii.BRUH;
import io.github.overlordsiii.deathlog.DeathLogEntry;
import io.github.overlordsiii.deathlog.TimeEntry;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class DeathLogCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher
			.register(literal("deathlog")
				.then(literal("query")
					.executes(DeathLogCommand::sendDeathLog)));
	}

	private static int sendDeathLog(CommandContext<ServerCommandSource> context) {
		for (DeathLogEntry deathLogEntry : BRUH.DEATH_LOG_CONFIG.entries) {
			String text = deathLogEntry.getDamageText() + ". Time: " + deathLogEntry.getTimeOfDeath().getString() + ". Coords: " + deathLogEntry.getCoords() + " in " + deathLogEntry.getDimension().getPath();
			if (context.getSource().getPlayer() != null) {
				context.getSource().getPlayer().sendMessage(Text.literal(text));
			}

			BRUH.LOGGER.info(text);
		}

		return 1;
	}
}
