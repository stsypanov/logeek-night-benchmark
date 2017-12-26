package com.luxoft.logeek.benchmark.optional;


import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import org.openjdk.jmh.annotations.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
public class OrElseVsOrElseGet {

    @Benchmark
    public String measureOrElse(Data data) {
        return data.orElse();
    }

    @Benchmark
    public String measureOrElseGet(Data data) {
        return data.orElseGet();
    }


    @State(Scope.Thread)
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static class Data extends ContextAwareBenchmark {
        Optional<String> emptyOptional = Optional.empty();

        public String orElseGet() {
            return emptyOptional.orElse(get());
        }

        public String orElse() {
            return emptyOptional.orElseGet(this::get);
        }

        public String get() {
            return "azaza";
        }
    }

}
