package ink.reactor.protocol.decoder;

import ink.reactor.protocol.inbound.PacketInData;

public record PacketDecoded(
        int id,
        PacketInData data
) {}
