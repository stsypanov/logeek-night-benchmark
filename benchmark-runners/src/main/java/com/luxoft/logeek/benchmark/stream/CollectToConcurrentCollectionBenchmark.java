package com.luxoft.logeek.benchmark.stream;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.luxoft.logeek.benchmark.stream.CollectToConcurrentCollectionBenchmark.Data.CONC_LINKED_DEQUE;
import static com.luxoft.logeek.benchmark.stream.CollectToConcurrentCollectionBenchmark.Data.COWLIST;
import static com.luxoft.logeek.benchmark.stream.CollectToConcurrentCollectionBenchmark.Data.VECTOR;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static org.openjdk.jmh.infra.Blackhole.consumeCPU;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class CollectToConcurrentCollectionBenchmark {

//    @Benchmark
//    public Collection<Integer> collect(Data data) {
//        ArrayList<Integer> list = data.list;
//        Collector<Integer, ?, Collection<Integer>> collector = data.freshCollector();
//        return list.stream().peek(integer -> consumeCPU(1)).collect(collector);
//    }

    @Benchmark
    public Collection<Integer> parallelCollect(Data data) {
        ArrayList<Integer> list = data.list;
        Collector<Integer, ?, Collection<Integer>> collector = data.freshCollector();
        return list.stream().parallel().peek(integer -> consumeCPU(1)).collect(collector);
    }

    @Benchmark
    public Collection<Integer> collectIntoTempList(Data data) {
        ArrayList<Integer> list = data.list;
        List<Integer> tempList = list.stream().parallel().peek(integer -> consumeCPU(1)).collect(toList());
        String collector = data.collector;
        switch (collector) {
            case VECTOR: return new Vector<>(tempList);
            case COWLIST: return new CopyOnWriteArrayList<>(tempList);
            case CONC_LINKED_DEQUE: return new ConcurrentLinkedQueue<>(tempList);
            default: throw new IllegalArgumentException(collector);
        }
    }

    @State(Scope.Thread)
    public static class Data {
        static final String VECTOR = "Vector";
        static final String COWLIST = "COWList";
        static final String CONC_LINKED_DEQUE = "ConqLinkedDeque";

        ArrayList<Integer> list;

        @Param({"10", "100", "1000", "10000"})
        int size;

        @Param({VECTOR, COWLIST, CONC_LINKED_DEQUE})
        String collector;

        @Setup
        public void setup() {
            list = IntStream
                    .range(0, size)
                    .boxed()
                    .collect(toCollection(ArrayList::new));
        }

        Collector<Integer, ?, Collection<Integer>> freshCollector() {
            switch (collector) {
                case VECTOR:
                    return Collectors.toCollection(Vector::new);
                case COWLIST:
                    return Collectors.toCollection(CopyOnWriteArrayList::new);
                case CONC_LINKED_DEQUE:
                    return Collectors.toCollection(ConcurrentLinkedDeque::new);
                default:
                    throw new IllegalArgumentException(collector);
            }
        }
    }
}
