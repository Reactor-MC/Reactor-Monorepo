package ink.reactor.dataparser.type.sound;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.*;
import ink.reactor.fission.commentary.MultiLineCommentary;

import java.io.IOException;

public class SoundDataParser implements DataParser {
    private static final String CLASS_NAME = "Sounds";

    @Override
    public void parse(String packageName) throws IOException {
        final StringBuilder builder = new StringBuilder();
        builder.append("package ").append(packageName).append(';').append('\n');
        builder.append(MultiLineCommentary.of("""
            Thanks to minecraft-data for collect all sounds.
            <a href="https://github.com/PrismarineJS/minecraft-data/blob/master/data/pc/1.21.4/sounds.json">Sounds</a>"""));

        builder.append("\npublic enum Sounds {");
        final JSONArray jsonArray = ParserFiles.loadJsonArray("sounds.json");
        final int size = jsonArray.size();
        int i = 0;

        for (final Object entry : jsonArray) {
            if (entry instanceof JSONObject sound) {
                String name = sound.getString("name");
                builder.append('\n');
                builder.append('\t');

                builder.append(name.replace('.', '_').toUpperCase());
                builder.append(++i == size ? ';' : ',');
            }
        }
        builder.append("\n}");

        ParserFiles.writeJava(builder.toString(), CLASS_NAME);
    }
}