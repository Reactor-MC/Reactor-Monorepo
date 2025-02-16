package ink.reactor.protocol.outbound.login;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.util.ComponentCombiner;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutLoginDisconnect implements PacketOutbound {

    private final byte[] data;

    public PacketOutLoginDisconnect(ChatComponent component) {
        data = fromJson(component.toJson());
    }

    public PacketOutLoginDisconnect(ChatComponent[] components) {
        data = components.length == 1
            ? fromJson(components[0].toJson()) 
            : fromJson(ComponentCombiner.toJsonArray(components));
    }

    private static byte[] fromJson(final String json) {
        final ExpectedSizeBuffer data = new ExpectedSizeBuffer(DataSize.string(json));
        data.writeString(json);
        return data.buffer;
    }

    @Override
    public byte[] write() {
        return data;
    }

    @Override
    public int getId() {
        return OutProtocol.LOGIN_LOGIN_DISCONNECT;
    }   
}
