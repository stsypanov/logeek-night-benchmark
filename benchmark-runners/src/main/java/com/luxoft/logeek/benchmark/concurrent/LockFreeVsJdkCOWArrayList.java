package com.luxoft.logeek.benchmark.concurrent;

import com.luxoft.logeek.collections.LockFreeCopyOnWriteArrayList;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class LockFreeVsJdkCOWArrayList {

    @Benchmark
    public boolean measureIdeaCOWAdd(Data data) {
        return data.ideaCowList.add(data.integer);
    }

    @Benchmark
    public boolean measureJdkCOWAdd(Data data) {
        return data.jdkCowList.add(data.integer);
    }

    @State(Scope.Benchmark)
    public static class Data {
        private Integer integer = 1;
        private CopyOnWriteArrayList<Integer> jdkCowList;
        private LockFreeCopyOnWriteArrayList<Integer> ideaCowList;

        @Setup
        public void setup() {
            jdkCowList = new CopyOnWriteArrayList<>();
            ideaCowList = new LockFreeCopyOnWriteArrayList<>();
        }
    }
}
