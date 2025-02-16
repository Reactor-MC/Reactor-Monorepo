package ink.reactor.item.data;

/*
 * This affects the default color of the item's name.
 */
public enum Rarity {
    COMMON,   // White
    UNCOMMON, // Yellow
    RARE,     // Aqua
    EPIC;     // Pink

    public static Rarity byId(int id) {
        return switch(id) {
            case 1 -> UNCOMMON;
            case 2 -> RARE;
            case 3 -> EPIC;
            default -> COMMON;
        };
    }
}