package ink.reactor.protocol.outbound.play.chat;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.command.Command;
import ink.reactor.command.argument.ArgumentType;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.writer.FriendlyBuffer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class PacketOutCommand implements PacketOutbound {
    private final Command command;

    @Override
    public byte[] write() {
        final FriendlyBuffer buffer = new FriendlyBuffer(30);

        final List<ArgumentType> argumentTypes = command.getArgs().argumentTypes();

        buffer.writeVarInt(0);

        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_COMMANDS;
    }
}
