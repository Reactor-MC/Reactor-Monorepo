package ink.reactor.api.player.connection;

public interface PacketOutbound {
    byte[] write();
    int getId();
}