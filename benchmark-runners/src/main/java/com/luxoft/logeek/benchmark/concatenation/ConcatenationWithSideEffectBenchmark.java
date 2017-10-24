package com.luxoft.logeek.benchmark.concatenation;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ConcatenationWithSideEffectBenchmark {

    @Benchmark
    public String measureWithSideEffect(Data data) {
        return " " + data.i++ + " ";
    }

    @Benchmark
    public String measureWithoutSideEffect(Data data) {
        int num = data.i++;
        return " " + num + " ";
    }

    @State(Scope.Thread)
    public static class Data {
        int i = 1000;
    }
}
