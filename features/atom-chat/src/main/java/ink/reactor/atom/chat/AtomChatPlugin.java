package ink.reactor.atom.chat;

import ink.reactor.api.config.YamlConfigManager;
import ink.reactor.api.plugin.Plugin;
import ink.reactor.atom.chat.command.AtomChatCommand;
import ink.reactor.atom.chat.handler.ChatHandler;
import ink.reactor.atom.chat.module.AtomChatModule;
import ink.reactor.atom.chat.module.chat.config.ChatConfig;
import ink.reactor.protocol.ConnectionState;
import lombok.Getter;

import java.util.List;

@Getter
public final class AtomChatPlugin extends Plugin {

    private List<AtomChatModule> modules;

    @Override
    protected void onEnable() {
        final YamlConfigManager yamlConfigManager = new YamlConfigManager(this);
        modules = AtomChatConfigLoader.load(yamlConfigManager);

        for (final AtomChatModule module : modules) {
            module.enable();
        }

        ConnectionState.PLAY.add(new ChatHandler(new ChatConfig()));

        getServer().getCommandStorage().register(new AtomChatCommand(this), "achat");
    }

    @Override
    protected void onDisable() {
        getServer().getCommandStorage().unregister("achat");

        for (final AtomChatModule module : modules) {
            module.disable();
        }
        modules = null;
    }

    @Override
    public String getName() {
        return "atom-chat";
    }
}