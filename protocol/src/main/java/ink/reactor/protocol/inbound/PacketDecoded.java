package ink.reactor.protocol.inbound;

public record PacketDecoded(
        int id,
        PacketInData data
) {}
