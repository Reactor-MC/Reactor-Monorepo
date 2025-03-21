package ink.reactor.dataparser.registry.block;

import java.io.IOException;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class BlockParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/block");
        final JSONArray array = loadJsonArray("registry/blocks.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Object entry : array) {
            if (!(entry instanceof JSONObject block)) {
                continue;
            }
            builder.append('\n');
            builder.append("\t");
            builder.append(toFieldName(block.getString("name")));
            builder.append("=of(");

            JSONArray drops = block.getJSONArray("drops");

            append(builder,
                block.getIntValue("id"),
                block.getDoubleValue("resistance")
            );
            if (drops != null && !drops.isEmpty()) {
                builder.append(',');
                if (drops.size() == 1) {
                    builder.append(drops.getFirst());
                } else {
                    final String dropsString = drops.toString();
                    builder.append("new char[] {");
                    builder.append(dropsString.subSequence(1, dropsString.length() -1));
                    builder.append('}');
                }
            }

            builder.append(")");

            if (++i != array.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
           .replace("{ALL_FIELD}", String.valueOf(array.size()))
            .replace("{BLOCKS_FIELD}", builder.toString())
        , "Block");
    }
}
