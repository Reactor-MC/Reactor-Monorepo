package ink.reactor.command.storage;

import java.util.Map;

import ink.reactor.command.Command;
import ink.reactor.command.exception.CommandException;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public final class CommandStorage {

    private final Map<String, CommandData> commands = new Object2ObjectOpenHashMap<>();

    public Command getCommand(final String alias) {
        final CommandData data = commands.get(alias);
        return (data == null) ? null : data.command();
    }

    public CommandData getData(final String alias) {
        return commands.get(alias);
    }

    public void register(final Command command, final String... aliases) {
        final CommandOptions options = command.getOptions();
        final CommandData data = new CommandData(aliases, command, options);

        for (final String alias : aliases) {
            final CommandData oldCommand = commands.put(alias, data);
            if (oldCommand == null) {
                continue;
            }
            if (oldCommand.options().getOverrideOption() != CommandOptions.ALLOW_OVERRIDE) {
                commands.put(alias, oldCommand);
                throw new CommandException("The command " + alias + " is already set and can't be changed");
            }
            if (options.getOnOverride() != null) {
                options.getOnOverride().accept(data);
            }
        }
    }

    public CommandData unregister(final String alias) {
        final CommandData commandData = commands.remove(alias);
        if (commandData != null && commandData.options().getOverrideOption() == CommandOptions.CANT_REMOVE_AND_OVERRIDE) {
            commands.put(alias, commandData);
            throw new CommandException("The command " + alias + " can't be removed and override");
        }
        return commandData;
    }
}
