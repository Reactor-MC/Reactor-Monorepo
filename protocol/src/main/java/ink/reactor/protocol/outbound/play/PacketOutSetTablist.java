package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.util.ComponentCombiner;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public final class PacketOutSetTablist implements PacketOutbound {

    private final ChatComponent[] header, footer;

    public PacketOutSetTablist(ChatComponent[] header, ChatComponent[] footer) {
        this.header = header;
        this.footer = footer;
    }

    @Override
    public byte[] write() {
        final FriendlyBuffer buffer = new FriendlyBuffer(32);

        NBTByteWriter.writeNBT(
            (header == null) ? ChatComponent.EMPTY.toNBT() : ComponentCombiner.toNBT(header),
            buffer
        );
        NBTByteWriter.writeNBT(
            (footer == null) ? ChatComponent.EMPTY.toNBT() : ComponentCombiner.toNBT(footer),
            buffer
        );

        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_TAB_LIST;
    }
}
