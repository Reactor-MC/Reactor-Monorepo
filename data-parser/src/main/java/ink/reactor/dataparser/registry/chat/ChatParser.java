package ink.reactor.dataparser.registry.chat;

import java.io.IOException;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class ChatParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/chat");
        final JSONObject jsonObject = loadJsonObject("registry/chat_type.json");

        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject chatType)) {
                continue;
            }
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new ChatType(");

            append(builder, entry.getKey(), i);
            loadData(builder, chatType.getJSONObject("chat"));
            loadData(builder, chatType.getJSONObject("narration"));

            builder.append(')');

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }
        
        writeFile(getClass(), template
            .replace("{ALL_FIELD}", String.valueOf(jsonObject.size()))
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "ChatType");
    }

    private void loadData(final StringBuilder builder, final JSONObject data) {
        builder.append(',');
        builder.append("new Data(");
        final JSONArray array = data.getJSONArray("parameters");

        if (array.size() == 2
            && array.getString(0).equals("sender")
            && array.getString(1).equals("content")
        ) {
            builder.append("SENDER_CONTENT");
        } else {
            builder.append("new String[] {");
            append(builder, array.toArray());
            builder.append("}");
        }

        builder.append(',');
        appendString(data.getString("translation_key"), builder);
        builder.append(')');
    }
}
