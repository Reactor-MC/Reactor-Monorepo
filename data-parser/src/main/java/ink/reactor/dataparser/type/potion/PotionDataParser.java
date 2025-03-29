package ink.reactor.dataparser.type.potion;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.ParserFiles;

import ink.reactor.fission.classes.enums.JavaEnum;
import ink.reactor.fission.classes.enums.JavaEnumObject;
import ink.reactor.fission.commentary.MultiLineCommentary;

import java.io.IOException;

public class PotionDataParser implements DataParser {
    private static final String CLASS_NAME = "PotionEffectType";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaEnum javaEnum = new JavaEnum(packageName, CLASS_NAME);
        javaEnum.setCommentary(MultiLineCommentary.of(
                """
                Thanks to minecraft-data for collect all effects.
                <a href="https://github.com/PrismarineJS/minecraft-data/blob/master/data/pc/1.21.4/effects.json">Effects</a>"""
        ));

        final JSONArray jsonArray = ParserFiles.loadJsonArray("effects.json");

        for (final Object entry : jsonArray) {
            if (entry instanceof JSONObject potionEffect) {
                javaEnum.addEnumObjects(new JavaEnumObject(
                        potionEffect.getString("displayName")
                                .toUpperCase()
                                .replace(' ', '_')
                                .replace("'", "")
                ));
            }
        }

        ParserFiles.writeJava(javaEnum.toString(), CLASS_NAME);
    }
}