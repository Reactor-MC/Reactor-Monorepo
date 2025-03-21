package ink.reactor.debug.command;

import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.format.ChatLegacy;
import ink.reactor.command.Command;
import ink.reactor.command.CommandSender;
import ink.reactor.command.argument.ArgumentBuilder;
import ink.reactor.command.argument.Arguments;
import ink.reactor.command.argument.ArgumentsOptions;
import ink.reactor.command.argument.suggestion.SimpleSuggestion;

public final class DebugCommand implements Command {

    @Override
    public void onExecute(final CommandSender sender, final Arguments args) {
        int number = args.nextPositiveInt();
        if (number < 0) {
            sender.sendMessage(format());
            return;
        }
        sender.sendMessage("Args length: " + args.length() + " - All: " + args.getArgs().toString());
        sender.sendMessage("Number: " + number);
        sender.sendMessage("Boolean: " + args.nextBoolean());
    }

    private ChatComponent[] format() {
        return ChatLegacy.format(
                """
                &b&lReactor &7debug command
                """
        );
    }

    @Override
    public ArgumentsOptions getArgs() {
        return ArgumentBuilder.builder()
            .setAutomaticSuggest(SimpleSuggestion.INSTANCE)
            .addInt()
            .addBoolean()
            .build();
    }
}
