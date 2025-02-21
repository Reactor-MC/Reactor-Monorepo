package ink.reactor.dataparser.other.effect;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.Parser;

import java.io.IOException;

public final class EffectParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("other/effects");
        final JSONArray jsonObject = loadJsonArray("other/effects.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Object entry : jsonObject) {
            if (!(entry instanceof JSONObject effect)) {
                continue;
            }
            builder.append('\n');
            builder.append("    ");
            builder.append(toFieldName(effect.getString("displayName")));

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template.replace("{OBJECT_FIELD_TYPE}", builder.toString()), "EffectType");
    }
}
