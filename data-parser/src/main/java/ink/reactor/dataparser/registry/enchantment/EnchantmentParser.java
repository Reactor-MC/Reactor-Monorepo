package ink.reactor.dataparser.registry.enchantment;

import java.io.IOException;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class EnchantmentParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/enchantment");
        final JSONObject jsonObject = loadJsonObject("registry/enchantments.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject enchantment)) {
                continue;
            }
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new Enchantment(");
            append(builder,
                i,
                entry.getKey(),
                enchantment.getIntValue("max_level")
            );

            builder.append(')');

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{ALL_FIELD}", String.valueOf(jsonObject.size()))
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "Enchantment");
    }
}
