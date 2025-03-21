package ink.reactor.command;

import java.util.Collection;
import java.util.List;

import ink.reactor.command.argument.Arguments;
import ink.reactor.command.event.CommandPreExecuteEvent;
import ink.reactor.command.event.CommandTabEvent;
import ink.reactor.command.storage.CommandData;
import ink.reactor.command.storage.CommandStorage;
import ink.reactor.util.StringUtil;
import ink.reactor.util.event.EventDispatcher;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class CommandExecutor {

    public static boolean execute(final CommandStorage storage, final CommandSender sender, String commandLine) {
        if (commandLine == null || commandLine.isEmpty()) {
            return false;
        }

        final List<String> args = StringUtil.splitIgnoreExtra(commandLine, ' ');
        final Command command = storage.getCommand(args.getFirst());

        if (command == null) {
            return false;
        }

        final Arguments arguments = createArguments(args);

        final CommandPreExecuteEvent commandPreExecuteEvent = new CommandPreExecuteEvent(sender, arguments, args.getFirst(), command);
        EventDispatcher.call(commandPreExecuteEvent);

        if (!commandPreExecuteEvent.isCancelled()) {
            command.onExecute(sender, arguments);
            return true;
        }
        return false;
    }

    public static Collection<?> tab(final CommandStorage storage, final CommandSender sender, final String commandLine) {
        if (commandLine == null || commandLine.isEmpty()) {
            return null;
        }

        final List<String> args = StringUtil.splitIgnoreExtra(commandLine, ' ');
        final CommandData commandData = storage.getData(args.getFirst());

        if (commandData == null) {
            return null;
        }

        final CommandTabEvent tabEvent = new CommandTabEvent();
        tabEvent.setOutput(commandData.command().onTab(sender, createArguments(args)));
        EventDispatcher.call(tabEvent);

        if (tabEvent.isCancelled()) {
            return null;
        }

        if (tabEvent.getOutput() == null) {
            tabEvent.setOutput(commandData.options().getArgumentsOptions().suggestion().suggest(args.size() - 1, commandData));
        }

        return tabEvent.getOutput();
    }

    private static Arguments createArguments(final List<String> args) {
        return new Arguments(args.size() > 1 ? args.subList(1, args.size()) : List.of());
    }
}