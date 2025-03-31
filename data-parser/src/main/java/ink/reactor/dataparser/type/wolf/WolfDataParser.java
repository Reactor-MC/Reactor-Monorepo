package ink.reactor.dataparser.type.wolf;

import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.*;
import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.field.JavaFields;

import java.io.IOException;
import java.util.Map;

public class WolfDataParser implements DataParser {

    private static final String CLASS_NAME = "WolfVariant";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaClass javaClass = DataParserTemplateCommon.createTemplate(getClass(), CLASS_NAME, packageName);
        JavaFields.FINAL.addFields(javaClass, String.class, "name", "angryTexture", "biomes", "tameTexture", "wildTexture");
        DataParserTemplateCommon.addVanillaData(CLASS_NAME, javaClass);

        final JSONObject jsonObject = ParserFiles.loadJsonObject("wolf_variant.json");

        for (final Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject wolf)) {
                continue;
            }
            javaClass.addFields(VanillaCommons.DEFAULT.createVanillaField(CLASS_NAME, entry.getKey(),
                    entry.getKey(),
                    wolf.getString("angry_texture"),
                    wolf.getString("biomes"),
                    wolf.getString("tame_texture"),
                    wolf.getString("wild_texture")));
        }

        final String fileContent = javaClass.toString().replace(ParserPlaceholders.ALL_VALUES, String.valueOf(javaClass.getStaticFields()-1));
        ParserFiles.writeJava(fileContent, CLASS_NAME);
    }
}