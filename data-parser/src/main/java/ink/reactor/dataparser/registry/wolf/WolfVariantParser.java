package ink.reactor.dataparser.registry.wolf;

import java.io.IOException;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class WolfVariantParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/wolf");
        final JSONObject jsonObject = loadJsonObject("registry/wolf_type.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject wolfType)) {
                continue;
            }
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new WolfType(");

            append(builder,
                entry.getKey(),
                i,
                wolfType.getString("angry_texture"),
                wolfType.getString("tame_texture"),
                wolfType.getString("wild_texture")
            );

            builder.append(')');

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{ALL_FIELD}", String.valueOf(jsonObject.size()))
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "WolfType");
    }
}
