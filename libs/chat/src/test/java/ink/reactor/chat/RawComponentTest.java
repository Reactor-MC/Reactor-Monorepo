package ink.reactor.chat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.chat.component.RawComponent;

public class RawComponentTest {

    private static final String INPUT = "Hi, this is a raw component. Without any style";

    @Test
    public void checkValid() {
        final RawComponent rawComponent = new RawComponent(INPUT);
        final String expectedJson = JSONObject.of("text", INPUT).toString();
        Assertions.assertEquals(expectedJson, rawComponent.toJson());
        Assertions.assertEquals(expectedJson, new String(rawComponent.toJsonBytes()));
    }
}