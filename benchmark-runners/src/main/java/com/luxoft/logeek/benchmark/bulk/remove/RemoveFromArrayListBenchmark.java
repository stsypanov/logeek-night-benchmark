package com.luxoft.logeek.benchmark.bulk.remove;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class RemoveFromArrayListBenchmark {

    @Benchmark
    public List<Byte> oneByOne_reverseOrder(Data data) {
        ArrayList<Byte> arrayList = new ArrayList<>(data.initial);
        for (int i = data.to - 1; i >= data.from; i--) {
            arrayList.remove(i);
        }
        return arrayList;
    }

    @Benchmark
    public List<Byte> oneByOne_directOrder(Data data) {
        ArrayList<Byte> arrayList = new ArrayList<>(data.initial);
        for (int i = data.from; i < data.to; i++) {
            arrayList.remove(data.from);
        }
        return arrayList;
    }

    @Benchmark
    public List<Byte> subList(Data data) {
        ArrayList<Byte> arrayList = new ArrayList<>(data.initial);
        arrayList.subList(data.from, data.to).clear();
        return arrayList;
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"10", "100", "1000"})
        private int size;

        @Param({"10", "25", "50"})
        private int percentRemoved; //percent of items removed from list

        private ArrayList<Byte> initial;
        private int from;
        private int to;

        @Setup
        public void initIteration() {
            from = size / 2; //remove from the second half of the list
            to = from + (size / 100 * percentRemoved);
            initial = IntStream.range(0, size).boxed()
                    .map(Integer::byteValue)
                    .collect(toCollection(ArrayList::new));
        }
    }
}