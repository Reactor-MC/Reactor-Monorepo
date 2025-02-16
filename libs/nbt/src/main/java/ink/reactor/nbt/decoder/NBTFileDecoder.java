package ink.reactor.nbt.decoder;

import java.io.File;
import java.io.IOException;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.compression.CompressionUtils;
import ink.reactor.util.buffer.reader.BufferReader;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class NBTFileDecoder {
    
    public static NBT decode(final File file) throws IOException {
        final byte[] uncompressedBytes = CompressionUtils.getUncompressedBytes(file);
        if (uncompressedBytes == null) {
            return null;
        }
        return NBTByteDecoder.decode(new BufferReader(uncompressedBytes), true);
    }
}