package ink.reactor.protocol.outbound;

public interface OutboundPacket {
    byte[] write();
    int getPacketId();
}
