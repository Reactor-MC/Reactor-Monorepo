package ink.reactor.protocol.handler;

import ink.reactor.protocol.decoder.PacketDecoded;
import ink.reactor.protocol.handler.storage.HandlerStorageType;
import ink.reactor.protocol.handler.storage.ArrayHandlerStorage;
import ink.reactor.protocol.handler.storage.SingleHandlerStorage;
import ink.reactor.protocol.PlayerConnectionImpl;

public final class PacketHandlerStorage {

    private final HandlerStorageType[] packetsHandlers;

    public PacketHandlerStorage(int amountPackets) {
        this.packetsHandlers = new HandlerStorageType[amountPackets];
    }

    public void execute(final PlayerConnectionImpl connection, final PacketDecoded packetDecoded) {
        final int packetId = packetDecoded.id();
        if (packetId < 0 || packetId >= packetsHandlers.length || packetsHandlers[packetId] == null) {
            return;
        }
        packetsHandlers[packetId].handle(connection, packetId, packetDecoded.data());
    }

    public void add(final PacketHandler handler) {
        final int id = handler.packetId();
        final HandlerStorageType storageType = packetsHandlers[id];
        if (storageType == null) {
            packetsHandlers[id] = new SingleHandlerStorage(handler);
            return;
        }
        if (storageType instanceof SingleHandlerStorage singleHandlerStorage) {
            packetsHandlers[id] = new ArrayHandlerStorage(singleHandlerStorage.getHandler(), handler);
            return;
        }
        if (storageType instanceof ArrayHandlerStorage arrayHandlerStorage) {
            arrayHandlerStorage.add(handler);
        }
    }
}
