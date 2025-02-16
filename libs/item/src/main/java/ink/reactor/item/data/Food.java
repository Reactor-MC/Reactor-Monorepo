package ink.reactor.item.data;

import lombok.Getter;

@Getter
public enum Food {

    APPLE(831, 4, 0.3F),
    BAKED_POTATO(1150, 5, 0.6F),
    BEEF(1037, 3, 0.3F),
    BEETROOT(1206, 1, 0.6F),
    BEETROOT_SOUP(1208, 6, 0.6F),
    BREAD(886, 5, 0.6F),
    CARROT(1148, 3, 0.6F),
    CHICKEN(1039, 2, 0.3F),
    CHORUS_FRUIT(1202, 4, 0.3F, true),
    COD(984, 2, 0.1F),
    COOKED_BEEF(1038, 8, 0.8F),
    COOKED_CHICKEN(1040, 6, 0.6F),
    COOKED_COD(988, 5, 0.6F),
    COOKED_MUTTON(1184, 6, 0.8F),
    COOKED_PORKCHOP(913, 8, 0.8F),
    COOKED_RABBIT(1171, 5, 0.6F),
    COOKED_SALMON(989, 6, 0.8F),
    COOKIE(1029, 2, 0.1F),
    DRIED_KELP(1034, 1, 0.3F),
    ENCHANTED_GOLDEN_APPLE(916, 4, 1.2F, true),
    GOLDEN_APPLE(915, 4, 1.2F, true),
    GOLDEN_CARROT(1153, 6, 1.2F),
    HONEY_BOTTLE(1277, 6, 0.1F, true),
    MELON_SLICE(1033, 2, 0.3F),
    MUSHROOM_STEW(880, 6, 0.6F),
    MUTTON(1183, 2, 0.3F),
    POISONOUS_POTATO(1151, 2, 0.3F),
    PORKCHOP(912, 3, 0.3F),
    POTATO(1149, 1, 0.3F),
    PUFFERFISH(987, 1, 0.1F),
    PUMPKIN_PIE(1162, 8, 0.3F),
    RABBIT(1170, 3, 0.3F),
    RABBIT_STEW(1172, 10, 0.6F),
    ROTTEN_FLESH(1041, 4, 0.1F),
    SALMON(985, 2, 0.1F),
    SPIDER_EYE(1049, 2, 0.8F),
    SUSPICIOUS_STEW(1244, 6, 0.6F, true),
    SWEET_BERRIES(1269, 2, 0.1F),
    GLOW_BERRIES(1270, 2, 0.1F),
    TROPICAL_FISH(986, 1, 0.1F);

    public static final Food[] ALL = values();

    private final int itemID;
    private final int nutrition;
    private final float saturation;
    private final boolean alwaysEdible; // Whether the item can always be eaten, even at full hunger.

    private Food(int itemID, int nutrition, float saturation) {
        this(itemID, nutrition, saturation, false);
    }

    private Food(int itemID, int nutrition, float saturation, boolean alwaysEdible) {
        this.itemID = itemID;
        this.nutrition = nutrition;
        this.saturation = saturation;
        this.alwaysEdible = alwaysEdible;
    }

    public static Food search(int nutrition, float saturation, boolean isAlwaysEdible) {
        for (final Food food : ALL) {
            if (food.nutrition == nutrition && food.saturation == saturation && food.alwaysEdible == isAlwaysEdible) {
                return food;
            }
        }
        return null;
    }
}