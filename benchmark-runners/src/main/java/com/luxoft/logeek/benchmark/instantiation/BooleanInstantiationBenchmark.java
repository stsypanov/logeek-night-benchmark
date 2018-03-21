package com.luxoft.logeek.benchmark.instantiation;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BooleanInstantiationBenchmark {

    @Benchmark
    @SuppressWarnings({"UnnecessaryBoxing", "BooleanConstructorCall"})
    public Boolean constructor(Data data) {
        return new Boolean(data.value);
    }

    @Benchmark
    @SuppressWarnings("UnnecessaryBoxing")
    public Boolean valueOf(Data data) {
        return Boolean.valueOf(data.value);
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"true", "false"})
        boolean value;
    }
}
