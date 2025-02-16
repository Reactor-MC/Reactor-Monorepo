package ink.reactor.chat.format;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ink.reactor.chat.ChatColor;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.ColoredComponent;
import lombok.experimental.UtilityClass;

/**
 * This class provides methods to transform legacy-formatted text (as used in Minecraft 1.8)
 * into chat components. It interprets text strings containing formatting codes for colors
 * and styles (e.g., `&a`, `&l`) and converts them into a list of `ChatComponent` objects,
 * each representing a fragment of text with its respective style.
 *
 * The class is designed as a utility and cannot be instantiated.
 * 
 * Minor changes: In 1.8 if you format, example: &bTest&l&aOne. The "One" keyword is not in bold.
 * In this software, this bug is fixed :)
 * &bTest&n&k&l&aOne = &bTest&a&l&k&nOne
 */
@UtilityClass
public final class ChatLegacy {

    private static final Pattern HEX_PATTERN = Pattern.compile("#([A-Fa-f0-9]{6})");

    /**
     * Replace all ampersands in string to '§'
     * 
     * @param textWithAmpersand Text with ampersand, example: "&aHello &lWorld!"
     * @return Text with all ampersands replaced by '§'
     */
    public static String color(final String textWithAmpersand) {
        return textWithAmpersand.replace('&', ChatColor.COLOR_CHAR);
    }

    /**
     * Replace all ampersands in string to '§' and transform
     * 
     * @param textWithAmpersand Text with ampersand, example: "&aHello &lWorld!"
     * @return Text with all ampersands replaced by '§'
     */
    public static String fullColor(final String textWithAmpersand) {
        return processHexColors(textWithAmpersand.replace('&', ChatColor.COLOR_CHAR));
    }

    public static String processHexColors(final String text) {
        final Matcher matcher = HEX_PATTERN.matcher(text);
        final StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hexColor = matcher.group(1);
            String replacement = "§x§" + String.join("§", hexColor.split(""));
            matcher.appendReplacement(buffer, replacement);
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    /**
     * Transforms a legacy-formatted string using the `&` character into an
     * array of chat components.
     *
     * @param legacyText The legacy text containing formatting codes.
     * @return An array of `ChatComponent` objects representing the text fragments.
     */
    public static ChatComponent[] format(final String legacyText) {
        return formatTextToList('&', legacyText).toArray(new ChatComponent[0]);
    }

    /**
     * Transforms a legacy-formatted string using the `§` character into an
     * into an array of chat components.
     *
     * @param legacyText The legacy text containing formatting codes.
     * @return An array of `ChatComponent` objects representing the text fragments.
     */
    public static ChatComponent[] formatColorChar(final String legacyText) {
        return formatTextToList(ChatColor.COLOR_CHAR, legacyText).toArray(new ChatComponent[0]);
    }

    /**
     * Converts a legacy-formatted string into a list of `ChatComponent` objects, using the
     * provided special character to identify formatting codes.
     *
     * @param specialChar The special character used for formatting codes (e.g., `&`).
     * @param legacyText  The legacy text containing formatting codes.
     * @return A list of `ChatComponent` objects representing the formatted text.
     */
    public static List<ChatComponent> formatTextToList(final char specialChar, final String legacyText) {
        final List<ChatComponent> components = new LinkedList<>();
        final int length = legacyText.length();

        TextComponent component = new TextComponent();

        for (int i = 0; i < length; i++) {
            char character = legacyText.charAt(i);
            if (character == '\n') {
                component.add(components);
                component = new TextComponent();
                components.add(ChatComponent.NEW_LINE);
                continue;
            }
            if (character != specialChar) {
                component.builder.append(character);
                continue;
            }
            if (i++ >= length) {
                continue;
            }

            character = legacyText.charAt(i);
            final ChatColor color = ChatColor.byLegacyCode(character);
            if (color != null) {
                if (component.getColor() == color) {
                    continue;
                }
                if (!component.builder.isEmpty()) {
                    component.add(components);
                    final TextComponent old = component;
                    component = new TextComponent();
                    component.resetOld(old);
                }
                component.setColor(color);
                continue;
            }
            if (component.builder.length() > 0) {
                final ChatColor oldColor = component.getColor();
                component.add(components);
                component = new TextComponent();
                component.setColor(oldColor);
            }
            switch (character) {
                case 'l': component.setBold(true); break;
                case 'o': component.setItalic(true); break;
                case 'n': component.setUnderlined(true); break;
                case 'm': component.setStrikethrough(true); break;
                case 'k': component.setObfuscated(true); break;
                case 'r':
                    component.add(components);
                    component = new TextComponent();
                    component.reset();
                    break;
            }
        }
        component.add(components);
        return components;
    }

    private static final class TextComponent extends ColoredComponent {
        public TextComponent() {
            super("");
        }

        public TextComponent(String text) {
            super(text);
        }

        private final StringBuilder builder = new StringBuilder();

        private void add(List<ChatComponent> components) {
            if (builder.length() == 0) {
                return;
            }
            setText(builder.toString());
            components.add(this);
        }

        public void resetOld(final TextComponent old) {
            setColor(ChatColor.WHITE);
            if (old.getBold() != UNDEFINED) {
                setBold(false);
            }
            if (old.getItalic() != UNDEFINED) {
                setItalic(false);
            }
            if (old.getUnderlined() != UNDEFINED) {
                setUnderlined(false);
            }
            if (old.getStrikethrough() != UNDEFINED) {
                setStrikethrough(false);
            }
            if (old.getObfuscated() != UNDEFINED) {
                setObfuscated(false);
            }
        }
    }
}