package ink.reactor.nbt.tags;

import java.util.Arrays;

import ink.reactor.nbt.TagNBT;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;
import ink.reactor.buffer.writer.DynamicSizeBuffer;

public final class IntArrayTag extends TagNBT {

    public final int[] value;

    public IntArrayTag(int[] value, Object key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_INT_ARRAY;
    }

    @Override
    public void write(final DynamicSizeBuffer buffer) {
        buffer.tryResize((value.length + 1) * DataSize.INT);

        final ExpectedSizeBuffer expectedSizeBuffer = buffer.getCurrentBuffer();
        expectedSizeBuffer.writeInt(value.length);

        for (final int integer : value) {
            expectedSizeBuffer.writeInt(integer);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }
}
