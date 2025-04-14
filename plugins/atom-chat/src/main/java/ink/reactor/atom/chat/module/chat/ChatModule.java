package ink.reactor.atom.chat.module.chat;

import ink.reactor.api.config.YamlConfigManager;
import ink.reactor.atom.chat.module.AtomChatModule;
import ink.reactor.atom.chat.module.chat.config.ChatConfigLoader;
import ink.reactor.atom.chat.handler.ChatHandler;
import ink.reactor.protocol.ConnectionState;

public class ChatModule extends AtomChatModule {

    private ChatConfigLoader chatConfigLoader;
    private ChatHandler chatHandler;

    @Override
    protected void onEnable() {
        chatConfigLoader = new ChatConfigLoader();
        chatConfigLoader.load(new YamlConfigManager(this));
        chatHandler = new ChatHandler(chatConfigLoader.getChatConfig());
        ConnectionState.PLAY.add(chatHandler);
    }

    @Override
    protected void onDisable() {
        ConnectionState.PLAY.remove(chatHandler);
        chatHandler = null;
        chatConfigLoader = null;
    }

    @Override
    public String getName() {
        return "chat";
    }
}
