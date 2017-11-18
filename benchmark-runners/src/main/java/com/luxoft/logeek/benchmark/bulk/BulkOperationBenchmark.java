package com.luxoft.logeek.benchmark.bulk;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("ALL")
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BulkOperationBenchmark extends BenchmarkBase {

    @Benchmark
    public List<Long> measureAddOneByOne(Data data) {
        List<Long> newList = new ArrayList<>();
        for (Long item : data.items) {
            newList.add(item);
        }
        return newList;
    }

    @Benchmark
    public List<Long> measureAddAll_ArrayList(Data data) {
        List<Long> newList = new ArrayList<>();
        newList.addAll(data.items);
        return newList;
    }

    @Benchmark
    public List<Long> measureAddAllViaConstructorArg_ArrayList(Data data) {
        List<Long> newList = new ArrayList<>(data.items);
        return newList;
    }

    @Benchmark
    public Set<Long> measureAddOneByOne_HashSet(Data data) {
        Set<Long> newSet = new HashSet<>();
        for (Long item : data.items) {
            newSet.add(item);
        }
        return newSet;
    }

    @Benchmark
    public Set<Long> measureAddAll_HashSet(Data data) {
        Set<Long> newSet = new HashSet<>();
        newSet.addAll(data.items);
        return newSet;
    }

    @Benchmark
    public Set<Long> measureAddAllViaConstructorArg_HashSet(Data data) {
        Set<Long> newSet = new HashSet<>(data.items);
        return newSet;
    }

    @Benchmark
    public Set<Long> measureAddAllVarArgViaAsList_HashSet(Data data) {
        Set<Long> set = new HashSet<>(Arrays.asList(data.array));
        return set;
    }

    @Benchmark
    public Set<Long> measureAddAllVarArgViaCollectionsAddAll_HashSet(Data data) {
        Set<Long> set = new HashSet<>(data.array.length);
        Collections.addAll(set, data.array);
        return set;
    }

    @Benchmark
    public List<Long> measureAddAllVarArgViaAsList_ArrayList(Data data) {
        List<Long> set = new ArrayList<>(Arrays.asList(data.array));
        return set;
    }

    @Benchmark
    public List<Long> measureAddAllVarArgViaCollectionsAddAll_ArrayList(Data data) {
        List<Long> set = new ArrayList<>(data.array.length);
        Collections.addAll(set, data.array);
        return set;
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"10", "100", "1000"})
        private int count;

        private ThreadLocalRandom random;
        List<Long> items;
        Long[] array;

        @Setup
        public void init() {
            random = ThreadLocalRandom.current();
        }

        @Setup(Level.Iteration)
        public void initIteration() {
            items = random.longs(count).boxed().collect(toList());
            array = items.toArray(new Long[0]);
        }
    }
}
