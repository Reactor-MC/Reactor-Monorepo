package ink.reactor.chat.format.minimessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.RawComponent;
import ink.reactor.chat.format.minimessage.TagTokenizer.Token;
import ink.reactor.chat.format.minimessage.TagTokenizer.TokenType;
import ink.reactor.chat.format.minimessage.tag.ColorTags;
import ink.reactor.chat.format.minimessage.tag.StyleTags;
import ink.reactor.chat.util.StringUtil;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public final class MiniMessage {
 
    private static final Map<String, MessageTag> GLOBAL_TAGS = new Object2ObjectOpenHashMap<>();

    static {
        ColorTags.registerTags();
        StyleTags.registerTags();
    }

    public static List<ChatComponent> format(final String text) {
        final List<Token> tokens = TagTokenizer.tokenize(text);
        final List<ChatComponent> components = new ArrayList<>();

        CurrentTag currentTag = null;

        final int size = tokens.size();
        for (int i = 0; i < size; i++) {
            final Token token = tokens.get(i);

            if (token.type() == TokenType.TEXT) {
                if (currentTag == null) {
                    components.add(new RawComponent(token.value()));
                    continue;
                }
                currentTag.tag.parse(currentTag.isCloseTag, token.value(), currentTag.args, components);
                currentTag = null;
                continue;
            }

            final boolean isCloseTag = token.value().charAt(1) == '/';
            final int beginIndex = (isCloseTag) ? 2 : 1;
            final String tagContent = token.value().substring(beginIndex, token.value().length()-1); // Remove '</' and '>'

            final List<String> args = StringUtil.split(tagContent, ':');
            final MessageTag messageTag = GLOBAL_TAGS.get(args.get(0));
            if (messageTag == null) {
                components.add(new RawComponent(token.value()));
                continue;
            }

            // Special case, when you open another tag without leave a space blank
            if (i+1 != size && tokens.get(i+1).type() == TokenType.TAG) {
                messageTag.parse(false, "", args, components);
                continue;
            }

            currentTag = new CurrentTag(messageTag, args, isCloseTag);
        }

        return components;
    }

    public static Map<String, MessageTag> getGlobalTags() {
        return GLOBAL_TAGS;
    }

    public static void registerTag(final MessageTag tag, final String... aliases) {
        for (final String alias : aliases) {
            GLOBAL_TAGS.put(alias, tag);
        }
    }

    private static record CurrentTag(
        MessageTag tag,
        List<String> args,
        boolean isCloseTag
    ) {}
}
