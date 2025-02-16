package ink.reactor.api.inventory;

import ink.reactor.item.ItemStack;

public interface Inventory {
    String getName();
    InventoryHolder getInventoryHolder();

    int getInventorySize();
    ItemStack[] getItems();

    void setItem(final int slot, final ItemStack itemStack);

    int getId();
}