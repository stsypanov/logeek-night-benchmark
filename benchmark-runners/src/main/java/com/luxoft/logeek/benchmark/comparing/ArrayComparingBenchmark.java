package com.luxoft.logeek.benchmark.comparing;

import com.luxoft.logeek.instanceOf.Comparing;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@Warmup(batchSize = 100, iterations = 1000)
@Measurement(batchSize = 100, iterations = 1000)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ArrayComparingBenchmark {
    private float[] fa1 = {1f};
    private float[] fa2 = {1f};

    @Benchmark
    public boolean measureEqualWithInstanceOf() {
        return Comparing.areEqual(fa1, fa2);
    }

    @Benchmark
    public boolean measureEqualUsingPolymorphysm() {
        return Comparing._areEqual(fa1, fa2);
    }
}
