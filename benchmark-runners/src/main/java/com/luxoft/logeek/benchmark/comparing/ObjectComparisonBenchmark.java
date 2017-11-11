package com.luxoft.logeek.benchmark.comparing;

import com.luxoft.logeek.instanceOf.Comparing;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ObjectComparisonBenchmark {
    private String s1 = "abc";
    private String s2 = "abd";

    @Benchmark
    public boolean measureEqualWithInstanceOf() {
        return Comparing.equal(s1, s2);
    }

    @Benchmark
    public boolean measureEqualUsingPolymorphysm() {
        return Comparing._equal(s1, s2);
    }
}
