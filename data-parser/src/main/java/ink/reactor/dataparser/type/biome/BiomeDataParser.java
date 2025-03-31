package ink.reactor.dataparser.type.biome;

import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.append.AppendOptions;
import ink.reactor.dataparser.append.CustomAppender;
import ink.reactor.dataparser.common.ParserClass;
import ink.reactor.dataparser.common.ParserFiles;
import ink.reactor.fission.commentary.MultiLineCommentary;
import ink.reactor.fission.template.loader.TemplateLoader;

import java.io.IOException;
import java.util.Map;

public class BiomeDataParser implements DataParser {

    @Override
    public void parse(String packageName) throws IOException {
        final String header =
            MultiLineCommentary.of(ParserClass.combineCommentaries(ParserClass.DATA_GENERATOR, ParserClass.JSON_COMBINED))
            + "\npackage " + packageName + ";\n";

        final String template = header + TemplateLoader.load("template/biomes.template", getClass().getClassLoader());
        final JSONObject jsonObject = ParserFiles.loadJsonObject("biomes.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject biome)) {
                continue;
            }
            builder.append('\n');
            builder.append("        ");
            builder.append(entry.getKey().toUpperCase());
            builder.append(" = vanilla(new Biome(");
            CustomAppender.append(builder,
                    i,
                    entry.getKey(),
                    biome.getBooleanValue("has_precipitation"),
                    biome.getDoubleValue("temperature"),
                    biome.getOrDefault("temperature_modifier", "none"),
                    biome.getDoubleValue("downfall")
            );

            builder.append(',');
            builder.append(appendEffects(biome.getJSONObject("effects")));
            builder.append("))");
            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }
        final String fileContent = template
                .replace("{ALL_FIELD}", String.valueOf(jsonObject.size()))
                .replace("{OBJECT_FIELD_TYPE}", builder.toString());

        ParserFiles.writeJava(fileContent, "Biome");
    }

    private String appendEffects(final JSONObject effects) {
        if (effects == null) {
            return null;
        }
        final StringBuilder builder = new StringBuilder();
        final String mood = getMoodSound(effects.getJSONObject("mood_sound"));
        final String music = getMusic(effects.getJSONObject("music"));
        final String additionsSound = getAdditionsSound(effects.getJSONObject("additions_sound"));
        final String particle = getParticle(effects.getJSONObject("particle"));

        builder.append("new Effects(");
        CustomAppender.append(builder,
                effects.getIntValue("fog_color"),
                effects.getInteger("foliage_color"),
                effects.getInteger("grass_color"),
                effects.getString("grass_color_modifier"),
                effects.getString("ambient_sound"),
                effects.getIntValue("sky_color"),
                effects.getIntValue("water_color"),
                effects.getIntValue("water_fog_color"));

        builder.append(',');
        CustomAppender.append(new AppendOptions().setAddStringQuoteMarks(false), builder, mood, music, additionsSound, particle);

        builder.append(')');
        return builder.toString();
    }

    private String getMusic(final JSONObject music) {
        if (music == null) {
            return null;
        }
        final StringBuilder builder = new StringBuilder();
        builder.append("new Effects.Music(");
        CustomAppender.append(builder,
                music.getIntValue("max_delay"),
                music.getIntValue("min_delay"),
                music.getBooleanValue("replace_current_music"),
                music.getString("sound")
        );
        builder.append(')');
        return builder.toString();
    }

    private String getMoodSound(final JSONObject mood) {
        if (mood == null) {
            return null;
        }
        final StringBuilder builder = new StringBuilder();
        builder.append("new Effects.MoodSound(");
        CustomAppender.append(builder,
                mood.getString("sound"),
                mood.getIntValue("tick_delay"),
                mood.getIntValue("offset"),
                mood.getIntValue("block_search_extent")
        );
        builder.append(')');
        return builder.toString();
    }

    private String getAdditionsSound(final JSONObject sounds) {
        if (sounds == null) {
            return null;
        }
        final StringBuilder builder = new StringBuilder();
        builder.append("new Effects.AdditionSound(");
        CustomAppender.append(builder,
                sounds.getString("sound"),
                sounds.getDoubleValue("tick_chance")
        );
        builder.append(')');
        return builder.toString();
    }

    private String getParticle(final JSONObject particle) {
        if (particle == null) {
            return null;
        }
        final JSONObject options = particle.getJSONObject("options");
        if (options == null) {
            return null;
        }
        final StringBuilder builder = new StringBuilder();
        builder.append("new Effects.Particle(");
        CustomAppender.append(builder, options.getString("type"), particle.getDoubleValue("probability"));
        builder.append(')');
        return builder.toString();
    }
}
