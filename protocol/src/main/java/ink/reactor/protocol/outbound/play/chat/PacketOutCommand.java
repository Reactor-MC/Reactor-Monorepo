package ink.reactor.protocol.outbound.play.chat;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.command.Command;
import ink.reactor.command.argument.ArgumentType;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.writer.DynamicSizeBuffer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class PacketOutCommand implements PacketOutbound {
    private final Command command;

    @Override
    public byte[] write() {
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(30);

        final List<ArgumentType> argumentTypes = command.getArgs().argumentTypes();

        buffer.writeVarInt(0);

        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_COMMANDS;
    }
}
