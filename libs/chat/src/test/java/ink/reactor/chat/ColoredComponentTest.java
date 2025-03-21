package ink.reactor.chat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.chat.component.ColoredComponent;

public class ColoredComponentTest {

    private static final String INPUT = "Hi, this a colored component with styles";

    @Test
    public void checkWithoutStyles() {
        final String expectedJson = JSONObject.of("text", INPUT).toString();
        Assertions.assertEquals(expectedJson, new ColoredComponent(INPUT).toJson());
    }

    @Test
    public void checkWithAllStyles() {
        final ColoredComponent component = new ColoredComponent(INPUT);
        component.setBold(true);
        component.setItalic(false);
        component.setObfuscated(false);
        component.setUnderlined(true);
        component.setStrikethrough(false);
        component.setColor(ChatColor.AQUA);

        final String expectedJson = JSONObject.of(
                "text", INPUT,
                "color", "aqua",
                "bold", true,
                "italic", false,
                "underlined", true,
                "obfuscated", false,
                "strikethrough", false
        ).toString();

        Assertions.assertEquals(expectedJson, component.toJson());
    }
}
