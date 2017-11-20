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
    public List<Byte> measureRemoveFromArrayListOneByOne_reverseOrder(Data data) {
        ArrayList<Byte> arrayList = new ArrayList<>(data.initial);
        for (int i = data.to - 1; i >= data.from; i--) {
            arrayList.remove(i);
        }
        return arrayList;
    }

    @Benchmark
    public List<Byte> measureRemoveFromArrayListOneByOne_directOrder(Data data) {
        ArrayList<Byte> arrayList = new ArrayList<>(data.initial);
        for (int i = data.from; i < data.to; i++) {
            arrayList.remove(data.from);
        }
        return arrayList;
    }

    @Benchmark
    public List<Byte> measureRemoveFromArrayListUsingSubList(Data data) {
        ArrayList<Byte> arrayList = new ArrayList<>(data.initial);
        arrayList.subList(data.from, data.to).clear();
        return arrayList;
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"10", "100"})
        private int itemsCount;            //items count

        @Param({"5", "10", "25", "50"})
        private int percentOfRemovedItems; //count of items removed from list

        private ArrayList<Byte> initial;
        private int from;
        private int to;

        @Setup
        public void initIteration() {
            from = itemsCount / 2; //remove from the second half of the list
            to = from + (itemsCount / 100 * percentOfRemovedItems);
            initial = IntStream.range(0, itemsCount).boxed()
                    .map(Integer::byteValue)
                    .collect(toCollection(ArrayList::new));
        }
    }
}