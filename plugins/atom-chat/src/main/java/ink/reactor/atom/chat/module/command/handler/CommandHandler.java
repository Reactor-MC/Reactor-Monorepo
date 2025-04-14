package ink.reactor.atom.chat.module.command.handler;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;

public final class CommandHandler implements PacketHandler {

    @Override
    public void handle(PlayerConnection connection, int packetId, PacketInData data) {
        final String command = data.readString();

        System.out.println("COMMAND: " + command);
    }

    @Override
    public int packetId() {
        return InProtocol.PLAY_CHAT_COMMAND;
    }
}
