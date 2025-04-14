package ink.reactor.atom.chat.module.chat.config;

import ink.reactor.api.config.ConfigSection;
import ink.reactor.api.config.YamlConfigManager;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public final class ChatConfigLoader {

    private final ChatConfig chatConfig = new ChatConfig();

    public void load(YamlConfigManager yamlConfigManager) {
        final ConfigSection config = yamlConfigManager.getConfig("modules/commands.yml");

        chatConfig.getParserColor().legacy = config.getBoolean("legacy");
        chatConfig.getParserColor().minimessage = config.getBoolean("mini-message");

        final List<String> blockedKeywords = config.getStringList("keyword-filter");
        chatConfig.setBlackListKeywords(blockedKeywords == null ? Set.of() : Set.copyOf(blockedKeywords));
    }
}
