package ink.reactor.dataparser.registry.banner;

import java.io.IOException;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class BannerParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/banner");
        final JSONObject jsonObject = loadJsonObject("registry/banner.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject banner)) {
                continue;
            }
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new Banner(");

            append(builder,
                banner.getString("asset_id"),
                i,
                banner.getString("translation_key")
            );

            builder.append(')');

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{ALL_FIELD}", String.valueOf(jsonObject.size()))
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "Banner");
    }
}
