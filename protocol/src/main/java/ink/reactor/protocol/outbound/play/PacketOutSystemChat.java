package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.nbt.NBT;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.writer.FriendlyBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutSystemChat implements PacketOutbound {

    private final NBT nbt;

    public PacketOutSystemChat(ChatComponent chatComponent) {
        this.nbt = chatComponent.toNBT();
    }

    @Override
    public byte[] write() {
        final FriendlyBuffer buffer = new FriendlyBuffer(100);
        NBTByteWriter.writeNBT(nbt, buffer);
        buffer.writeBoolean(false);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_SYSTEM_CHAT;
    }
}