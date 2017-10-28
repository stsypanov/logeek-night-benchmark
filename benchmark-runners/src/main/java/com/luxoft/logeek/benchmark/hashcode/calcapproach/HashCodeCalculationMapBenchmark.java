package com.luxoft.logeek.benchmark.hashcode.calcapproach;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.hashcode.calcapproach.DataClass;
import com.luxoft.logeek.hashcode.calcapproach.DataClassWithChangedHashCalc;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class HashCodeCalculationMapBenchmark extends BenchmarkBase {
    private static final Function<Long, DataClass> LONG_DATA_CLASS_FUNCTION =
            aLong -> new DataClass(
                    aLong,
                    aLong.toString()
            );
    private static final Function<Long, DataClassWithChangedHashCalc> LONG_DATA_CLASS_WITH_CHANGED_HASH_CALC_FUNCTION =
            aLong -> new DataClassWithChangedHashCalc(
                    aLong,
                    aLong.toString()
            );

    private List<Long> longs;

    @Param({"10", "100", "1000"})
    private int size;

    @Setup
    public void initTrial() {
        super.init();
        longs = random.longs(size).boxed().collect(Collectors.toList());
    }

    @Benchmark
    public Map<DataClass, Long> measureConventionalHashCodeCalculation() {
        return longs.stream()
                .collect(toMap(
                        LONG_DATA_CLASS_FUNCTION,
                        Function.identity()
                ));
    }

    @Benchmark
    public Map<DataClassWithChangedHashCalc, Long> measureHashCodeCalculationWithObjectsHash() {
        return longs.stream()
                .collect(toMap(
                        LONG_DATA_CLASS_WITH_CHANGED_HASH_CALC_FUNCTION,
                        Function.identity()
                ));
    }
}
