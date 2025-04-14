package ink.reactor.atom.chat;

import ink.reactor.api.Reactor;
import ink.reactor.api.config.ConfigSection;
import ink.reactor.api.config.YamlConfigManager;
import ink.reactor.atom.chat.module.AtomChatModule;
import ink.reactor.atom.chat.module.chat.ChatModule;
import ink.reactor.atom.chat.module.command.ChatCommandModule;

import java.util.ArrayList;
import java.util.List;

final class AtomChatConfigLoader {

    static List<AtomChatModule> load(final YamlConfigManager configManager) {

        final ConfigSection modules = configManager.getConfig("config.yml");
        if (modules == null) {
            Reactor.getServer().getConsole().sendMessage("AtomChat: The enable-modules section don't exist in config.yml");
            return List.of();
        }

        final List<AtomChatModule> chatModules = new ArrayList<>();

        final ChatCommandModule chatCommandModule = new ChatCommandModule();
        chatModules.add(chatCommandModule);

        final ChatModule chatModule = new ChatModule();
        chatModules.add(chatModule);

        if (modules.getBoolean("commands")) chatCommandModule.load();
        if (modules.getBoolean("chat")) chatModule.load();

        return chatModules;
    }
}