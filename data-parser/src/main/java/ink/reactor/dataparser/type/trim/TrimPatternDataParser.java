package ink.reactor.dataparser.type.trim;

import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.DataParserTemplateCommon;
import ink.reactor.dataparser.common.ParserFiles;
import ink.reactor.dataparser.common.ParserPlaceholders;
import ink.reactor.dataparser.common.VanillaCommons;
import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.field.JavaFields;

import java.io.IOException;
import java.util.Map;

public class TrimPatternDataParser implements DataParser {
    private static final String CLASS_NAME = "TrimPattern";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaClass javaClass = DataParserTemplateCommon.createTemplate(getClass(), CLASS_NAME, packageName);
        JavaFields.FINAL.addFields(javaClass, String.class, "assetId", "description", "templateItem");
        JavaFields.FINAL.addFields(javaClass, "boolean", "decal");

        DataParserTemplateCommon.addVanillaData(CLASS_NAME, javaClass);

        final JSONObject jsonObject = ParserFiles.loadJsonObject("trim_pattern.json");

        for (final Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject trimPattern)) {
                continue;
            }
            javaClass.addFields(VanillaCommons.DEFAULT.createVanillaField(CLASS_NAME, entry.getKey(),
                    trimPattern.getString("asset_id"),
                    trimPattern.getJSONObject("description").getString("translate"),
                    trimPattern.getString("template_item"),
                    trimPattern.getBoolean("decal")));
        }

        final String fileContent = javaClass.toString().replace(ParserPlaceholders.ALL_VALUES, String.valueOf(javaClass.getStaticFields()-1));
        ParserFiles.writeJava(fileContent, CLASS_NAME);
    }
}