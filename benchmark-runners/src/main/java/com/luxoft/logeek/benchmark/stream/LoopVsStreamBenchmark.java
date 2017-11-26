package com.luxoft.logeek.benchmark.stream;

import com.sun.istack.internal.NotNull;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LoopVsStreamBenchmark {

    @Benchmark
    public Collection<Integer> measureOldMapping(Data data) {
        return oldMap2List(data.collection, Function.identity());
    }

    @Benchmark
    public Collection<Integer> measureStream(Data data) {
        return stream(data.collection, Function.identity());
    }

    @Benchmark
    public Collection<Integer> measureIdeaMapping(Data data) {
        return map2List(data.collection, Function.identity());
    }

    public static class Data {
        @Param({"1", "5", "10", "100", "1000", "10000"})
        private int count;

        private Collection<Integer> collection;

        @Setup
        public void setup() {
            collection = IntStream.range(0, count).boxed().collect(Collectors.toList());
        }
    }


    private static <T, V> List<V> oldMap2List(Collection<? extends T> collection, Function<T, V> mapper) {
        List<V> list = new ArrayList<>(collection.size());
        for (final T t : collection) {
            list.add(mapper.apply(t));
        }
        return list;
    }

    private static <T, V> List<V> stream(Collection<? extends T> collection, Function<T, V> mapper) {
        return collection.stream().map(mapper::apply).collect(Collectors.toList());
    }

    private static <T, V> List<V> map2List(Collection<? extends T> collection, Function<T, V> mapper) {
        if (collection.isEmpty()) return Collections.emptyList();
        ArrayList<V> list = new ArrayList<>(collection.size());
        for (final T t : collection) {
            list.add(mapper.apply(t));
        }
        return list;
    }
}
