package com.luxoft.logeek.benchmark.concurrent;

import com.intellij.util.containers.LockFreeCopyOnWriteArrayList;
import org.openjdk.jmh.annotations.*;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class LockFreeVsJdkCOWArrayListModificationBenchmark {

    @Benchmark
    @Group("ideaCow")
    @GroupThreads(2)
    public boolean measureIdeaCOWAdd(Data data) {
        return data.ideaCowList.add(data.integer);
    }

    @Benchmark
    @Group("ideaCow")
    @GroupThreads(4)
    public Integer measureIdeaCOWRead(Data data) {
        LockFreeCopyOnWriteArrayList<Integer> ideaCowList = data.ideaCowList;
        Iterator<Integer> iterator = ideaCowList.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    @Benchmark
    @Group("jdkCow")
    @GroupThreads(2)
    public boolean measureJdkCOWAdd(Data data) {
        return data.jdkCowList.add(data.integer);
    }

    @Benchmark
    @Group("jdkCow")
    @GroupThreads(4)
    public Integer measureJdkCOWRead(Data data) {
        CopyOnWriteArrayList<Integer> ideaCowList = data.jdkCowList;
        Iterator<Integer> iterator = ideaCowList.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    @State(Scope.Group)
    public static class Data {
        private Integer integer = 1;
        private CopyOnWriteArrayList<Integer> jdkCowList;
        private LockFreeCopyOnWriteArrayList<Integer> ideaCowList;

        @Setup(Level.Iteration)
        public void setup() {
            jdkCowList = new CopyOnWriteArrayList<>();
            ideaCowList = new LockFreeCopyOnWriteArrayList<>();
        }
    }
}
