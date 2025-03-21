package ink.reactor.dataparser;

import java.io.IOException;

import ink.reactor.dataparser.other.effect.EffectParser;
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

    public static void main(final String[] args) {
        String parserName, option;

        if (args.length == 0) {
            parserName = "biome";
            option = "registry";
        } else {
            if (args.length != 2) {
                System.out.println("Format: java -jar dataparser.jar (option) (parsername)");
                return;
            }
            option = args[0];
            parserName = args[1];
        }

        parserName = parserName.toLowerCase();
        option = option.toLowerCase();

        final Parser parser = switch(option) {
            case "other" -> handleOther(parserName);
            case "report" -> handleReport(parserName);
            case "tags" -> handleTags(parserName);
            case "registry" -> handleRegistry(parserName);
            default -> null;
        };

        if (parser == null) {
            System.out.println("Can't found the option " + option + " with the parser "+ parserName);
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

    private static Parser handleOther(final String parserName) {
        return switch (parserName) {
            case "effects" -> new EffectParser();
            default -> null;
        };
    }
}
