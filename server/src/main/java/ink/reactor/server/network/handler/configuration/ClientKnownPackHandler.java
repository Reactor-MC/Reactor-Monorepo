package ink.reactor.server.network.handler.configuration;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.configuration.PacketInSelectKnownPack;
import ink.reactor.protocol.outbound.CachedPacket;
import ink.reactor.protocol.outbound.configuration.PacketOutFinishConfiguration;
import ink.reactor.protocol.outbound.configuration.Registries;

final class ClientKnownPackHandler implements PacketHandler {

    private static final CachedPacket
        WOLF_VARIANT = Registries.packet(Registries.wolfVariants()),
        DAMAGE_TYPES = Registries.packet(Registries.damageTypes()),
        TRIM_MATERIAL = Registries.packet(Registries.trimMaterial()),
        TRIM_PATTERN = Registries.packet(Registries.trimPattern()),
        BANNER = Registries.packet(Registries.banner()),
        BIOME = Registries.packet(Registries.biome()),
        PAINTING = Registries.packet(Registries.painting()),
        DIMENSION_TYPE = Registries.packet(Registries.dimensionTypes());

    @Override
    public void handle(PlayerConnection connection, int packetId, PacketInData data) {
        final PacketInSelectKnownPack knownPack = new PacketInSelectKnownPack();
        knownPack.read(data);

        connection.sendPackets(
            BIOME,
            PAINTING,
            BANNER,
            TRIM_MATERIAL,
            TRIM_PATTERN,
            DAMAGE_TYPES,
            DIMENSION_TYPE,
            WOLF_VARIANT
        );

        connection.sendPacket(PacketOutFinishConfiguration.INSTANCE);
    }

    @Override
    public int packetId() {
        return InProtocol.CONFIGURATION_SELECT_KNOWN_PACKS;
    }
}
