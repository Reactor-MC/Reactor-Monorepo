package ink.reactor.item.component;

public final class ItemComponent {

    private ItemComponent() {
        throw new IllegalAccessError("This class is only for storage purposes and can't be a instance");
    }

    public static final byte
        CUSTOM_DATA = 0,
        MAX_STACK_SIZE = 1,
        MAX_DAMAGE = 2,
        DAMAGE = 3,
        UNBREAKABLE = 4,
        CUSTOM_NAME = 5,
        ITEM_NAME = 6,
        ITEM_MODEL = 7,
        LORE = 8,
        RARITY = 9,
        ENCHANTMENTS = 10,
        CAN_PLACE_ON = 11,
        CAN_BREAK = 12,
        ATTRIBUTE_MODIFIERS = 13,
        CUSTOM_MODEL_DATA = 14,
        HIDE_ADDITIONAL_TOOLTIP = 15,
        HIDE_TOOLTIP = 16,
        REPAIR_COST = 17,
        CREATIVE_SLOT_LOCK = 18,
        ENCHANTMENT_GLINT_OVERRIDE = 19,
        INTANGIBLE_PROJECTILE = 20,
        FOOD = 21,
        CONSUMABLE = 22,
        USE_REMAINDER = 23,
        USE_COOLDOWN = 24,
        DAMAGE_RESISTANT = 25,
        TOOL = 26,
        ENCHANTABLE = 27,
        EQUIPPABLE = 28,
        REPAIRABLE = 29,
        GLIDER = 30,
        TOOLTIP_STYLE = 31,
        DEATH_PROTECTION = 32,
        STORED_ENCHANTMENTS = 33,
        DYED_COLOR = 34,
        MAP_COLOR = 35,
        MAP_ID = 36,
        MAP_DECORATIONS = 37,
        MAP_POST_PROCESSING = 38,
        CHARGED_PROJECTILES = 39,
        BUNDLE_CONTENTS = 40,
        POTION_CONTENTS = 41,
        SUSPICIOUS_STEW_EFFECTS = 42,
        WRITABLE_BOOK_CONTENT = 43,
        WRITTEN_BOOK_CONTENT = 44,
        TRIM = 45,
        DEBUG_STICK_STATE = 46,
        ENTITY_DATA = 47,
        BUCKET_ENTITY_DATA = 48,
        BLOCK_ENTITY_DATA = 49,
        INSTRUMENT = 50,
        OMINOUS_BOTTLE_AMPLIFIER = 51,
        JUKEBOX_PLAYABLE = 52,
        RECIPES = 53,
        LODESTONE_TRACKER = 54,
        FIREWORK_EXPLOSION = 55,
        FIREWORKS = 56,
        PROFILE = 57,
        NOTE_BLOCK_SOUND = 58,
        BANNER_PATTERNS = 59,
        BASE_COLOR = 60,
        POT_DECORATIONS = 61,
        CONTAINER = 62,
        BLOCK_STATE = 63,
        BEES = 64,
        LOCK = 65,
        CONTAINER_LOOT = 66;

    public static String getName(final byte id) {
        switch (id) {
            case CUSTOM_DATA: return "CUSTOM_DATA";
            case MAX_STACK_SIZE: return "MAX_STACK_SIZE";
            case MAX_DAMAGE: return "MAX_DAMAGE";
            case DAMAGE: return "DAMAGE";
            case UNBREAKABLE: return "UNBREAKABLE";
            case CUSTOM_NAME: return "CUSTOM_NAME";
            case ITEM_NAME: return "ITEM_NAME";
            case ITEM_MODEL: return "ITEM_MODEL";
            case LORE: return "LORE";
            case RARITY: return "RARITY";
            case ENCHANTMENTS: return "ENCHANTMENTS";
            case CAN_PLACE_ON: return "CAN_PLACE_ON";
            case CAN_BREAK: return "CAN_BREAK";
            case ATTRIBUTE_MODIFIERS: return "ATTRIBUTE_MODIFIERS";
            case CUSTOM_MODEL_DATA: return "CUSTOM_MODEL_DATA";
            case HIDE_ADDITIONAL_TOOLTIP: return "HIDE_ADDITIONAL_TOOLTIP";
            case HIDE_TOOLTIP: return "HIDE_TOOLTIP";
            case REPAIR_COST: return "REPAIR_COST";
            case CREATIVE_SLOT_LOCK: return "CREATIVE_SLOT_LOCK";
            case ENCHANTMENT_GLINT_OVERRIDE: return "ENCHANTMENT_GLINT_OVERRIDE";
            case INTANGIBLE_PROJECTILE: return "INTANGIBLE_PROJECTILE";
            case FOOD: return "FOOD";
            case CONSUMABLE: return "CONSUMABLE";
            case USE_REMAINDER: return "USE_REMAINDER";
            case USE_COOLDOWN: return "USE_COOLDOWN";
            case DAMAGE_RESISTANT: return "DAMAGE_RESISTANT";
            case TOOL: return "TOOL";
            case ENCHANTABLE: return "ENCHANTABLE";
            case EQUIPPABLE: return "EQUIPPABLE";
            case REPAIRABLE: return "REPAIRABLE";
            case GLIDER: return "GLIDER";
            case TOOLTIP_STYLE: return "TOOLTIP_STYLE";
            case DEATH_PROTECTION: return "DEATH_PROTECTION";
            case STORED_ENCHANTMENTS: return "STORED_ENCHANTMENTS";
            case DYED_COLOR: return "DYED_COLOR";
            case MAP_COLOR: return "MAP_COLOR";
            case MAP_ID: return "MAP_ID";
            case MAP_DECORATIONS: return "MAP_DECORATIONS";
            case MAP_POST_PROCESSING: return "MAP_POST_PROCESSING";
            case CHARGED_PROJECTILES: return "CHARGED_PROJECTILES";
            case BUNDLE_CONTENTS: return "BUNDLE_CONTENTS";
            case POTION_CONTENTS: return "POTION_CONTENTS";
            case SUSPICIOUS_STEW_EFFECTS: return "SUSPICIOUS_STEW_EFFECTS";
            case WRITABLE_BOOK_CONTENT: return "WRITABLE_BOOK_CONTENT";
            case WRITTEN_BOOK_CONTENT: return "WRITTEN_BOOK_CONTENT";
            case TRIM: return "TRIM";
            case DEBUG_STICK_STATE: return "DEBUG_STICK_STATE";
            case ENTITY_DATA: return "ENTITY_DATA";
            case BUCKET_ENTITY_DATA: return "BUCKET_ENTITY_DATA";
            case BLOCK_ENTITY_DATA: return "BLOCK_ENTITY_DATA";
            case INSTRUMENT: return "INSTRUMENT";
            case OMINOUS_BOTTLE_AMPLIFIER: return "OMINOUS_BOTTLE_AMPLIFIER";
            case JUKEBOX_PLAYABLE: return "JUKEBOX_PLAYABLE";
            case RECIPES: return "RECIPES";
            case LODESTONE_TRACKER: return "LODESTONE_TRACKER";
            case FIREWORK_EXPLOSION: return "FIREWORK_EXPLOSION";
            case FIREWORKS: return "FIREWORKS";
            case PROFILE: return "PROFILE";
            case NOTE_BLOCK_SOUND: return "NOTE_BLOCK_SOUND";
            case BANNER_PATTERNS: return "BANNER_PATTERNS";
            case BASE_COLOR: return "BASE_COLOR";
            case POT_DECORATIONS: return "POT_DECORATIONS";
            case CONTAINER: return "CONTAINER";
            case BLOCK_STATE: return "BLOCK_STATE";
            case BEES: return "BEES";
            case LOCK: return "LOCK";
            case CONTAINER_LOOT: return "CONTAINER_LOOT";
            default: throw new IllegalArgumentException("Unknown component ID: " + id);
        }
    }
}