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

/*
 * Results:
 * r.b.protocol.varint.VarIntWriter.writeFriendlyBuffer       avgt   30      11,945 ±     0,297  ns/op
 * i.r.b.protocol.varint.VarIntWriter.writeVelocity             avgt   30      13,203 ±     1,003  ns/op
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
public class VarIntLengthCalculator {
    private static final int[] VAR_INT_LENGTHS = new int[65];
    private static final int SAMPLE_SIZE = 1000;
    private int[] numbers;
    private int index = 0;

    static {
        for (int i = 0; i <= 32; ++i) {
            VAR_INT_LENGTHS[i] = (int) Math.ceil((31d - (i - 1)) / 7d);
        }
        VAR_INT_LENGTHS[32] = 1; // Special case for the number 0.
    }

    @Setup
    public void setup() {
        final SplittableRandom random = new SplittableRandom();
        numbers = new int[SAMPLE_SIZE];
        for (int i = 0; i < SAMPLE_SIZE; i++) {
            int shift = random.nextInt(0, 28);
            numbers[i] = random.nextInt(-(1 << shift), 1 << shift);
        }
    }

    private int getNumber() {
        int value = numbers[index];
        index = (index + 1) % SAMPLE_SIZE;
        return value;
    }

    @Benchmark
    public int reactorVarInt() {
        int number = getNumber();
        if (number < 0) { // Negative numbers use 5 bytes
            return 5;
        }
        if (number < (1 << 7)) return 1;
        if (number < (1 << 14)) return 2;
        if (number < (1 << 21)) return 3;
        if (number < (1 << 28)) return 4;
        return 5;
    }

    @Benchmark
    public int velocityVarInt() {
        int number = getNumber();
        return VAR_INT_LENGTHS[Integer.numberOfLeadingZeros(number)];
    }
}
