package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.item.data.potion.PotionEffectType;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutRemovePotionEffect implements PacketOutbound {
    private final int entityId;
    private final PotionEffectType effect;

    @Override
    public byte[] write() {
        System.out.println("EXPIRO asdasdasdasd");
        final ExpectedSizeBuffer expectedSizeBuffer = new ExpectedSizeBuffer(DataSize.varInt(entityId) + DataSize.varInt(effect.id));
        expectedSizeBuffer.writeVarInt(entityId);
        expectedSizeBuffer.writeVarInt(effect.id);
        return expectedSizeBuffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_REMOVE_MOB_EFFECT;
    }
}
