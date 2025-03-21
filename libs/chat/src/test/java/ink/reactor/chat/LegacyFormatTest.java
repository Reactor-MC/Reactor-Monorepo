package ink.reactor.chat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.ColoredComponent;
import ink.reactor.chat.format.ChatLegacy;

public class LegacyFormatTest {

    private static final String INPUT = "&a&lHi, this is a &etext with &nLegacy format";

    @Test
    public void checkOutput() {
        final ChatComponent[] components = ChatLegacy.format(INPUT);
        assertEquals(3, components.length);

        final ColoredComponent first = (ColoredComponent)components[0]; // Hi, this is a
        assertEquals(ChatColor.GREEN, first.getColor());
        assertTrue(first.isBold());

        final ColoredComponent second = (ColoredComponent)components[1]; // text with
        assertEquals(ChatColor.YELLOW, second.getColor());
        assertFalse(second.hasFontStyle());

        final ColoredComponent last = (ColoredComponent)components[2]; // Legacy format
        assertTrue(last.isUnderlined());
    }
}