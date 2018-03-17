package com.luxoft.logeek.benchmark.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class EqualsIgnoreCaseBenchmark {

    @Benchmark
    public boolean defaultMethod(Data data) {
        return (data.str != null) && data.str.equalsIgnoreCase("true");
    }

    @Benchmark
    public boolean betterMethod(Data data) {
        return "true".equalsIgnoreCase(data.str);
    }

    @State(Scope.Thread)
    public static class Data {

        @Param({"true", "false", "random"})
        String str;

    }

}
