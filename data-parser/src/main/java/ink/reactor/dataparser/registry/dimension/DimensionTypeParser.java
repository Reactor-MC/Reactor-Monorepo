package ink.reactor.dataparser.registry.dimension;

import java.io.IOException;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;
import ink.reactor.dataparser.util.AppendOptions;

public final class DimensionTypeParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/dimension");
        final JSONObject jsonObject = loadJsonObject("registry/dimension_types.json");
        final StringBuilder builder = new StringBuilder();

        final AppendOptions options = new AppendOptions()
            .setSpacesInNewLine(12)
            .setStartInNewLine(true);

        int i = 0;
        for (final Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject type)) {
                continue;
            }
            final Object monsterSpawnLightLevel = type.get("monster_spawn_light_level");
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new WorldType(");

            append(options, builder,
                entry.getKey(),
                type.getLongValue("fixed_time"),
                type.getBoolean("has_skylight"),
                type.getBoolean("has_ceiling"),
                type.getBoolean("ultrawarm"),
                type.getBoolean("natural"),
                type.getDoubleValue("coordinate_scale"),
                type.getBoolean("bed_works"),
                type.getBoolean("respawn_anchor_works"),
                type.getIntValue("min_y"),
                type.getIntValue("height"),
                type.getIntValue("logical_height"),
                type.getString("infiniburn"),
                type.getString("effects"),
                type.getFloatValue("ambient_light"),
                type.getBoolean("piglin_safe"),
                type.getBoolean("has_raids"),
                (monsterSpawnLightLevel instanceof Integer) ? (int)monsterSpawnLightLevel : 0,
                type.getIntValue("monster_spawn_block_light_limit")
            );

            builder.append(')');

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "WorldType");
    }
}
