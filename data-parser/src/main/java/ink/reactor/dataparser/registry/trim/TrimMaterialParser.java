package ink.reactor.dataparser.registry.trim;

import java.io.IOException;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class TrimMaterialParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/trim-material");
        final JSONObject jsonObject = loadJsonObject("registry/trim_material.json");
        final StringBuilder builder = new StringBuilder();
        
        int i = 0;
        for (final Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject trim)) {
                continue;
            }
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new TrimMaterial(");
            append(builder,
                trim.getString("asset_name"),
                i,
                trim.getJSONObject("description").getString("translate"),
                trim.getString("ingredient"),
                trim.getDoubleValue("item_model_index")
            );

            builder.append(')');

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{ALL_FIELD}", String.valueOf(jsonObject.size()))
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "TrimMaterial");
    }
}
