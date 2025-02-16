package ink.reactor.protocol.outbound;

import ink.reactor.api.player.connection.PacketOutbound;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CachedPacket implements PacketOutbound {

    private final byte[] data;
    private final int id;

    @Override
    public byte[] write() {
        return data;
    }

    @Override
    public int getId() {
        return id;
    }
}
