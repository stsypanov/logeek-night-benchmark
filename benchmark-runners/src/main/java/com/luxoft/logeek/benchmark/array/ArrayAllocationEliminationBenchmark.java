package com.luxoft.logeek.benchmark.array;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ArrayAllocationEliminationBenchmark {

    private int length = 10;
    private Method equals; // 1 param
    private Method toString; // no params

    @Setup
    public void setUp() throws Exception {
        equals = getClass().getMethod("equals", Object.class);
        toString = getClass().getMethod("toString");
    }

    @Benchmark
    public int baseline() {
        return new int[length].length;
    }

    @Benchmark
    public int baselineClone() {
        return new int[length].clone().length;
    }

    @Benchmark
    public int cloneArrayFromEqualsMethod() {
        return equals.getParameterTypes().length;
    }

    @Benchmark
    public int cloneArrayFromToStringMethod() {
        return toString.getParameterTypes().length;
    }
}
