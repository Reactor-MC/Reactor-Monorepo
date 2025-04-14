package ink.reactor.nbt.tags;

import java.util.Arrays;

import ink.reactor.nbt.TagNBT;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;
import ink.reactor.buffer.writer.DynamicSizeBuffer;

public final class LongArrayTag extends TagNBT {

    public final long[] value;

    public LongArrayTag(long[] value, Object key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_LONG_ARRAY;
    }

    @Override
    public void write(final DynamicSizeBuffer buffer) {
        buffer.tryResize((value.length * DataSize.LONG) + DataSize.INT);

        final ExpectedSizeBuffer expectedSizeBuffer = buffer.getCurrentBuffer();
        expectedSizeBuffer.writeInt(value.length);

        for (final long longValue : value) {
            expectedSizeBuffer.writeLong(longValue);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }
}
