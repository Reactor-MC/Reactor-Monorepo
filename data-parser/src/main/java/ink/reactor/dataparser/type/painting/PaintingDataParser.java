package ink.reactor.dataparser.type.painting;

import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.ParserClass;
import ink.reactor.dataparser.common.ParserPlaceholders;
import ink.reactor.dataparser.common.ParserFiles;
import ink.reactor.dataparser.common.VanillaCommons;
import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.field.JavaFields;
import ink.reactor.fission.method.JavaGetterAndSetters;
import ink.reactor.fission.method.JavaMethod;

import java.io.IOException;
import java.util.Map;

public class PaintingDataParser implements DataParser {

    private static final String CLASS_NAME = "PaintingVariant";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaClass javaClass = createTemplate(packageName);
        final JSONObject jsonObject = ParserFiles.loadJsonObject("painting.json");

        for (final Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject painting)) {
                continue;
            }
            final JSONObject author = painting.getJSONObject("author");
            final String authorName = (author == null) ? "" : author.getString("translate");

            javaClass.addFields(VanillaCommons.DEFAULT.createVanillaField(CLASS_NAME, entry.getKey(),
                    painting.getString("asset_id"), authorName, painting.getJSONObject("title").getString("translate"),
                    painting.getInteger("height"), painting.getInteger("width")));
        }

        final String fileContent = javaClass.toString().replace(ParserPlaceholders.ALL_VALUES, String.valueOf(javaClass.getStaticFields()-1));
        ParserFiles.writeJava(fileContent, CLASS_NAME);
    }

    private JavaClass createTemplate(final String packageName) {
        final JavaClass javaClass = ParserClass.immutableClass(
                packageName, CLASS_NAME, getClass(),
                ParserClass.combineCommentaries(ParserClass.JSON_COMBINED, ParserClass.DATA_GENERATOR));

        final VanillaCommons vanillaCommons = VanillaCommons.builder().copyParameters(true).build();

        vanillaCommons.addAllField(javaClass);

        JavaFields.FINAL.addFields(javaClass, String.class, "assetId", "author", "title");
        JavaFields.FINAL.addBytes(javaClass, "height", "width");

        final JavaMethod vanillaMethod = vanillaCommons.createVanillaMethod(javaClass);

        vanillaCommons.fillVanillaMethodCode(vanillaMethod, CLASS_NAME, "assetId, author, title, (byte)height, (byte)width");
        vanillaMethod.getParameters().forEach(parameter -> {
            if (parameter.getType().equals("byte")) {
                parameter.setType("int");
            }
        });

        javaClass.addMethods(vanillaMethod);
        JavaGetterAndSetters.DEFAULT.addGetters(javaClass);
        return javaClass;
    }
}
