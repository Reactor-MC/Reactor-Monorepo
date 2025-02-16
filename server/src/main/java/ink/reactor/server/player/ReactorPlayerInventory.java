package ink.reactor.server.player;

import ink.reactor.api.inventory.InventoryHolder;
import ink.reactor.api.player.data.PlayerInventory;
import ink.reactor.item.ItemStack;
import ink.reactor.protocol.outbound.play.PacketOutContainerSetSlot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
final class ReactorPlayerInventory implements PlayerInventory {

    private final ItemStack[] items = new ItemStack[45];
    private final ReactorPlayer player;

    private int stateId = 0;

    ReactorPlayerInventory(ReactorPlayer player) {
        this.player = player;
    }

    @Override
    public InventoryHolder getInventoryHolder() {
        return player;
    }

    @Override
    public int getInventorySize() {
        return 45;
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        items[slot] = itemStack;
        player.getConnection().sendPacket(new PacketOutContainerSetSlot(0, nextStateId(), slot, itemStack));        
    }

    private int nextStateId() {
        if (++stateId == Short.MAX_VALUE) {
            stateId = 0;
        }
        return stateId;
    }

    @Override
    public int getId() {
        return 0;
    }
}
