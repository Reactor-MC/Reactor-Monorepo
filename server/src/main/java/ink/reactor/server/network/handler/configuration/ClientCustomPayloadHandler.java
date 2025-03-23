package ink.reactor.server.network.handler.configuration;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.outbound.configuration.PacketOutPluginMessage;
import ink.reactor.protocol.outbound.configuration.PacketOutSelectKnownPacks;
import ink.reactor.api.player.connection.PacketOutbound;

public final class ClientCustomPayloadHandler implements PacketHandler {
    private static final PacketOutbound
        BRAND = new PacketOutPluginMessage("minecraft:brand", "reactor".getBytes()),
        PACK_MINECRAFT_CORE = new PacketOutSelectKnownPacks("minecraft", "core", "1.0.0");

    @Override
    public void handle(PlayerConnection connection, int packetId, PacketInData data) {
        connection.sendPackets(BRAND, PACK_MINECRAFT_CORE);
    }

    @Override
    public int packetId() {
        return InProtocol.CONFIGURATION_CUSTOM_PAYLOAD;
    }
}
