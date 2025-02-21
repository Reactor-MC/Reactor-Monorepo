package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.util.ComponentCombiner;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.writer.FriendlyBuffer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class PacketOutSetActionBarText implements PacketOutbound {

    private final ChatComponent[] actionBarText;

    @Override
    public byte[] write() {
        final FriendlyBuffer buffer = new FriendlyBuffer(100);
        NBTByteWriter.writeNBT(ComponentCombiner.toNBT(actionBarText), buffer);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_SET_ACTION_BAR_TEXT;
    }
}
