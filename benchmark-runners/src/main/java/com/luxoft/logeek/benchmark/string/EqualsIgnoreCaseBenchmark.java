package com.luxoft.logeek.benchmark.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Бенчмарк для возможного улучшения метода
 * Boolean.valueOf(String)
 */
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class EqualsIgnoreCaseBenchmark {

    @Benchmark
    public boolean defaultMethod(Data data) {
        return data.str != null && data.str.equalsIgnoreCase("true");
    }

    @Benchmark
    public boolean betterMethod(Data data) {
        return "true".equalsIgnoreCase(data.str);
    }

    @State(Scope.Thread)
    public static class Data {

        @Param({"true", "false", "null"})
        String str;

        @Setup
        public void setup() {
            str = "null".equals(str) ? null : str;
        }

    }

}
