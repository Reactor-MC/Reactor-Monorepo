package ink.reactor.protocol.outbound.login;

import java.util.UUID;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutLoginSuccess implements PacketOutbound {

    private final UUID uuid;
    private final String username;

    // TODO: Properties

    public PacketOutLoginSuccess(final UUID uuid, final String username) {
        this.uuid = uuid;
        this.username = username;
    }

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer data = new ExpectedSizeBuffer(
            DataSize.UUID +
            DataSize.string(username) +
            DataSize.BYTE
        );

        data.writeUUID(uuid);
        data.writeString(username);
        data.writeVarInt(0); // Properties

        return data.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.LOGIN_LOGIN_FINISHED;
    }
}