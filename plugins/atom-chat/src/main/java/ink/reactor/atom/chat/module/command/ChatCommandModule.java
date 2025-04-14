package ink.reactor.atom.chat.module.command;

import ink.reactor.atom.chat.module.AtomChatModule;
import ink.reactor.atom.chat.module.command.handler.CommandHandler;
import ink.reactor.protocol.ConnectionState;

public final class ChatCommandModule extends AtomChatModule {

    private CommandHandler commandHandler;

    @Override
    public void onEnable() {
        commandHandler = new CommandHandler();
        ConnectionState.PLAY.add(commandHandler);
    }

    @Override
    protected void onDisable() {
        if (commandHandler != null) {
            ConnectionState.PLAY.remove(commandHandler);
        }
    }

    @Override
    public String getName() {
        return "command";
    }
}