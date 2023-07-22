package io.github.overlordsiii.command.suggestion;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.server.command.ServerCommandSource;

public class EnumSuggestionProvider<T extends Enum<T>> implements SuggestionProvider<ServerCommandSource> {

	private final T[] values;

	public EnumSuggestionProvider(T[] values) {
		this.values = values;
	}
	@Override
	public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) {
		String string = builder.getRemaining();
		sortFormattingByString(string).forEach(builder::suggest);
		return builder.buildFuture();
	}
	private ArrayList<String> sortFormattingByString(String currentArg){
		ArrayList<String> suggestionsBasedOnCurrentArg = new ArrayList<>();
		for (T formatting : values) {
			if (formatting.toString().indexOf(currentArg) == 0){
				suggestionsBasedOnCurrentArg.add(formatting.toString());
			}
		}
		return suggestionsBasedOnCurrentArg;
	}


}
