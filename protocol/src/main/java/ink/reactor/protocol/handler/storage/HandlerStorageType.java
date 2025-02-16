package ink.reactor.protocol.handler.storage;

import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.PlayerConnectionImpl;

public interface HandlerStorageType {
    void handle(final PlayerConnectionImpl connection, final int packetId, final PacketInData data);    
}