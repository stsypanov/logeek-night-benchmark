package com.luxoft.logeek.benchmark.formatter;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.Benchmark;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatterBenchmark extends BenchmarkBase {

    @Benchmark
    public String measureSimpleDateTimeFormatter() {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    }

    @Benchmark
    public String measureDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDate.now());
    }

    @Benchmark
    public String measureDateTimeFormatterWhenDateConverted() {
        LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return DateTimeFormatter.ofPattern("dd.MM.yyyy").format(localDate);
    }
}
