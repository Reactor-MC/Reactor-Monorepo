package ink.reactor.chat.format.minimessage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.FullComponent;
import ink.reactor.chat.component.RawComponent;
import ink.reactor.chat.format.minimessage.TagTokenizer.Token;
import ink.reactor.chat.format.minimessage.TagTokenizer.TokenType;
import ink.reactor.chat.util.StringUtil;

final class MiniMessageFormater {

    static List<ChatComponent> format(final String text, final Map<String, MiniTag> tags) {
        final List<Token> tokens = TagTokenizer.tokenize(text);
        final List<ChatComponent> components = new ArrayList<>();
        final Deque<OpenTag> openTags = new ArrayDeque<>();

        FullComponent component = new FullComponent("");

        for (final Token token : tokens) {
            if (token.type() == TokenType.TEXT) {
                component.setText(token.value());
                applyTags(component, openTags, components);
                components.add(component);
                component = new FullComponent("");
                continue;
            }

            final boolean isCloseTag = token.value().charAt(1) == '/';
            final String tagContent = token.value().substring(isCloseTag ? 2 : 1, token.value().length() - 1); // Remove '<', '</' and '>'
            final List<String> args = StringUtil.split(tagContent, ':');
            final MiniTag messageTag = tags.get(args.get(0));

            if (messageTag == null) {
                components.add(new RawComponent(token.value()));
                continue;
            }

            if (!isCloseTag) {
                openTags.push(new OpenTag(messageTag, args));
                continue;
            }

            messageTag.onClose(component);
            closeTag(openTags, tagContent, component);
        }
        return components;
    }

    private static void applyTags(final FullComponent component, final Deque<OpenTag> openTags, final List<ChatComponent> components) {
        final Iterator<OpenTag> iterator = openTags.iterator();

        while (iterator.hasNext()) {
            final OpenTag openTag = iterator.next();
            openTag.tag.parse(component, openTag.args, components);

            if (openTag.tag.autoCloseableTag()) {
                iterator.remove();
            }
        }
    }

    private static void closeTag(final Deque<OpenTag> openTags, final String tagContent, final FullComponent component) {
        final Iterator<OpenTag> iterator = openTags.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().args.get(0).equals(tagContent)) {
                iterator.remove();
                break;
            }
        }
    }

    private static record OpenTag(
        MiniTag tag,
        List<String> args
    ) {}
}