package com.luxoft.logeek.benchmark.set;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.utils.ContainerUtils;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SetAddAllBenchmark extends BenchmarkBase {

    @Benchmark
    public boolean measureAddAllViaMethod(Data data) {
        return new HashSet<>().addAll(Arrays.asList(data.integers));
    }

    @Benchmark
    public boolean measureAddAllViaCollectionsAddAll(Data data) {
        return Collections.addAll(new HashSet<>(), data.integers);
    }

    @Benchmark
    public Set<Integer> measureAddAllViaUtilsAddAll(Data data) {
        return ContainerUtils.addAll(new HashSet<>(), data.integers);
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"10", "100", "1000"})
        private int count;

        Integer[] integers;

        @Setup
        public void setup() {
            integers = IntStream.range(0, count).boxed().toArray(i -> new Integer[count]);
        }
    }
}
