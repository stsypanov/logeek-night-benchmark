package com.luxoft.logeek.benchmark.reflection;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class ArgBenchmark {

    @Benchmark
    public boolean measureNameComparison(Data data) {
        return Object.class.getName().equals(data.type.getName());
    }

    @Benchmark
    public boolean measureIsAssignableFrom(Data data) {
        return Object.class.isAssignableFrom(data.type);
    }

    @State(Scope.Benchmark)
    public static class Data {
        Class<?> type = String.class;
    }
}
