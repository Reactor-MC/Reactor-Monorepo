package ink.reactor.world.palette;

import ink.reactor.util.buffer.writer.WriteBuffer;
import ink.reactor.world.chunk.vanilla.palette.VanillaPaletteChunkSection;
import ink.reactor.world.palette.type.DataPalette;
import ink.reactor.world.palette.type.GlobalPalette;
import ink.reactor.world.palette.type.SingletonPalette;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class PaletteWriter {

    public static void writeChunkSection(WriteBuffer buf, VanillaPaletteChunkSection section) {
        buf.writeShort(section.getNonEmptyBlocks());
        writeDataPalette(buf, section.getChunkData());
        writeDataPalette(buf, section.getBiomeData());
    }

    public static void writeDataPalette(WriteBuffer buf, DataPalette palette) {
        if (palette.getPalette() instanceof SingletonPalette) {
            buf.writeByte(0); // Bits per entry
            buf.writeVarInt(palette.getPalette().idToState(0));
            buf.writeVarInt(0); // Data length
            return;
        }

        buf.writeByte(palette.getStorage().getBitsPerEntry());

        if (!(palette.getPalette() instanceof GlobalPalette)) {
            int paletteLength = palette.getPalette().size();
            buf.writeVarInt(paletteLength);
            for (int i = 0; i < paletteLength; i++) {
                buf.writeVarInt(palette.getPalette().idToState(i));
            }
        }

        long[] data = palette.getStorage().getData();
        for (final long longData : data) {
            buf.writeLong(longData);
        }
    }
}
