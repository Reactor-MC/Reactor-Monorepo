package ink.reactor.dataparser.type.banner;

import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.*;
import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.field.JavaFields;

import java.io.IOException;
import java.util.Map;

public class BannerDataParser implements DataParser {
    private static final String CLASS_NAME = "BannerPattern";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaClass javaClass = DataParserTemplateCommon.createTemplate(getClass(), CLASS_NAME, packageName);
        JavaFields.FINAL.addFields(javaClass, String.class, "assetId", "translationKey");
        DataParserTemplateCommon.addVanillaData(CLASS_NAME, javaClass);

        final JSONObject jsonObject = ParserFiles.loadJsonObject("banners.json");

        for (final Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject banner)) {
                continue;
            }
            javaClass.addFields(VanillaCommons.DEFAULT.createVanillaField(CLASS_NAME, entry.getKey(),
                    banner.getString("asset_id"),
                    banner.getString("translation_key")));
        }

        final String fileContent = javaClass.toString().replace(ParserPlaceholders.ALL_VALUES, String.valueOf(javaClass.getStaticFields()-1));
        ParserFiles.writeJava(fileContent, CLASS_NAME);
    }
}
