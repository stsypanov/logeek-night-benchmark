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

    @Benchmark
    public boolean bestMethod(Data data) {
        String s = data.str;
        return s != null
                && s.length() == 4
                && (s.charAt(0) | 0x20) == 't'
                && (s.charAt(1) | 0x20) == 'r'
                && (s.charAt(2) | 0x20) == 'u'
                && (s.charAt(3) | 0x20) == 'e';
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
