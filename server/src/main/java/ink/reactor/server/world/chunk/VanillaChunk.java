package ink.reactor.server.world.chunk;

import ink.reactor.api.world.World;
import ink.reactor.api.world.block.Block;
import ink.reactor.api.world.chunk.Chunk;
import ink.reactor.api.world.chunk.ChunkSection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class VanillaChunk implements Chunk {

    private final int x, z;
    private final World world;
    private VanillaChunkSection[] sections = new VanillaChunkSection[24]; // 24 sections = 16 * 24 = 319 max height

    @Override
    public char getId(int x, int y, int z) {
        final VanillaChunkSection section = sections[y >> 4];
        return (section == null) ? 0 : section.getBlockId(x, y, z);
    }

    @Override
    public ChunkSection getChunkSection(int y) {
        return sections[y >> 4];
    }

    @Override
    public void setBlock(char blockIdData, int x, int y, int z) {
        VanillaChunkSection section = sections[y >> 4];
        if (section == null) {
            section = new VanillaChunkSection();
            sections[y >> 4] = section;
        }
        section.setBlockId(blockIdData, x, y, z);
    }

    @Override
    public char replaceId(final char id, final int x, final int y, final int z) {   
        final ChunkSection section = sections[y >> 4];
        return section == null ? 0 : section.replaceBlockId(id, x, y, z);
    }

    @Override
    public void fillSections(final int start, int end, final Block block) {
        if (start < 0 || start > end) {
            return;
        }
        end = Math.min(end, sections.length); // Prevent ArrayOutOfBounds

        final char blockId = block.getId();
        for (int i = start; i < end; i++) {
            VanillaChunkSection section = sections[i];
            if (section == null) {
                section = new VanillaChunkSection();
                sections[i] = section;
            }
            section.fill(blockId);
        }
    }

    @Override
    public void clear() {
        sections = new VanillaChunkSection[24];
    }

    @Override
    public void unload() {
        for (final VanillaChunkSection section : sections) {
            section.setAllNull();
        }
        sections = null;
    }
}