package ink.reactor.dataparser.registry.painting;

import java.io.IOException;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class PaintingParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/painting");
        final JSONObject jsonObject = loadJsonObject("registry/painting_variant.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject painting)) {
                continue;
            }
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new Painting(");
            final JSONObject author = painting.getJSONObject("author");
            append(builder,
                painting.getString("asset_id"),
                i,
                painting.getIntValue("height"),
                painting.getIntValue("width"),
                painting.getJSONObject("title").getString("translate"),
                author == null ? null : author.getString("translate")
            );

            builder.append(')');

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{ALL_FIELD}", String.valueOf(jsonObject.size()))
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "Painting");
    }
}
