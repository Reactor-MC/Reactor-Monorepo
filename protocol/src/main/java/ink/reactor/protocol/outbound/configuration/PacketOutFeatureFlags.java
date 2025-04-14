package ink.reactor.protocol.outbound.configuration;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutFeatureFlags implements PacketOutbound {

    private final String[] identifiers;

    public PacketOutFeatureFlags(String... identifiers) {
        this.identifiers = identifiers;
    }

    @Override
    public byte[] write() {
        int size = DataSize.varInt(identifiers.length);
        for (final String id : identifiers) {
            size += DataSize.string(id);
        }

        final ExpectedSizeBuffer eBuffer = new ExpectedSizeBuffer(size);
        eBuffer.writeVarInt(identifiers.length);
        for (final String id : identifiers) {
            eBuffer.writeString(id);
        }
        return eBuffer.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.CONFIGURATION_UPDATE_ENABLED_FEATURES;
    }
}
