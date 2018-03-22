package com.luxoft.logeek.benchmark.concurrent;

import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class CowSubListRemoveBenchmark {

    @Benchmark
    public boolean removeFromJdkCowSubList(Data data) {
        return data.subListFromJdkCowList.remove(data.integer);
    }

    @Benchmark
    public boolean removeFromPatchedCowSubList(Data data) {
        return data.subListFromPatchedJdkCowList.remove(data.integer);
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"10", "100", "1000"})
        private int size;

        private List<Integer> subListFromJdkCowList;
        private List<Integer> subListFromPatchedJdkCowList;

        private Integer integer = -1; //элемент отсутствует в списке, так что мы пройдём по нему до конца

        @Setup
        public void setup() {
            Integer[] integers = IntStream.range(0, size).boxed().toArray(Integer[]::new);

            subListFromJdkCowList = new CopyOnWriteArrayList<>(integers).subList(0, size / 2);
            subListFromPatchedJdkCowList = new com.jdk.CopyOnWriteArrayList<>(integers).subList(0, size / 2); //пропатченая реализация, см. строку 1191
        }
    }
}
