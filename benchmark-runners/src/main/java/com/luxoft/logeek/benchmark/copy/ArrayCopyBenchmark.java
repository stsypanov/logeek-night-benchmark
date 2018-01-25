package com.luxoft.logeek.benchmark.copy;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ArrayCopyBenchmark {

    @Benchmark
    public byte[] measureClone(Data data) {
        return data.array.clone();
    }

    @Benchmark
    public byte[] measureArraysCopyOf(Data data) {
        return Arrays.copyOf(data.array, data.array.length);
    }

    @Benchmark
    public byte[] measureArraysCopyOfWithForEach(Data data) {
        return copyOfWithForEach(data.array);
    }

    @Benchmark
    public byte[] measureArraysCopyOfWithForI(Data data) {
        return copyOfWithForI(data.array);
    }

    private byte[] copyOfWithForEach(byte[] array) {
        byte[] bytes = new byte[array.length];
        int i = 0;
        for (byte b : array) {
            bytes[i++] = b;
        }
        return bytes;
    }

    private byte[] copyOfWithForI(byte[] array) {
        byte[] copy = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        return copy;
    }

    @State(Scope.Benchmark)
    public static class Data {

        @Param({"1", "5", "10", "20", "50", "100", "1000", "10000"})
        int count;

        byte[] array;

        @Setup
        public void setup() {
            array = new byte[count];
            new Random().nextBytes(array);
        }
    }
}
