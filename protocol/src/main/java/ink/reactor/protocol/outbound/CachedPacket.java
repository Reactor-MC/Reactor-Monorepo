package ink.reactor.protocol.outbound;

import ink.reactor.api.player.connection.PacketOutbound;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CachedPacket implements PacketOutbound {

    private final byte[] data;
    private final int id;

    public static CachedPacket of(final PacketOutbound packetOutbound) {
        return new CachedPacket(packetOutbound.write(), packetOutbound.getId());
    }

    public static CachedPacket of(final byte[] data, final int id) {
        return new CachedPacket(data, id);
    }

    @Override
    public byte[] write() {
        return data;
    }

    @Override
    public int getId() {
        return id;
    }
}
