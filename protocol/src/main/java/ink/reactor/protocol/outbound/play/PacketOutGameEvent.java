package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutGameEvent implements PacketOutbound {

    // Available events
    public static final byte
        NO_RESPAWN_BLOCK_AVAILABLE = 0,
        BEGING_RAINING = 1,
        END_RAINING = 2,
        CHANGE_GAMEMODE = 3,
        WIN_GAME = 4,
        DEMO_EVENT = 5,
        ARROW_HIT_PLAYER = 6,
        RAIN_LEVEL_CHANGE = 7,
        THUNDER_LEVEL_CHANGE = 8,
        PLAY_PUFFERFISH_STING_SOUND = 9,
        PLAY_ELDER_GUARDIAN_MOB_APPEARANCE = 10,
        ENABLE_RESPAWN_SCREEN = 11,
        LIMITED_CRAFTING = 12,
        WAITING_FOR_LEVEL_CHUNKS = 13;

    private final byte event;
    private final float value;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(DataSize.BYTE + DataSize.FLOAT);
        buffer.writeByte(event);
        buffer.writeFloat(value);
        return buffer.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_GAME_EVENT;
    }
}
