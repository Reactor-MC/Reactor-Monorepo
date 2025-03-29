package ink.reactor.dataparser.type.item;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.ParserFiles;
import ink.reactor.fission.JavaVisibility;
import ink.reactor.fission.classes.JavaConstructor;
import ink.reactor.fission.classes.enums.JavaEnum;
import ink.reactor.fission.classes.enums.JavaEnumObject;
import ink.reactor.fission.commentary.MultiLineCommentary;
import ink.reactor.fission.field.JavaFields;
import ink.reactor.fission.method.JavaGetterAndSetters;
import ink.reactor.fission.method.JavaMethod;

import java.io.IOException;

public class ItemDataParser implements DataParser {

    private static final String CLASS_NAME = "Material";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaEnum javaEnum = new JavaEnum(packageName, CLASS_NAME);
        javaEnum.setCommentary(MultiLineCommentary.of(
                """
                Thanks to minecraft-data for collect all items.
                <a href="https://github.com/PrismarineJS/minecraft-data/blob/master/data/pc/1.21.4/items.json">Items</a>"""
        ));

        JavaFields.FINAL.addInts(javaEnum,"stackSize");

        final JSONArray jsonArray = ParserFiles.loadJsonArray("items.json");

        for (final Object entry : jsonArray) {
            if (entry instanceof JSONObject material) {
                final JavaEnumObject javaEnumObject = new JavaEnumObject(material.getString("name").toUpperCase());
                final int stackSize = material.getIntValue("stackSize");
                if (stackSize != 64) {
                    javaEnumObject.addParameters(stackSize);
                }
                javaEnum.addEnumObjects(javaEnumObject);
            }
        }

        JavaConstructor.DEFAULT.add(javaEnum).setVisibility(JavaVisibility.DEFAULT);

        final JavaMethod noArgsConstructor = new JavaMethod(CLASS_NAME);
        noArgsConstructor.setReturnObjectType(null);
        noArgsConstructor.setVisibility(JavaVisibility.DEFAULT);
        noArgsConstructor.setCodeBlock("this.stackSize = 64;");
        javaEnum.addMethods(noArgsConstructor);

        JavaGetterAndSetters.DEFAULT.add(javaEnum);

        ParserFiles.writeJava(javaEnum.toString(), CLASS_NAME);
    }
}
