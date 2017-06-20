package com.luxoft.logeek.benchmark.copy.runner;

import com.luxoft.logeek.benchmark.copy.CollectToHashSetBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class CollectToHashSetBenchmarkRunner {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CollectToHashSetBenchmark.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(100)
                .forks(1)//0 makes debugging possible
                .build();

        new Runner(opt).run();
    }
}
