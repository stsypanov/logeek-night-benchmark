package com.luxoft.logeek.benchmark.bulk;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
@SuppressWarnings({"UseBulkOperation", "UnnecessaryLocalVariable"})
public class BulkOperationBenchmark extends BenchmarkBase {

    @Benchmark
    public Collection<Long> measureAddOneByOne(Data data) {
        Collection<Long> newCollection = data.freshCollection();
        for (Long item : data.items) {
            newCollection.add(item);
        }
        return newCollection;
    }

    @Benchmark
    public Collection<Long> measureAddAll(Data data) {
        Collection<Long> newCollection = data.freshCollection();
        newCollection.addAll(data.items);
        return newCollection;
    }

    @Benchmark
    public Collection<Long> measureAddAllViaConstructorArg(Data data) {
        Collection<Long> newCollection = data.freshCollection(data.items);
        return newCollection;
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"1", "2"})
        private int collectionType;

        @Param({"10", "100", "1000"})
        private int count;

        private ThreadLocalRandom random;
        private List<Long> items;

        @Setup
        public void init() {
            random = ThreadLocalRandom.current();
        }

        @Setup(Level.Iteration)
        public void initIteration() {
            items = random.longs(count).boxed().collect(toList());
        }

        Collection<Long> freshCollection() {
            return 1 == collectionType
                    ? new ArrayList<>()
                    : new HashSet<>();
        }

        Collection<Long> freshCollection(Collection<Long> items) {
            return 1 == collectionType
                    ? new ArrayList<>(items)
                    : new HashSet<>(items);
        }
    }
}
