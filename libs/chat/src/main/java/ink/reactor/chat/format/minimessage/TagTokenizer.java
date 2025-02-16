package ink.reactor.chat.format.minimessage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class TagTokenizer {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("(</?[a-zA-Z0-9:#-]+>)|([^<>]+)");

    public static List<Token> tokenize(final String text) {
        final List<Token> tokens = new ArrayList<>();
        final Matcher matcher = TOKEN_PATTERN.matcher(text);

        while (matcher.find()) {
            String group = matcher.group(1);
            if (group != null) { 
                tokens.add(new Token(TokenType.TAG, group));
                continue;
            }

            group = matcher.group(2);
            if (group != null) { 
                tokens.add(new Token(TokenType.TEXT, group.trim()));
            }
        }
        return tokens;
    }

    static record Token(
        TokenType type,
        String value
    ) {}

    static enum TokenType {
        TAG, TEXT;
    }
}