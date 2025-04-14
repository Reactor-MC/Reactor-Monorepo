package ink.reactor.atom.chat.command;

import ink.reactor.atom.chat.AtomChatPlugin;
import ink.reactor.command.Command;
import ink.reactor.command.CommandSender;
import ink.reactor.command.argument.ArgumentBuilder;
import ink.reactor.command.argument.Arguments;
import ink.reactor.command.argument.ArgumentsOptions;

public final class AtomChatCommand implements Command {

    private final AtomChatPlugin atomChatPlugin;
    private final AtomChatModuleState atomChatModuleState;

    public AtomChatCommand(AtomChatPlugin atomChatPlugin) {
        this.atomChatPlugin = atomChatPlugin;
        this.atomChatModuleState = new AtomChatModuleState(atomChatPlugin);
    }

    @Override
    public void onExecute(final CommandSender sender, final Arguments args) {
        final String option = args.nextString();
        if (option == null) {
            sender.sendMessageLegacy(format());
            return;
        }
        switch (option.toLowerCase()) {
            case "info":
                sender.sendMessageLegacy(AtomChatInfo.getModulesInfo(atomChatPlugin.getModules()));
                break;
            case "reload":
                atomChatModuleState.reloadModule(args.nextString(), sender);
                break;
            case "disable":
                atomChatModuleState.disableModule(args.nextString(), sender);
                break;
            case "remove":
                atomChatModuleState.removeModule(args.nextString(), sender);
                break;
            case "enable":
                atomChatModuleState.enableModule(args.nextString(), sender);
                break;
        }
    }

    @Override
    public ArgumentsOptions getArgs() {
        return new ArgumentBuilder()
            .addString(2)
            .build();
    }

    private String format() {
        return
            """
                &b&lAtom Series &7- Chat

                &b/achat &7->
                    &finfo &7- See modules info (persistence and state)
                    &fdisable/enable/reload &3(module) &7- Manage the state
                    &fremove &3(module) &7- Remove module
            """;
    }
}