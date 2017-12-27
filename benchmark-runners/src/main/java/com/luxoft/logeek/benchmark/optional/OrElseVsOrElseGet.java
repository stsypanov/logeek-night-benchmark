package com.luxoft.logeek.benchmark.optional;


import org.openjdk.jmh.annotations.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class OrElseVsOrElseGet {

    @Benchmark
    public String measureOrElse() {
        return Optional.<String>empty().orElse(get());
    }

    @Benchmark
    public String measureOrElseGetMethodRef() {
        return Optional.<String>empty().orElseGet(this::get);
    }

    @Benchmark
    public String measureOrElseGetLambda() {
        return  Optional.<String>empty().orElseGet(() -> get());
    }

    private String get() {
        return "azaza";
    }

}
