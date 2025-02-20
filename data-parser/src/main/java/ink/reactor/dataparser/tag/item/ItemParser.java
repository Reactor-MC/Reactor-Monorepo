package ink.reactor.dataparser.tag.item;

import java.io.IOException;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class ItemParser implements Parser {

    @Override
    public void load() throws IOException {
        parseTypes();
    }

    private void parseTypes() throws IOException {
        final String template = loadTemplate("tag/item/type");
        final JSONArray array = loadJsonArray("tag/item/items.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Object entry : array) {
            if (!(entry instanceof JSONObject item)) {
                continue;
            }
            final String name = item.getString("name");
            final int id = item.getIntValue("id");
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(name));
            builder.append(" = new Material(");

            append(builder,
                id,
                item.getIntValue("stackSize"),
                item.getIntValue("maxDurability"),
                name
            );

            builder.append(')');

            if (++i != array.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
            .replace("{ALL_FIELD}", String.valueOf(array.size()))
        , "Material");
    }
}
