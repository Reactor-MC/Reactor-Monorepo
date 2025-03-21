package ink.reactor.atom.chat.module.chat.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public final class ChatConfig {

    private final ParserColor parserColor = new ParserColor();

    private Set<String> blackListKeywords = Set.of();

    @Getter
    public static final class ParserColor {
        boolean minimessage, legacy;
    }
}
