package com.luxoft.logeek.benchmark.formatter;

import org.openjdk.jmh.annotations.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Fork(10)
@State(Scope.Benchmark)
@Warmup(iterations = 10)
@Measurement(iterations = 100)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class FormatterBenchmark {

    private static final String pattern = "dd.MM.yyyy";

    private Date date;
    private LocalDate localDate;

    @Setup
    public void setup() {
        date = new Date();
        localDate = LocalDate.now();
    }

    @Benchmark
    public String measureSimpleDateTimeFormatter() {
        return new SimpleDateFormat(pattern).format(date);
    }

    @Benchmark
    public String measureDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(pattern).format(localDate);
    }

    @Benchmark
    public String measureDateTimeFormatterWhenDateConverted() {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return DateTimeFormatter.ofPattern(pattern).format(localDate);
    }
}