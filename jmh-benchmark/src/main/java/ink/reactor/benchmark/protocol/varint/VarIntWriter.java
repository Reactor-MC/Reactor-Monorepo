package ink.reactor.benchmark.protocol.varint;

import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import ink.reactor.util.buffer.writer.FriendlyBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/*
 * Result: 
 * i.r.b.protocol.varint.VarIntWriter.writeFriendlyBuffer       avgt    4      13,180 ±      0,403  ns/op
 * i.r.b.protocol.varint.VarIntWriter.writeVelocity             avgt   30     153,452 ±     19,562  ns/op
 *
 * Cpu: i3-4160
 * Ram: 16gb ddr3 1600mhz
 * Disk: SSD Gigabyte 120gb
 * OS: Loc-OS 23 (debian 12)
 * JMH version: 1.37
 */
@Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class VarIntWriter {

    private static final int
        AMOUNT_NUMBERS = 1000,
        SAMPLE_SIZE = 10,
        MAX_BUFFER_SIZE = 3_000_000;
    
    private static final float RESIZE_FACTOR = 1.5f;

    private int[] numbers;
    private int index = 0;

    private FriendlyBuffer friendlyBuffer;
    private VelocityBuffer velocityBuffer;

    @Setup
    public void setupBuffers() {
        final SplittableRandom random = new SplittableRandom();
        numbers = new int[AMOUNT_NUMBERS];
        for (int i = 0; i < AMOUNT_NUMBERS; i++) {
            int shift = random.nextInt(0, 28);
            numbers[i] = random.nextInt(-(1 << shift), 1 << shift);
        }

        friendlyBuffer = new FriendlyBuffer(SAMPLE_SIZE, RESIZE_FACTOR);
        velocityBuffer = new VelocityBuffer(Unpooled.directBuffer(SAMPLE_SIZE));
    }

    private int getNumber() {
        int value = numbers[index];
        index = (index + 1) % SAMPLE_SIZE;
        return value;
    }

    @Benchmark
    public void writeFriendlyBuffer() {
        if (friendlyBuffer.getIndex() >= MAX_BUFFER_SIZE) {
            friendlyBuffer = new FriendlyBuffer(SAMPLE_SIZE, RESIZE_FACTOR);
        }
        friendlyBuffer.writeVarInt(getNumber());
    }

    @Benchmark
    public void writeVelocity() {
        if (velocityBuffer.buf.writerIndex() >= MAX_BUFFER_SIZE) {
            velocityBuffer = new VelocityBuffer(Unpooled.directBuffer(SAMPLE_SIZE));
        }
        velocityBuffer.writeVarInt(getNumber());
    }

    private record VelocityBuffer(ByteBuf buf) {
        public void writeVarInt(int value) {
            if ((value & (0xFFFFFFFF << 7)) == 0) {
                buf.writeByte(value);
            } else if ((value & (0xFFFFFFFF << 14)) == 0) {
                final int w = (value & 0x7F | 0x80) << 8 | (value >>> 7);
                buf.writeShort(w);
            } else if ((value & (0xFFFFFFFF << 21)) == 0) {
                final int w = (value & 0x7F | 0x80) << 16 | ((value >>> 7) & 0x7F | 0x80) << 8 | (value >>> 14);
                buf.writeMedium(w);
            } else if ((value & (0xFFFFFFFF << 28)) == 0) {
                final int w = (value & 0x7F | 0x80) << 24 | (((value >>> 7) & 0x7F | 0x80) << 16)
                        | ((value >>> 14) & 0x7F | 0x80) << 8 | (value >>> 21);
                buf.writeInt(w);
            } else {
                final int w = (value & 0x7F | 0x80) << 24 | ((value >>> 7) & 0x7F | 0x80) << 16 | ((value >>> 14) & 0x7F | 0x80) << 8
                        | ((value >>> 21) & 0x7F | 0x80);
                buf.writeInt(w);
                buf.writeByte(value >>> 28);
            }
        }   
    }
}