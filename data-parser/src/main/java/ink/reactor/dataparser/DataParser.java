package ink.reactor.dataparser;

import java.io.IOException;

import ink.reactor.dataparser.registry.banner.BannerParser;
import ink.reactor.dataparser.registry.biome.BiomeParser;
import ink.reactor.dataparser.registry.block.BlockParser;
import ink.reactor.dataparser.registry.chat.ChatParser;
import ink.reactor.dataparser.registry.damage.DamageTypeParser;
import ink.reactor.dataparser.registry.dimension.DimensionTypeParser;
import ink.reactor.dataparser.registry.enchantment.EnchantmentParser;
import ink.reactor.dataparser.registry.jukebox.JukeboxParser;
import ink.reactor.dataparser.registry.painting.PaintingParser;
import ink.reactor.dataparser.registry.trim.TrimMaterialParser;
import ink.reactor.dataparser.registry.trim.TrimPatternParser;
import ink.reactor.dataparser.registry.wolf.WolfVariantParser;
import ink.reactor.dataparser.report.PacketIdParser;
import ink.reactor.dataparser.tag.entity.EntityParser;
import ink.reactor.dataparser.tag.item.ItemParser;

public final class DataParser {

    public static void main(String[] args) {
        final String parserName = "packets";
        final String option = "report";

        Parser parser = null;

        if (option.equals("report")) {
            parser = handleReport(parserName);
        } else if (option.equals("registry")) {
            parser = handleRegistry(parserName);
        } else if (option.equals("tags")) {
            parser = handleTags(parserName);
        }

        if (parser == null) {
            System.out.println("Can't found the option " + option);
            return;
        }
        try {
            parser.load();
        } catch (IOException e) {
            System.err.println("Error trying in registry parser. Parser loader:" + parserName);
            e.printStackTrace();
        }
    }

    private static Parser handleTags(final String parserName) {
        return switch(parserName) {
            case "entity" -> new EntityParser();
            case "item" -> new ItemParser();
            default -> null;
        };
    }

    private static Parser handleReport(final String parserName) {
        return switch(parserName) {
            case "packets" -> new PacketIdParser();
            default -> null;
        };
    }

    private static Parser handleRegistry(final String parserName) {
        return switch(parserName) {
            case "chat" -> new ChatParser();
            case "block" -> new BlockParser();
            case "biome" -> new BiomeParser();
            case "banner" -> new BannerParser();
            case "jukebox" -> new JukeboxParser();
            case "wolf" -> new WolfVariantParser();
            case "damage" -> new DamageTypeParser();
            case "painting" -> new PaintingParser();
            case "dimension" -> new DimensionTypeParser();
            case "enchantment" -> new EnchantmentParser();
            case "trim-pattern" -> new TrimPatternParser();
            case "trim-material" -> new TrimMaterialParser();
            default -> null;
        };
    }
}
