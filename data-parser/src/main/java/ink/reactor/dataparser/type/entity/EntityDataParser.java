package ink.reactor.dataparser.type.entity;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.DataParserTemplateCommon;
import ink.reactor.dataparser.common.ParserFiles;
import ink.reactor.dataparser.common.ParserPlaceholders;
import ink.reactor.dataparser.common.VanillaCommons;
import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.field.JavaFields;

import java.io.IOException;

public class EntityDataParser implements DataParser {

    private static final String CLASS_NAME = "EntityType";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaClass javaClass = DataParserTemplateCommon.createTemplate(getClass(), CLASS_NAME, packageName);
        JavaFields.FINAL.addInts(javaClass, "id");
        JavaFields.FINAL.addFields(javaClass, String.class, "name");
        JavaFields.FINAL.addDoubles(javaClass, "height", "width");

        DataParserTemplateCommon.addVanillaData(CLASS_NAME, javaClass);

        final JSONArray jsonArray = ParserFiles.loadJsonArray("entities.json");

        for (final Object entry : jsonArray) {
            if (!(entry instanceof JSONObject entity)) {
                continue;
            }
            final String name = entity.getString("name").toUpperCase();
            javaClass.addFields(VanillaCommons.DEFAULT.createVanillaField(true, CLASS_NAME, name,
                    entity.getIntValue("id"),
                    name,
                    entity.getDouble("height"),
                    entity.getDouble("width")));
        }

        final String fileContent = javaClass.toString().replace(ParserPlaceholders.ALL_VALUES, String.valueOf(javaClass.getStaticFields()-1));
        ParserFiles.writeJava(fileContent, CLASS_NAME);
    }
}