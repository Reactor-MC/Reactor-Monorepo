package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.item.ItemStack;
import ink.reactor.item.serializer.ItemStackByteSerializer;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.writer.FriendlyBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutContainerSetSlot implements PacketOutbound {
    private final int windowId;
    private final int stateId;
    private final int slot;
    private final ItemStack itemStack;

    @Override
    public byte[] write() {
        final FriendlyBuffer buffer = new FriendlyBuffer(4 + (itemStack.hasComponents() ? 32 : 0));
        buffer.writeVarInt(windowId);
        buffer.writeVarInt(stateId);
        buffer.writeShort(slot);

        ItemStackByteSerializer.toBytes(itemStack, buffer);

        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_CONTAINER_SET_SLOT;
    }
}
