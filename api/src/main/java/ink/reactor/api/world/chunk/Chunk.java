package ink.reactor.api.world.chunk;

import ink.reactor.api.world.World;
import ink.reactor.api.world.block.Block;

import java.util.LinkedList;
import java.util.List;

/*
 * A chunk is a cuboid of 16.319.16 blocks.
 * Vanilla y is 319 (81664 blocks). But you can defined custom chunks with other height
 */
public interface Chunk {

    int getX();

    int getZ();

    World getWorld();

    /**
     * Gets a block from this chunk
     * If the coordinates are out of range, the method does nothing and return 0 (Air block).
     *
     * @param x 0-15
     * @param y 0-319
     * @param z 0-15
     * @return block id
     */
    char getId(final int x, final int y, final int z);

    default Block getBlock(final int x, final int y, final int z) {
        return Block.ALL.getArray()[getId(x, y, z)];
    }

    /**
     * Get chunk section by Y cord. Vanilla: 0-15. But if you have a custom chunk, this value varies
     * if the y indice is out of range (higher than {@link Chunk#getSections()} length or lower than 0), the method does nothing and return null.
     *
     * @param y The Y coordinate of the chunk section (range: 0 to {@link Chunk#getSections()} length - 1).
     * @return The chunk section at the specified Y coordinate, or {@code null} if it does not exist.
     */
    ChunkSection getChunkSection(final int y);

    /**
     * Get all chunks sections
     * Some chunk sections may be null
     * Vanilla amount: 24
     *
     * @return array of chunk section
     */
    ChunkSection[] getSections();

    /**
     * Set specific block in the chunk
     * If the coordinates are out of range, the method does nothing.
     *
     * @param block Block to set
     * @param x     0-15
     * @param y     0-319
     * @param z     0-15
     */
    default void setBlock(final Block block, final int x, final int y, final int z) {
        setBlock(block.getId(), x, y, z);
    }

    void setBlock(final char blockIdData, final int x, final int y, final int z);

    /**
     * Set specific block and return the older
     *
     * @param id id of the block to set
     * @param x  0-15
     * @param y  0-319
     * @param z  0-15
     * @return older block id
     * @ignore if the x, y or z indices are out of range.
     */
    char replaceId(final char id, final int x, final int y, final int z);

    default Block replaceBlock(final Block block, final int x, final int y, final int z) {
        return Block.ALL.getArray()[replaceId(block.getId(), x, y, z)];
    }

    /**
     * Fills the specified range of chunk sections with the given block type.
     * If the section indices are out of range or {@code start > end}, the method does nothing.
     *
     * @param start The index of the first section to fill (inclusive).
     * @param end   The index of the last section to fill (inclusive).
     * @param block The block type used to fill the sections.
     */
    void fillSections(final int start, final int end, final Block block);

    /*
     * Set all blocks to AIR and reset light
     */
    void clear();

    /*
     * Unload the chunk with all chunk sections.
     * Make all variables (blocks, skyLight, blockLight, etc) null
     * Used for prevent bad programmed plugins and avoid memory leaks :)
     */
    void unload();

    default boolean isLoaded() {
        return getSections() == null;
    }

    default boolean isEmpty() {
        return getBlocksAmount() == 0;
    }

    /**
     * Get all none air blocks in the chunk
     * @return amount of non air blocks
     */
    default int getBlocksAmount() {
        int amount = 0;
        final ChunkSection[] sections = getSections();
        for (final ChunkSection section : sections) {
            if (section != null) {
                amount += section.getNonEmptyBlocks();
            }
        }
        return amount;
    }

    default int getBlocksAmountByType(final Block block) {
        return getBlocksAmountPerId(block.getId());
    }

    default int getBlocksAmountPerId(final char blockId) {
        int amount = 0;
        final ChunkSection[] sections = getSections();
        for (final ChunkSection section : sections) {
            if (section == null) {
                continue;
            }
            final char[] blocks = section.getBlocks();
            for (final char character : blocks) {
                if (character == blockId) {
                    amount++;
                }
            }
        }
        return amount;
    }

    /**
     * Get all blocks in the chunk with the same type
     *
     * @return list of all blocks with the same type
     */
    default List<ChunkBlock> getBlocksPerType(final Block block) {
        final List<ChunkBlock> blocks = new LinkedList<>();
        final ChunkSection[] sections = getSections();
        for (final ChunkSection section : sections) {
            if (section != null) {
                section.getBlocksPerType(block, blocks);
            }
        }
        return blocks;
    }
}