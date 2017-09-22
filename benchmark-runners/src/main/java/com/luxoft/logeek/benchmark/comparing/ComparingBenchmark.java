package com.luxoft.logeek.benchmark.comparing;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.instanceOf.Comparing;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

public class ComparingBenchmark extends BenchmarkBase{
    private String s1= "abc";
    private String s2= "abd";

    @Setup
    public void setUp(){
        super.init();
    }

    @Benchmark
    public boolean measureEqualWithInstanceOf() {
        return Comparing.equal(s1, s2);
    }

    @Benchmark
    public boolean measureEqualUsingPolymorphysm() {
        return Comparing._equal(s1, s2);
    }
}
