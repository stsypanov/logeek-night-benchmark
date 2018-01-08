package com.luxoft.logeek.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ContainsInStringBenchmark {

    @Benchmark
    public boolean measureContainsString(Data data) {
        return _contains(data.s);
    }

    @Benchmark
    public boolean measureContainsChar(Data data) {
        return _containsChar(data.ch);
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private boolean _contains(String str) {
        return "asfdfdfgsdg".contains(str);
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private boolean _containsChar(char ch) {
        return "asfdfdfgsdg".indexOf(ch) > -1;
    }

    @State(Scope.Thread)
    public static class Data {
        String s = "1";
        char ch = '1';
    }
}
