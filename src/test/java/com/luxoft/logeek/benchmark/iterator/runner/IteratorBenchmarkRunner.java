package com.luxoft.logeek.benchmark.iterator.runner;

import com.luxoft.logeek.benchmark.example.ExampleBenchmark;
import com.luxoft.logeek.benchmark.iterator.IteratorFromStreamBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class IteratorBenchmarkRunner {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(IteratorFromStreamBenchmark.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)//0 makes debugging possible
                .build();

        new Runner(opt).run();
    }
}
