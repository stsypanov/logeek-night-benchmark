package com.luxoft.logeek.benchmark.stream;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms6g", "-Xmx6g"})
public class LoopVsStreamBenchmark {

    @Benchmark
    public Collection<Integer> measureOldMapping(Data data) {
        ArrayList<Integer> list = new ArrayList<>(data.collection.size());
        for (Integer t : data.collection) {
            list.add(Function.<Integer>identity().apply(t));
        }
        return list;
    }

    @Benchmark
    public Collection<Integer> measureStream(Data data) {
        return stream(data.collection, identity());
    }

    @Benchmark
    public Collection<Integer> measureSizedStream(Data data) {
        return sizedStream(data.collection, identity());
    }

    @Benchmark
    public Collection<Integer> measureIdeaMapping(Data data) {
        return map2List(data.collection, identity());
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"1", "10", "100", "1000"})
        private int count;

        private Collection<Integer> collection;

        @Setup
        public void setup() {
            collection = IntStream.range(0, count).boxed().collect(toList());
        }
    }

    private static <T, V> List<V> stream(Collection<? extends T> collection, Function<T, V> mapper) {
        return collection.stream().map(mapper::apply).collect(toList());
    }

    private static <T, V> List<V> sizedStream(Collection<? extends T> collection, Function<T, V> mapper) {
        return collection.stream().map(mapper::apply).collect(toCollection(() -> new ArrayList<>(collection.size())));
    }

    private static <T, V> List<V> map2List(Collection<? extends T> collection, Function<T, V> mapper) {
        if (collection.isEmpty()) return Collections.emptyList();
        ArrayList<V> list = new ArrayList<>(collection.size());
        for (T t : collection) {
            list.add(mapper.apply(t));
        }
        return list;
    }
}
