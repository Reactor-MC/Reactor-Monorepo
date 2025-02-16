package ink.reactor.benchmark.api.nbt;

import ink.reactor.nbt.tags.StringTag;
import ink.reactor.nbt.type.NBTFastAdd;
import ink.reactor.nbt.type.NBTGeneral;
import org.openjdk.jmh.annotations.*;

import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

/*
 * Results:
 * 
 * NBTWrite.addFast          avgt   30   6,406 ± 0,165  ns/op
 * NBTWrite.addGeneral       avgt   30  10,568 ± 1,283  ns/op
 * NBTWrite.addOrSetFast     avgt   30  40,675 ± 1,921  ns/op
 * NBTWrite.addOrSetGeneral  avgt   30  10,081 ± 0,593  ns/op
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
public class NBTWrite {

    private NBTFastAdd fastWrite;
    private NBTGeneral general;

    private String[] randomKeys;

    private int fastIndex, generalIndex;

    @Setup
    public void setup() {
        fastWrite = new NBTFastAdd();
        general = new NBTGeneral();

        randomKeys = new String[30];
        final SplittableRandom random = new SplittableRandom();
        for (int i = 0; i < 30; i++) {
            final int keyLength = random.nextInt(1, 16);
            final byte[] bytes = new byte[keyLength];
            for (int b = 0; b < keyLength; b++) {
                bytes[b] = (byte)random.nextInt(33, 126);
            }
            randomKeys[i] = new String(bytes);
        }
    }

    @Benchmark
    public void addFast() {
        if (++fastIndex == randomKeys.length) {
            fastIndex = 0;
        }
        final String key = randomKeys[fastIndex];
        fastWrite.add(new StringTag("1234", key));
    }

    @Benchmark
    public void addOrSetFast() {
        if (++fastIndex == randomKeys.length) {
            fastIndex = 0;
        }
        final String key = randomKeys[fastIndex];
        fastWrite.addOrSet(new StringTag("1234", key));
    }

    @Benchmark
    public void addGeneral() {
        if (++generalIndex == randomKeys.length) {
            generalIndex = 0;
        }
        final String key = randomKeys[generalIndex];
        general.add(new StringTag("1234", key));
    }

    @Benchmark
    public void addOrSetGeneral() {
        if (++generalIndex == randomKeys.length) {
            generalIndex = 0;
        }
        final String key = randomKeys[generalIndex];
        general.addOrSet(new StringTag("1234", key));
    }
}