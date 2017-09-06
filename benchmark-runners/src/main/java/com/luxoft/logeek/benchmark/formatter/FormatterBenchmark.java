package com.luxoft.logeek.benchmark.formatter;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatterBenchmark extends BenchmarkBase {

    private Date date;
    private LocalDate localDate;

    @Setup(Level.Iteration)
    public void setup() {
        date = new Date();
        localDate = LocalDate.now();
    }

    @Benchmark
	public String measureSimpleDateTimeFormatter() {
        return new SimpleDateFormat("dd").format(date);
    }

    @Benchmark
    public String measureDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd").format(localDate);
    }

    @Benchmark
    public String measureDateTimeFormatterWhenDateConverted() {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return DateTimeFormatter.ofPattern("dd").format(localDate);
    }
}