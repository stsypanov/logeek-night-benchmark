package com.luxoft.logeek.benchmark.concurrent;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//todo check for OME
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class AddIntoArrayListBenchmark {

    @Benchmark
    public boolean add(Data data) {
        return data.list.add(data.integer);
    }

    @State(Scope.Thread)
    public static class Data {
        private Integer integer = 1;
        private ArrayList<Integer> list;

        @Setup
        public void setup() {
            list = new ArrayList<>();
        }
    }
}
