package ink.reactor.dataparser.registry.damage;

import java.io.IOException;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class DamageTypeParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/damage");
        final JSONObject jsonObject = loadJsonObject("registry/damage_type.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject damage)) {
                continue;
            }
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new DamageType(");
            append(builder,
                entry.getKey(),
                i,
                damage.getDouble("exhaustion"),
                damage.getString("message_id"),
                damage.getString("scaling")
            );

            builder.append(')');

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{ALL_FIELD}", String.valueOf(jsonObject.size()))
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "DamageType");
    }
}
