package com.luxoft.logeek.benchmark.comparing;

import com.luxoft.logeek.instanceOf.Comparing;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@Fork(value = 10, jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
@Warmup(iterations = 10)
@Measurement(iterations =20)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ArrayComparingBenchmark {
    private float[] fa1;
    private float[] fa2;

    @Setup
    public void setup() {
        fa1 = new float[]{1f};
        fa2 = new float[]{1f};
    }

    @Benchmark
    public boolean measureEqualWithInstanceOf() {
        return Comparing.areEqual(fa1, fa2);
    }

    @Benchmark
    public boolean measureEqualUsingPolymorphysm() {
        return Comparing._areEqual(fa1, fa2);
    }
}
