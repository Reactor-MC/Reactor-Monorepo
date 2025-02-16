package ink.reactor.benchmark.api.nbt;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import ink.reactor.nbt.tags.StringTag;
import ink.reactor.nbt.type.NBTFastAdd;
import ink.reactor.nbt.type.NBTGeneral;

import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

/*
 * Results (Different keys = 30):
 * 
 * NBTGet.getFastWrite       avgt   30   6,698 ± 0,691  ns/op
 * NBTGet.getGeneral         avgt   30   5,417 ± 0,204  ns/op
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
public class NBTGet {

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
            final String key = new String(bytes);
            randomKeys[i] = key;

            final StringTag tag = new StringTag("value", key);
            fastWrite.add(tag);
            general.add(tag);
        }
    }

    @Benchmark
    public void getFastWrite(final Blackhole blackhole) {
        if (++fastIndex == randomKeys.length) {
            fastIndex = 0;
        }
        final String key = randomKeys[fastIndex];
        blackhole.consume(fastWrite.get(key));
    }

    @Benchmark
    public void getGeneral(final Blackhole blackhole) {
        if (++generalIndex == randomKeys.length) {
            generalIndex = 0;
        }
        final String key = randomKeys[generalIndex];
        blackhole.consume(general.get(key));
    }
}