package ink.reactor.world.chunk.vanilla;

import ink.reactor.nbt.type.NBTGeneral;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.world.chunk.ChunkHeightmap;
import ink.reactor.world.chunk.ChunkSection;
import lombok.Getter;

import java.util.Arrays;

/*
    All heightmaps share the basic structure of encoding the position of the highest "occupied" block
    in each column of blocks within a chunk column.
*/
@Getter
public class GenericHeightmap implements ChunkHeightmap {

    // Contains all highest block in all chunk columns. Contains 256 blocks (16 of x and others 16 of z)
    // If the array were a multidimensional array, it would be indexed array[z][x].
    protected final short[] worldSurface = new short[16 * 16];

    protected final ChunkSection[] sections;
    private final int minY, maxY;

    // The Bits Per Entry value used is calculated as ceil(log2(world height + 1)).
    // This is because the number of possible height values is one more than the world height—ranging from 0
    // (completely blank column; not even bedrock) to world height (highest position is occupied).
    // Note that this means, for example, that a world with height 256 will use a Bits Per Entry of 9.
    private final int bitsPerEntry;

    protected GenericHeightmap(ChunkSection[] sections, int minY, int maxY, int worldHeight) {
        this.sections = sections;
        this.minY = minY;
        this.maxY = maxY;
        this.bitsPerEntry = (int) Math.ceil(Math.log(worldHeight + 1) / Math.log(2));
    }

    public static GenericHeightmap of(ChunkSection[] sections, int minY, int maxY, int worldHeight) {
        final GenericHeightmap heightMap = new GenericHeightmap(sections, minY, maxY, worldHeight);
        if (minY != 0) {
            Arrays.fill(heightMap.worldSurface, (short) minY);
        }
        return heightMap;
    }

    @Override
    public short[] getHeights() {
        return worldSurface;
    }

    @Override
    public short getWorldSurface(final int x, final int z) {
        return worldSurface[z << 4 | x];
    }

    @Override
    public void setSurface(final char blockId, final int x, final int y, final int sectionY, final int z) {
        final int key = z << 4 | x;
        if (blockId == 0) {
            if (worldSurface[key] == y) {
                findAndSetHighestBlock(sectionY, x, z);
            }
            return;
        }
        if (y > worldSurface[key]) {
            worldSurface[key] = (short) y;
        }
    }

    public void findAndSetHighestBlock(final int sectionY, final int x, final int z) {
        for (int i = sectionY; i >= 0; i--) {
            final ChunkSection section = sections[i];
            if (section == null || section.isEmpty()) {
                continue;
            }
            for (int y = 15; y >= 0; y--) {
                final char block = section.getBlockId(x, y, z);
                if (block != 0) {
                    // Transform chunk section y cord to chunk cord, example: 3 to -61
                    worldSurface[z << 4 | x] = (short) (((i * 16) + y) + minY);
                    return;
                }
            }
        }
        worldSurface[z << 4 | x] = (short) minY; // Can't found any block
    }

    public long[] writeHeightmap() {
        final int valuesPerLong = 64 / bitsPerEntry;
        final long[] packedArray = new long[(256 + valuesPerLong - 1) / valuesPerLong];

        int i = 0;
        for (final short height : worldSurface) {
            final int bitIndex = (i % valuesPerLong) * bitsPerEntry;
            final int longIndex = i / valuesPerLong;
            packedArray[longIndex] |= ((long) height & ((1L << bitsPerEntry) - 1)) << bitIndex;
            i++;
        }

        return packedArray;
    }
}