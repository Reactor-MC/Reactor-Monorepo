package ink.reactor.chat.color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ink.reactor.chat.ChatColor;

public class HexConvertorTest {

    // https://www.rgbcolorcode.com/color/emerald
    private static final ChatColor EMERALD_RGB = ChatColor.rgb(80,200,120);
    private static final String EMERALD_HEX = "#50C878";

    @Test
    public void compareHex() {
        Assertions.assertEquals(EMERALD_HEX, EMERALD_RGB.toHex());
    }

    @Test
    public void hexToRGB() {
        Assertions.assertEquals(EMERALD_RGB.toHex(), ChatColor.hex(EMERALD_HEX).toHex());
    }
}