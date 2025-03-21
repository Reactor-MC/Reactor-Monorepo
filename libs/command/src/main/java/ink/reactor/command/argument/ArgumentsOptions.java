package ink.reactor.command.argument;

import ink.reactor.command.argument.suggestion.CommandSuggestion;

import java.util.List;

public record ArgumentsOptions(
    CommandSuggestion suggestion,
    List<ArgumentType> argumentTypes
) {
}