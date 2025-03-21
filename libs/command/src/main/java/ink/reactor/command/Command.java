package ink.reactor.command;

import java.util.Collection;

import ink.reactor.command.argument.Arguments;
import ink.reactor.command.argument.ArgumentsOptions;
import ink.reactor.command.storage.CommandOptions;

public interface Command {
    CommandOptions DEFAULT_OPTIONS = new CommandOptions();

    void onExecute(final CommandSender sender, final Arguments args);

    default Collection<?> onTab(final CommandSender sender, final Arguments args) {
        return null;
    }

    default ArgumentsOptions getArgs() {
        return null;
    }

    default CommandOptions getOptions() {
        final ArgumentsOptions args = getArgs();
        if (args == null) {
            return DEFAULT_OPTIONS;
        }
        final CommandOptions options = new CommandOptions();
        options.setArgumentsOptions(args);
        return options;
    }
}