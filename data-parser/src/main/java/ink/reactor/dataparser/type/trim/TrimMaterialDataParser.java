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

public class TrimMaterialDataParser implements DataParser {
    private static final String CLASS_NAME = "TrimMaterial";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaClass javaClass = DataParserTemplateCommon.createTemplate(getClass(), CLASS_NAME, packageName);
        JavaFields.FINAL.addFields(javaClass, String.class, "assetName", "ingredient", "description", "overrideArmorAssets");
        JavaFields.FINAL.addDoubles(javaClass, "itemModelIndex");

        DataParserTemplateCommon.addVanillaData(CLASS_NAME, javaClass);

        final JSONObject jsonObject = ParserFiles.loadJsonObject("trim_material.json");

        for (final Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject trimMaterial)) {
                continue;
            }
            final JSONObject overrideArmorAssets = trimMaterial.getJSONObject("override_armor_assets");
            javaClass.addFields(VanillaCommons.DEFAULT.createVanillaField(CLASS_NAME, entry.getKey(),
                    trimMaterial.getString("asset_name"),
                    trimMaterial.getJSONObject("description").getString("translate"),
                    trimMaterial.getString("ingredient"),
                    overrideArmorAssets == null ? "" : overrideArmorAssets.firstEntry().getValue(),
                    trimMaterial.getDoubleValue("item_model_index")
            ));
        }

        final String fileContent = javaClass.toString().replace(ParserPlaceholders.ALL_VALUES, String.valueOf(javaClass.getStaticFields()-1));
        ParserFiles.writeJava(fileContent, CLASS_NAME);
    }
}