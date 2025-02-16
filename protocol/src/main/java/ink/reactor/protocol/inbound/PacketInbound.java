package ink.reactor.protocol.inbound;

public interface PacketInbound {
    void read(final PacketInData data);
}
