package ink.reactor.dataparser.type.chat;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.*;
import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.classes.JavaClassType;
import ink.reactor.fission.field.JavaField;
import ink.reactor.fission.field.JavaFields;
import ink.reactor.fission.format.JavaFormatter;

import java.io.IOException;
import java.util.Map;

public class ChatDataParser  implements DataParser {
    private static final String CLASS_NAME = "ChatType";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaClass javaClass = DataParserTemplateCommon.createTemplate(getClass(), CLASS_NAME, packageName);

        final JavaField senderContent = new JavaField("String[]", "SENDER_CONTENT");
        senderContent.setValue("new String[] {\"sender\", \"content\"};");
        javaClass.addFields(JavaFields.STATIC_CONSTANTS.applyOptions(senderContent));

        final JavaClass dataClas = new JavaClass("Data");
        dataClas.setClassType(JavaClassType.RECORD);
        dataClas.addFields(
                new JavaField("String[]", "parameters"),
                new JavaField("String", "translationKey"));

        javaClass.addSubclass(dataClas);

        JavaFields.FINAL.addFields(javaClass, "Data", "chat", "narration");

        DataParserTemplateCommon.addVanillaData(CLASS_NAME, javaClass);

        final JSONObject jsonObject = ParserFiles.loadJsonObject("chat.json");

        for (final Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject chat)) {
                continue;
            }
            javaClass.addFields(VanillaCommons.DEFAULT.createVanillaField(false, CLASS_NAME, entry.getKey(),
                    loadData(chat.getJSONObject("chat")),
                    loadData(chat.getJSONObject("narration"))
            ));
        }

        final String fileContent = javaClass.toString().replace(ParserPlaceholders.ALL_VALUES, String.valueOf(javaClass.getStaticFields()-2));
        ParserFiles.writeJava(fileContent, CLASS_NAME);
    }


    private String loadData(final JSONObject data) {
        final StringBuilder builder = new StringBuilder();
        builder.append("new Data(");
        final JSONArray array = data.getJSONArray("parameters");

        if (array.size() == 2
                && array.getString(0).equals("sender")
                && array.getString(1).equals("content")
        ) {
            builder.append("SENDER_CONTENT");
        } else {
            builder.append("new String[] {");
            builder.append(JavaFormatter.format(array));
            builder.append("}");
        }

        builder.append(',');
        builder.append('"').append(data.getString("translation_key")).append('"');
        builder.append(')');
        return builder.toString();
    }
}