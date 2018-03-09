package com.luxoft.logeek.benchmark.array;

import com.luxoft.logeek.utils.ArrayUtilRt;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class IndexOfBenchmark {

    @Benchmark
    public int effectiveIndexOf(ArrayHolder holder) {
        return ArrayUtilRt.indexOf(holder.array, holder.integer);
    }

    @Benchmark
    public int ineffectiveIndexOf(ArrayHolder holder) {
        return ArrayUtilRt._indexOf(holder.array, holder.integer);
    }

    @State(Scope.Thread)
    public static class ArrayHolder {
        Integer[] array;

        Integer integer;

        @Param({"10", "100", "1000"})
        int length;

        @Param({"true", "false"})
        boolean nullable;

        @Setup
        public void setup() {
            array = IntStream
                    .range(0, length)
                    .boxed()
                    .toArray(Integer[]::new);

            integer = nullable ? null : array[length - 1]; //null or the last one
        }
    }
}
