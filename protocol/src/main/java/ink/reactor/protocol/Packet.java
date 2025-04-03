package ink.reactor.protocol;

public record Packet(
    int id,
    PacketData data
) {}
