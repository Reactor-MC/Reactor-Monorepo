package ink.reactor.protocol.outbound.configuration;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.util.ComponentCombiner;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.protocol.outbound.OutProtocol;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutConfigDisconnected implements PacketOutbound {
    private final ChatComponent[] components;

    @Override
    public byte[] write() {
        return NBTByteWriter.writeNBT(ComponentCombiner.toNBT(components), false);
    }

    @Override
    public int getId() {
        return OutProtocol.CONFIGURATION_DISCONNECT;
    }
}
