package com.luxoft.logeek.benchmark.list;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.singletonList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class AddIntoArrayListBenchmark {

    @Benchmark
    public Object addIntoNewList(Data data) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(data.integer);
        integers.add(data.integer);
        return integers;
    }

    @Benchmark
    public Object addIntoNewSizedList(Data data) {
        ArrayList<Integer> integers = new ArrayList<>(2);
        integers.add(data.integer);
        integers.add(data.integer);
        return integers;
    }

    @Benchmark
    public Object addViaConstructor(Data data) {
        return new ArrayList<>(Arrays.asList(data.integer, data.integer));
    }

    @State(Scope.Thread)
    public static class Data {
        Integer integer = 1;
    }
}
