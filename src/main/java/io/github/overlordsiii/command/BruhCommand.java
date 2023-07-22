package io.github.overlordsiii.command;

import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import io.github.overlordsiii.BRUH;
import io.github.overlordsiii.command.suggestion.EnumSuggestionProvider;
import io.github.overlordsiii.config.BRUHGeneralConfig;
import io.github.overlordsiii.config.DeathCoordinateMessageType;
import io.github.overlordsiii.config.Modules;
import io.github.overlordsiii.config.SheepWoolChances;
import io.github.overlordsiii.config.module.ColorfulSheepModule;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;

import java.lang.reflect.Field;


public class BruhCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(literal("bruh")
			.requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
				.then(literal("toggle")
					.then(argument("module", StringArgumentType.greedyString())
						.suggests(new EnumSuggestionProvider<>(Modules.values()))
							.executes(context -> executeToggleModule(StringArgumentType.getString(context, "module"), context))))
			.then(literal("safeCactusItems")
				.then(argument("safeCactusItems", BoolArgumentType.bool())
					.executes(context -> executeSetSafeCactusItems(context, BoolArgumentType.getBool(context, "safeCactusItems")))))
			.then(literal("sheepWoolChances")
				.then(argument("sheepWoolType", StringArgumentType.word())
					.suggests(new EnumSuggestionProvider<>(SheepWoolChances.values()))
						.then(argument("woolChance", DoubleArgumentType.doubleArg(0, 100))
							.executes(context -> executeSetSheepChances(context, StringArgumentType.getString(context, "sheepWoolType"), DoubleArgumentType.getDouble(context, "woolChance"))))))
			.then(literal("deathMsgType")
				.then(argument("deathMsgType", StringArgumentType.word())
					.suggests(new EnumSuggestionProvider<>(DeathCoordinateMessageType.values()))
						.executes(context -> executeSetDeathMessage(context, StringArgumentType.getString(context, "deathMsgType")))))
			.then(literal("leapOfFaithReduction")
				.then(argument("leapOfFaithReduction", DoubleArgumentType.doubleArg(0, 100))
					.executes(context -> executeSetLeapOfFaithReduction(context, DoubleArgumentType.getDouble(context, "leapOfFaithReduction"))))));
	}

	private static int executeSetLeapOfFaithReduction(CommandContext<ServerCommandSource> context, double leapOfFaithReduction) {
		BRUH.CONFIG.leapOfFaithModule.haybaleBlockReductionPercentage = leapOfFaithReduction;
		BRUH.CONFIG_MANAGER.save();

		context.getSource().sendFeedback(() -> Text.literal("Set leap of faith damage reduction on hay bales to: " + leapOfFaithReduction + "%"), true);
		return 1;
	}

	private static int executeSetDeathMessage(CommandContext<ServerCommandSource> context, String deathMsgType) {
		DeathCoordinateMessageType type = getValue(DeathCoordinateMessageType.class, deathMsgType);

		if (type == null) {
			context.getSource().sendFeedback(() -> Text.literal("Please enter a correct death coordinate message type!"), false);
			return -1;
		}

		BRUH.CONFIG.deathCoordinatesModule.msgType = type;
		BRUH.CONFIG_MANAGER.save();

		context.getSource().sendFeedback(() -> Text.literal("Set Death Coordinate Message Type to: " + type), true);

		return 1;
	}

	private static int executeSetSheepChances(CommandContext<ServerCommandSource> context, String sheepWoolType, double woolChance) {
		SheepWoolChances sheepWoolChances = getValue(SheepWoolChances.class, sheepWoolType);

		if (sheepWoolChances == null) {
			context.getSource().sendFeedback(() -> Text.literal("Please enter a correct wool type"), false);
			return -1;
		}

		String fieldName = sheepWoolChances.name().replace("_", "").toLowerCase() + "Chance";

		try {
			Field field = ColorfulSheepModule.class.getField(fieldName);
			field.setDouble(BRUH.CONFIG.colorfulSheepModule, woolChance);

			context.getSource().sendFeedback(() -> Text.literal("Set " + sheepWoolChances + " to " + woolChance + "%. Requires server restart to take effect."), true);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		BRUH.CONFIG_MANAGER.save();
		return 1;
	}

	private static int executeSetSafeCactusItems(CommandContext<ServerCommandSource> context, boolean safeCactusItems) {
		BRUH.CONFIG.safeCactusModule.safeItems = safeCactusItems;
		BRUH.CONFIG_MANAGER.save();

		context.getSource().sendFeedback(() -> Text.literal("Set Cactus Safe Items to " + safeCactusItems), true);
		return 1;
	}

	private static int executeToggleModule(String module, CommandContext<ServerCommandSource> context) {
		Modules modules = getValue(Modules.class, module);
		if (modules == null) {
			context.getSource().sendFeedback(() -> Text.literal("Please enter a correct module"), false);
			return -1;
		}

		try {
			Field field = BRUHGeneralConfig.class.getField(modules.getGeneralConfigEnableFieldName());
			field.setBoolean(BRUH.CONFIG.generalConfig, !field.getBoolean(BRUH.CONFIG.generalConfig));

			boolean newValue = field.getBoolean(BRUH.CONFIG.generalConfig);

			context.getSource().sendFeedback(() -> Text.literal("Toggled " + modules.name() + " to " + newValue), true);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		BRUH.CONFIG_MANAGER.save();

		return 1;
	}

	public static <T extends Enum<T>> T getValue(Class<T> clazz, String module) {
		try {
			return T.valueOf(clazz, module);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}


}
