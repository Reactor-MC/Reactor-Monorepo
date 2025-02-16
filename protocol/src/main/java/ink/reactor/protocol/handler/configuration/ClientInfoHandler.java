package ink.reactor.protocol.handler.configuration;

import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.configuration.PacketInClientInformation;
import ink.reactor.protocol.PlayerConnectionImpl;

final class ClientInfoHandler implements PacketHandler {

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        new PacketInClientInformation(connection.getPlayer()).read(data);
    }

    @Override
    public int packetId() {
        return InProtocol.CONFIGURATION_CLIENT_INFORMATION;
    }
}