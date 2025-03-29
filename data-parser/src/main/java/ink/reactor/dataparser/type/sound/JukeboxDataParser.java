package ink.reactor.dataparser.type.sound;

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

public class JukeboxDataParser implements DataParser {
    private static final String CLASS_NAME = "Jukebox";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaClass javaClass = DataParserTemplateCommon.createTemplate(getClass(), CLASS_NAME, packageName);
        JavaFields.FINAL.addFields(javaClass, String.class, "description", "soundEvent");
        JavaFields.FINAL.addInts(javaClass, "comparatorOutput");
        JavaFields.FINAL.addDoubles(javaClass, "lengthInSeconds");

        DataParserTemplateCommon.addVanillaData(CLASS_NAME, javaClass);

        final JSONObject jsonObject = ParserFiles.loadJsonObject("jukebox.json");

        for (final Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject jukebox)) {
                continue;
            }
            String fieldName = entry.getKey();
            try {
                int value = Integer.parseInt(fieldName);
                fieldName = "NUMBER_" + value;
            } catch (NumberFormatException e) {
                fieldName = entry.getKey();
            }
            javaClass.addFields(VanillaCommons.DEFAULT.createVanillaField(CLASS_NAME, fieldName,
                    jukebox.getJSONObject("description").getString("translate"),
                    jukebox.getString("sound_event"),
                    jukebox.getIntValue("comparator_output"),
                    jukebox.getDouble("length_in_seconds")
            ));
        }

        final String fileContent = javaClass.toString().replace(ParserPlaceholders.ALL_VALUES, String.valueOf(javaClass.getStaticFields()-1));
        ParserFiles.writeJava(fileContent, CLASS_NAME);
    }
}