package ink.reactor.server.world.chunk;

import java.util.LinkedList;
import java.util.List;

import ink.reactor.api.world.block.Block;
import ink.reactor.api.world.chunk.ChunkBlock;
import ink.reactor.api.world.chunk.ChunkSection;

import lombok.Getter;
import lombok.NonNull;

@Getter
public final class VanillaChunkSection implements ChunkSection {

    private static final int AIR = 0;

    private char[] blocks = new char[4096];
    private byte[] skyLight, blockLight = new byte[2048];

    private int nonEmptyBlocks = 0;

    @Override
    public char getBlockId(final int x, final int y, final int z) {
        return blocks[y << 8 | z << 4 | x];
    }

    @Override
    public void setBlockId(final char id, final int x, final int y, final int z) {
        final int key = y << 8 | z << 4 | x;
        final char old = blocks[key];
        blocks[key] = id;

        if (id != AIR) {
            if (old == AIR) {
                ++nonEmptyBlocks;
            }
            return;
        }
        if (old != AIR) {
            --nonEmptyBlocks;
        }
    }

    @Override
    public char replaceBlockId(final char id, final int x, final int y, final int z) {
        final char old = blocks[y << 8 | z << 4 | x];
        setBlockId(id, x, y, z);
        return old;
    }

    @Override
    public void fill(final char block) {
        if (block == AIR) {
            blocks = new char[4096];
            nonEmptyBlocks = 0;
            return;
        }
        for (int i = 0; i < 4096; i++) {
            blocks[i] = block;
        }
        nonEmptyBlocks = 4096;
    }

    @Override
    public void clear() {
        nonEmptyBlocks = 0;
        blocks = new char[4096];
        skyLight = new byte[2048];
        blockLight = new byte[2048];
    }

    @Override
    public void setBlocks(@NonNull char[] blocks) {
        this.blocks = blocks;
        this.nonEmptyBlocks = calculateNonEmptyBlocks(blocks);
    }

    @Override
    public void setBlocksUnsafe(@NonNull char[] blocks) {
        this.blocks = blocks;
    }

    @Override
    public List<ChunkBlock> getBlocksPerType(final Block block) {
        final List<ChunkBlock> list = new LinkedList<>();
        getBlocksPerType(block, list);
        return list;
    }

    @Override
    public void getBlocksPerType(final Block block, final List<ChunkBlock> list) {
        final char targetId = block.getId();
        for (int i = 0; i < 4096; i++) {
            if (blocks[i] == targetId) {
                list.add(new ChunkBlock(
                    i & 0xF,
                    (i >> 4) & 0xF,
                    (i >> 8) & 0xFF)
                );
            }
        }
    }

    @Override
    public void setNonEmptyBlocks(int nonEmptyBlocks) {
        this.nonEmptyBlocks = nonEmptyBlocks;
    }

    @Override
    public void recalculateNonEmptyBlocks() {
        this.nonEmptyBlocks = calculateNonEmptyBlocks(blocks);
    }

    private static int calculateNonEmptyBlocks(final char[] blocks) {
        int nonEmptyBlocks = 0;
        for (int i = 0; i < 4096; i++) {
            if (blocks[i] != AIR) {
                ++nonEmptyBlocks;
            }
        }
        return nonEmptyBlocks;
    }

    void setAllNull() {
        blockLight = null;
        skyLight = null;
        blocks = null;
        nonEmptyBlocks = 0;
    }
}