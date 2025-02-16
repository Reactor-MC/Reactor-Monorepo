package ink.reactor.api.player.data;

import ink.reactor.api.inventory.Inventory;

public interface PlayerInventory extends Inventory {
   
    @Override
    default String getName() {
        return "PLAYER_INVENTORY";
    }
}