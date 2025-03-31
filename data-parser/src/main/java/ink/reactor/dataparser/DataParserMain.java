package ink.reactor.dataparser;

import ink.reactor.dataparser.type.banner.BannerDataParser;
import ink.reactor.dataparser.type.biome.BiomeDataParser;
import ink.reactor.dataparser.type.block.BlockDataParser;
import ink.reactor.dataparser.type.chat.ChatDataParser;
import ink.reactor.dataparser.type.damage.DamageTypeDataParser;
import ink.reactor.dataparser.type.dimension.DimensionDataParser;
import ink.reactor.dataparser.type.entity.EntityDataParser;
import ink.reactor.dataparser.type.item.ItemDataParser;
import ink.reactor.dataparser.type.packet.PacketDataParser;
import ink.reactor.dataparser.type.painting.PaintingDataParser;
import ink.reactor.dataparser.type.potion.PotionDataParser;
import ink.reactor.dataparser.type.sound.JukeboxDataParser;
import ink.reactor.dataparser.type.sound.SoundDataParser;
import ink.reactor.dataparser.type.trim.TrimMaterialDataParser;
import ink.reactor.dataparser.type.trim.TrimPatternDataParser;
import ink.reactor.dataparser.type.wolf.WolfDataParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataParserMain {

    private static final String PACKAGE_NAME = "ink.reactor.output";

    private static final Map<String, DataParser> OPTIONS = new HashMap<>();
    static {
        OPTIONS.put("painting", new PaintingDataParser());
        OPTIONS.put("wolf", new WolfDataParser());
        OPTIONS.put("banner", new BannerDataParser());
        OPTIONS.put("damage", new DamageTypeDataParser());
        OPTIONS.put("trim-material", new TrimMaterialDataParser());
        OPTIONS.put("trim-pattern", new TrimPatternDataParser());
        OPTIONS.put("dimension", new DimensionDataParser());
        OPTIONS.put("jukebox", new JukeboxDataParser());
        OPTIONS.put("sound", new SoundDataParser());
        OPTIONS.put("chat", new ChatDataParser());
        OPTIONS.put("biome", new BiomeDataParser());
        OPTIONS.put("potion", new PotionDataParser());
        OPTIONS.put("item", new ItemDataParser());
        OPTIONS.put("entity", new EntityDataParser());
        OPTIONS.put("packet", new PacketDataParser());
        OPTIONS.put("block", new BlockDataParser());
    }

    public static void main(final String[] args) {
        String parserName;

        if (args.length == 0) {
            parserName = "block";
            System.out.println("Using default parser " + parserName + ". Available options: " + OPTIONS.keySet());
        } else {
            parserName = args[0];
        }

        parserName = parserName.toLowerCase();

        if (parserName.equals("all")) {
            OPTIONS.forEach((key, value) -> load(value, key));
            return;
        }

        final DataParser parser = OPTIONS.get(parserName);

        if (parser == null) {
            System.out.println("Can't found the parser "+ parserName);
            return;
        }
        load(parser, parserName);
    }

    private static void load(final DataParser dataParser, final String parserName) {
        try {
            long time = System.currentTimeMillis();

            dataParser.parse(PACKAGE_NAME);

            time = System.currentTimeMillis() - time;
            System.out.println("Parse " + parserName + " in: " + time + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}