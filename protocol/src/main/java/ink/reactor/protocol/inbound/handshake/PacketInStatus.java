package ink.reactor.protocol.inbound.handshake;

import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.PacketInbound;

public final class PacketInStatus implements PacketInbound {

    public int protocolVersion;
    public String hostname;
    public int port;
    public int state;

    @Override
    public void read(final PacketInData data) {
        protocolVersion = data.readVarInt();
        hostname = data.readString(255);
        port = data.buffer.readUnsignedShort();
        state = data.readVarInt();
    }
}