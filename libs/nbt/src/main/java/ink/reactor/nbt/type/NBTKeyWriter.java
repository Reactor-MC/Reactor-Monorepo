package ink.reactor.nbt.type;

import ink.reactor.nbt.writer.NBTStringWriter;
import ink.reactor.util.buffer.writer.WriteBuffer;

final class NBTKeyWriter {

    static void writeKey(final WriteBuffer buffer, final Object key) {
        if (key == null) {
            NBTStringWriter.writeString("", buffer);
            return;
        }
        if (key instanceof byte[] bytes) {
            NBTStringWriter.writeString(bytes, buffer);
            return;
        }
        NBTStringWriter.writeString(key.toString(), buffer);
    }
}