package com.luxoft.logeek.benchmark.misc;

import com.luxoft.logeek.benchmark.BaseBenchmark;
import org.openjdk.jmh.annotations.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Fork(10)
@State(Scope.Benchmark)
@Warmup(iterations = 10)
@Measurement(iterations = 100)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MiscBenchmark extends BaseBenchmark {

    private ValueHolder holder;
    private Object nullable;

    private Integer value;
    private Integer value2;
    private Set<Integer> integerSet;

    @Setup
    public void setupTrial() {
        super.init();
        holder = new ValueHolder();
        value = 42;
        value2 = 43;
        integerSet = new HashSet<>();
        integerSet.add(value);
    }


    @Setup(Level.Iteration)
    public void setup() {
        boolean value = random.nextInt() % 2 == 0;
        holder.setValue(value);
        nullable = value ? null : holder;
    }

    @Benchmark
    public boolean measureBooleanGetter() {
        return holder.getValue();
    }

    @Benchmark
    public boolean measureNullabilityCheck() {
        return nullable == null;
    }

    @Benchmark
    public boolean measureInverseNullabilityCheck() {
        return nullable != null;
    }

    @Benchmark
    public boolean measureContains() {
        return integerSet.contains(value);
    }

    @Benchmark
    public boolean measureDoesNotContain() {
        return integerSet.contains(value2);
    }


    static class ValueHolder {
        private boolean value;

        public boolean getValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }
    }
}
