package ink.reactor.server;

import ink.reactor.nbt.decoder.NBTByteDecoder;
import ink.reactor.nbt.writer.NBTStreamWriter;
import ink.reactor.util.buffer.reader.BufferReader;
import ink.reactor.world.chunk.vanilla.array.VanillaChunk;
import ink.reactor.world.data.WorldType;

import java.io.IOException;
import java.nio.file.Path;

public class test {

    public static void main(String[] args) throws IOException {
        VanillaChunk a = VanillaChunk.of(0,0, WorldType.OVERWORLD);
        a.setBlock(0, 1, 0, 'a');
        a.setBlock(0, 2, 1, 'a');
        a.setBlock(0, 4, 3, 'a');

        NBTStreamWriter.writeFile(Path.of("/home/choco/Desktop/login.html"),NBTByteDecoder.decode(new BufferReader(a.serializeHeightMapNBT()), false));
        System.out.println(NBTByteDecoder.decode(new BufferReader(a.serializeHeightMapNBT()), false));
    }
}
