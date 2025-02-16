package ink.reactor.protocol.decoder;

import io.netty.buffer.ByteBuf;

final class PacketDecoder {
    public static int readVarIntSafely(final ByteBuf buf) {
        int numRead = 0;
        int result = 0;

        while (numRead < 5) {
            if (!buf.isReadable()) {
                return -1;
            }
    
            byte read = buf.readByte();
            int value = (read & 0x7F);
            result |= (value << (7 * numRead));
    
            numRead++;
    
            if ((read & 0x80) != 0x80) {
                return result;
            }
        }
        throw new RuntimeException("VarInt is too big");
    }
}
