package ink.reactor.api.world.chunk;

import ink.reactor.api.world.block.Block;
import lombok.NonNull;

import java.util.List;

public interface ChunkSection {

    default Block getBlock(final int x, final int y, final int z) {
        return Block.ALL.getArray()[getBlockId(x, y, z)];
    }

    char getBlockId(final int x, final int y, final int z);

    default void setBlock(final Block block, final int x, final int y, final int z) {
        setBlockId(block.getId(), x, y, z);
    }

    void setBlockId(final char id, final int x, final int y, final int z);

    char replaceBlockId(final char id, final int x, final int y, final int z);

    default void fill(final Block block) {
        fill(block.getId());
    }

    void fill(final char block);

    char[] getBlocks();

    void clear();

    // Set blocks and execute recalculateNonEmptyBlocks
    void setBlocks(@NonNull char[] blocks);

    // Set blocks but not recalculate empty blocks
    // After execute this method, execute setNonEmptyBlocks
    void setBlocksUnsafe(@NonNull char[] blocks);

    int getNonEmptyBlocks();

    void setNonEmptyBlocks(int nonEmptyBlocks);

    void recalculateNonEmptyBlocks();

    /**
     * Get all blocks in the chunk with the same type
     *
     * @return list of all blocks with the same type
     */
    List<ChunkBlock> getBlocksPerType(final Block block);

    void getBlocksPerType(final Block block, final List<ChunkBlock> list);

    byte[] getSkyLight();

    byte[] getBlockLight();

    default boolean isLoaded() {
        return getBlocks() == null;
    }
}