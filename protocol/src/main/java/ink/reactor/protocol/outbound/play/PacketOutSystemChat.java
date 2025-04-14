package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.util.ComponentCombiner;
import ink.reactor.nbt.NBT;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.writer.DynamicSizeBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutSystemChat implements PacketOutbound {

    private final NBT nbt;

    public PacketOutSystemChat(ChatComponent chatComponent) {
        this.nbt = chatComponent.toNBT();
    }

    public PacketOutSystemChat(ChatComponent[] chatComponent) {
        this.nbt = ComponentCombiner.toNBT(chatComponent);
    }

    @Override
    public byte[] write() {
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(100);
        NBTByteWriter.writeNBT(nbt, buffer);
        buffer.writeBoolean(false);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_SYSTEM_CHAT;
    }
}