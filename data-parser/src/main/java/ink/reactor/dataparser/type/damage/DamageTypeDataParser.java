package ink.reactor.dataparser.type.damage;

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

public class DamageTypeDataParser implements DataParser {
    private static final String CLASS_NAME = "DamageType";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaClass javaClass = DataParserTemplateCommon.createTemplate(getClass(), CLASS_NAME, packageName);
        JavaFields.FINAL.addFields(javaClass, String.class, "name", "deathMessageType", "messageId", "scaling");
        JavaFields.FINAL.addDoubles(javaClass, "exhaustion");

        DataParserTemplateCommon.addVanillaData(CLASS_NAME, javaClass);

        final JSONObject jsonObject = ParserFiles.loadJsonObject("damage_types.json");

        for (final Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject damageType)) {
                continue;
            }
            final String deathMsg = damageType.getString("death_message_type");
            javaClass.addFields(VanillaCommons.DEFAULT.createVanillaField(CLASS_NAME, entry.getKey(),
                    entry.getKey(),
                    deathMsg == null ? "" : deathMsg,
                    damageType.getString("message_id"),
                    damageType.getString("scaling"),
                    damageType.getDouble("exhaustion")));
        }

        final String fileContent = javaClass.toString().replace(ParserPlaceholders.ALL_VALUES, String.valueOf(javaClass.getStaticFields()-1));
        ParserFiles.writeJava(fileContent, CLASS_NAME);
    }
}