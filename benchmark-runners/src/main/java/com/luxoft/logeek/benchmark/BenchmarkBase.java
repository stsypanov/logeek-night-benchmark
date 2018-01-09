package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.base.BaseBenchmark;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BenchmarkBase extends BaseBenchmark {
}
