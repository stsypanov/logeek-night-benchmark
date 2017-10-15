package com.luxoft.logeek.benchmark.misc;

import com.luxoft.logeek.benchmark.BaseBenchmark;
import org.openjdk.jmh.annotations.*;

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

    @Setup
    public void setupTrial() {
        super.init();
        holder = new ValueHolder();
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
