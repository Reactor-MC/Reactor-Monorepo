package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.util.ComponentCombiner;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.writer.DynamicSizeBuffer;

public final class PacketOutSetSubtitleText implements PacketOutbound {

    private final ChatComponent[] subtitle;

    public PacketOutSetSubtitleText(ChatComponent[] component) {
        this.subtitle = component;
    }

    @Override
    public byte[] write() {
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(100);
        NBTByteWriter.writeNBT(ComponentCombiner.toNBT(subtitle), buffer);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_SET_SUBTITLE_TEXT;
    }
}
