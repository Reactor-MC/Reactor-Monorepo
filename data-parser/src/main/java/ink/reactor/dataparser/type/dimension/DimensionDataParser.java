package ink.reactor.dataparser.type.dimension;

import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.DataParserTemplateCommon;
import ink.reactor.dataparser.common.ParserFiles;
import ink.reactor.dataparser.common.ParserPlaceholders;
import ink.reactor.dataparser.common.VanillaCommons;
import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.classes.JavaClassType;
import ink.reactor.fission.classes.JavaConstructor;
import ink.reactor.fission.field.JavaFields;
import ink.reactor.fission.method.JavaGetterAndSetters;
import ink.reactor.fission.method.JavaMethod;

import java.io.IOException;
import java.util.Map;

public class DimensionDataParser implements DataParser {

    private static final String CLASS_NAME = "DimensionType";

    @Override
    public void parse(String packageName) throws IOException {
        final JavaClass javaClass = DataParserTemplateCommon.createTemplate(getClass(), CLASS_NAME, packageName);

        JavaFields.FINAL.addFields(javaClass, String.class, "name", "infiniburn", "effects");
        JavaFields.FINAL.addLongs(javaClass, "fixedTime");
        JavaFields.FINAL.addFields(javaClass, "boolean", "hasSkyLight", "hasCeiling", "ultrawarm", "natural", "bedWorks", "respawnAnchorWorks", "piglinSafe", "hasRaids");
        JavaFields.FINAL.addInts(javaClass, "id", "minY", "height", "localHeight", "monsterSpawnLightLevel", "monsterSpawnBlockLightLimit");
        JavaFields.FINAL.addDoubles(javaClass, "coordinateScale", "ambientLight");

        DataParserTemplateCommon.addVanillaData(CLASS_NAME, javaClass);

        final JSONObject jsonObject = ParserFiles.loadJsonObject("dimensions.json");
        int i = 0;

        for (final Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject dimension)) {
                continue;
            }
            final Object monsterSpawnLightLevel = dimension.get("monster_spawn_light_level");
            javaClass.addFields(VanillaCommons.DEFAULT.createVanillaField(CLASS_NAME, entry.getKey(),
                    entry.getKey(),
                    dimension.getString("infiniburn"),
                    dimension.getString("effects"),

                    dimension.getLongValue("fixed_time"),

                    dimension.getBoolean("has_skylight"),
                    dimension.getBoolean("has_ceiling"),
                    dimension.getBoolean("ultrawarm"),
                    dimension.getBoolean("natural"),
                    dimension.getBoolean("bed_works"),
                    dimension.getBoolean("respawn_anchor_works"),
                    dimension.getBoolean("piglin_safe"),
                    dimension.getBoolean("has_raids"),

                    i++,
                    dimension.getIntValue("min_y"),
                    dimension.getIntValue("height"),
                    dimension.getIntValue("logical_height"),
                    dimension.getIntValue("monster_spawn_block_light_limit"),
                    (monsterSpawnLightLevel instanceof Integer) ? monsterSpawnLightLevel : 0,

                    dimension.getDoubleValue("coordinate_scale"),
                    dimension.getDoubleValue("ambient_light")
            ));
        }

        final JavaMethod hashCode = new JavaMethod("hashCode", "return id;");
        hashCode.setReturnObjectType("int");
        javaClass.addMethods(hashCode);

        JavaConstructor.DEFAULT.add(javaClass);
        JavaGetterAndSetters.DEFAULT.add(javaClass);

        final String fileContent = javaClass.toString().replace(ParserPlaceholders.ALL_VALUES, String.valueOf(javaClass.getStaticFields()-1));
        ParserFiles.writeJava(fileContent, CLASS_NAME);
    }
}