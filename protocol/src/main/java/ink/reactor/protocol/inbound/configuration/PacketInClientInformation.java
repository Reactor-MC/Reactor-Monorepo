package ink.reactor.protocol.inbound.configuration;

import ink.reactor.api.player.Player;
import ink.reactor.chat.ChatMode;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.PacketInbound;

public final class PacketInClientInformation implements PacketInbound {

    private final Player player;

    public PacketInClientInformation(Player player) {
        this.player = player;
    }

    @Override
    public void read(final PacketInData data) {
        player.setLocale(data.readString(16));
        player.setViewDistance(data.buffer.readByte());
        player.setChatMode(ChatMode.byId(data.readVarInt()));
        player.setChatColors(data.buffer.readBoolean());
        player.getSkin().fromBitmask(data.buffer.readUnsignedByte());
        player.setMainHand(data.readVarInt());
        player.setTextFiltering(data.buffer.readBoolean());
        player.setServerListings(data.buffer.readBoolean());
    }
}