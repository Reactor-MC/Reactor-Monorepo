package ink.reactor.dataparser.registry.jukebox;

import java.io.IOException;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class JukeboxParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/jukebox");
        final JSONObject jsonObject = loadJsonObject("registry/jukebox_songs.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject jukebox)) {
                continue;
            }
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new JukeboxSong(");
            append(builder,
                entry.getKey(),
                i,
                jukebox.getJSONObject("description").getString("translate"),
                jukebox.getString("sound_event"),
                jukebox.getIntValue("comparator_output"),
                jukebox.getIntValue("length_in_seconds")
            );

            builder.append(')');

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{ALL_FIELD}", String.valueOf(jsonObject.size()))
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "JukeboxSong");
    }
}