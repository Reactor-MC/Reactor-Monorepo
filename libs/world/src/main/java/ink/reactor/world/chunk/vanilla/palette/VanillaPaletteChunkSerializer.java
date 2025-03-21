package ink.reactor.world.chunk.vanilla.palette;

import ink.reactor.nbt.type.NBTGeneral;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.nbt.writer.NBTStreamWriter;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class VanillaPaletteChunkSerializer {

    public static byte[] serialize(VanillaPaletteChunk chunk) {
        final FriendlyBuffer buffer = new FriendlyBuffer(32, 1.25f);

        buffer.writeInt(chunk.getX());
        buffer.writeInt(chunk.getZ());

        buffer.writeBytes(chunk.serializeChunkData());
        buffer.writeBytes(chunk.serializeLightData());

        return buffer.compress();
    }


}
