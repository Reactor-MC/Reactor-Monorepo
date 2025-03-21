package ink.reactor.command.argument.suggestion;

import ink.reactor.command.storage.CommandData;

import java.util.Collection;

public interface CommandSuggestion {
    Collection<?> suggest(final int index, final CommandData data);
}
