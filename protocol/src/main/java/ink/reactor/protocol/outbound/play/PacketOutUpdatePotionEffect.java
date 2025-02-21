package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.entity.effect.MobEffect;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutUpdatePotionEffect implements PacketOutbound {
    private final int entityId;
    private final MobEffect effect;
    private final boolean ambient, showParticles, showIcon, blend;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer expectedSizeBuffer = new ExpectedSizeBuffer(
            DataSize.varInt(entityId) + DataSize.varInt(effect.getAmplifier()) + DataSize.varInt(effect.getDuration()) + (DataSize.BYTE * 2)
        );

        expectedSizeBuffer.writeVarInt(entityId);
        expectedSizeBuffer.writeVarInt(effect.getType().id);
        expectedSizeBuffer.writeVarInt(effect.getAmplifier());
        expectedSizeBuffer.writeVarInt(effect.getDuration());

        byte bitmask = 0;
        if (ambient) bitmask |= 0x01;
        if (showParticles) bitmask |= 0x02;
        if (showIcon) bitmask |= 0x04;
        if (blend) bitmask |= 0x08;

        expectedSizeBuffer.writeByte(bitmask);

        return expectedSizeBuffer.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_UPDATE_MOB_EFFECT;
    }
}
