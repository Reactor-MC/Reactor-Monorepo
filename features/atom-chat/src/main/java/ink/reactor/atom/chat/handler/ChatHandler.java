package ink.reactor.atom.chat.handler;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.atom.chat.module.chat.config.ChatConfig;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.RawComponent;
import ink.reactor.chat.format.ChatLegacy;
import ink.reactor.chat.format.minimessage.MiniMessage;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.play.PacketInChatMessage;
import ink.reactor.protocol.outbound.play.PacketOutSystemChat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ChatHandler implements PacketHandler {

    private final ChatConfig chatConfig;

    @Override
    public void handle(PlayerConnection connection, int packetId, final PacketInData data) {
        final PacketInChatMessage packetInChatMessage = new PacketInChatMessage();
        packetInChatMessage.read(data);

        ChatComponent[] message;

        if (chatConfig.getParserColor().isLegacy()) {
            message = ChatLegacy.format(packetInChatMessage.getMessage());
        } else if (chatConfig.getParserColor().isMinimessage()) {
            message = MiniMessage.format(packetInChatMessage.getMessage());
        } else {
            message = new RawComponent[] { new RawComponent(packetInChatMessage.getMessage()) };
        }

        connection.sendPacket(new PacketOutSystemChat(message));
    }

    @Override
    public int packetId() {
        return InProtocol.PLAY_CHAT;
    }
}
