package com.luxoft.logeek.benchmark.array;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class SubListToArrayBenchmark {

    @Benchmark
    public Integer[] list_typeChecked(Data holder) {
        return holder.list.toArray(new Integer[0]);
    }

    @Benchmark
    public Integer[] subList_typeChecked(Data holder) {
        return holder.list.subList(0, holder.size).toArray(new Integer[0]);
    }

    @State(Scope.Thread)
    public static class Data {
        ArrayList<Integer> list;

        @Param({"0", "10", "100", "1000"})
        int size;

        @Setup
        public void setup() {
            list = IntStream
                    .range(0, size)
                    .boxed()
                    .collect(toCollection(ArrayList::new));
        }
    }
}
