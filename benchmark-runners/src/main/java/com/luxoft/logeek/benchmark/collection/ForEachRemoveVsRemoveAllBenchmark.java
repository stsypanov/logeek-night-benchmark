package com.luxoft.logeek.benchmark.collection;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class ForEachRemoveVsRemoveAllBenchmark {

    @Benchmark
    public Object forEachRemove(Data data) {
        Collection<Integer> collection = data.freshCollection();
        List<Integer> toBeRemoved = data.toBeRemoved;
        toBeRemoved.forEach(collection::remove);
        return collection;
    }

    @Benchmark
    public Object removeAll(Data data) {
        Collection<Integer> collection = data.freshCollection();
        List<Integer> toBeRemoved = data.toBeRemoved;
        collection.removeAll(toBeRemoved);
        return collection;
    }

    @State(Scope.Thread)
    public static class Data {
        private static final String ARRAY_LIST = "ArrayList";
        private static final String ARRAY_DEQUE = "ArrayDeque";
        private static final String HASH_SET = "HashSet";
        private static final String COPY_ON_WRITE_ARRAY_LIST = "CopyOnWriteArrayList";
        private static final String CONCURRENT_LINKED_DEQUE = "ConcurrentLinkedDeque";

        Integer array[];

        List<Integer> toBeRemoved;

        @Param({ARRAY_LIST, HASH_SET})
        String collection;

        @Param({"10", "100", "1000"})
        int size;

        @Setup
        public void setup() {
            array = IntStream
                    .range(0, size)
                    .boxed()
                    .toArray(Integer[]::new);

            int half = array.length / 2;
            toBeRemoved = Arrays.asList(Arrays.copyOf(this.array, half));
            Collections.shuffle(toBeRemoved);
        }

        Collection<Integer> freshCollection() {
            switch (collection) {
                case ARRAY_LIST:
                    return new ArrayList<>(asList(array));
                case HASH_SET:
                    return new HashSet<>(asList(array));
                case ARRAY_DEQUE:
                    return new ArrayDeque<>(asList(array));
                case COPY_ON_WRITE_ARRAY_LIST:
                    return new CopyOnWriteArrayList<>(asList(array));
                case CONCURRENT_LINKED_DEQUE:
                    return new ConcurrentLinkedQueue<>(asList(array));
                default:
                    throw new IllegalArgumentException(collection);
            }
        }
    }
}
