package ink.reactor.api.inventory;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class InventoryUtils {

    public static int toRows(final int inventoryItems) {
        return Math.ceilDiv(inventoryItems, 9);
    }

    public static int toSlots(final int rows) {
        return rows * 9;
    }
}