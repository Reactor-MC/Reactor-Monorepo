package ink.reactor.protocol.inbound.handshake;

import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.PacketInbound;

public final class PacketInPing implements PacketInbound {

    public long timestamp;

    @Override
    public void read(PacketInData data) {
        timestamp = data.buffer.readLong();
    }
}
