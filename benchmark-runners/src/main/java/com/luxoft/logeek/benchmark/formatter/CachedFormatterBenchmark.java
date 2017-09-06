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

public class CachedFormatterBenchmark extends BenchmarkBase {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final ThreadLocal<SimpleDateFormat> simpleDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy"));
    
    private Date date;
    private LocalDate localDate;
    
    @Setup(Level.Iteration)
    public void setup() {
        date = new Date();
        localDate = LocalDate.now();
    }

    @Benchmark
    public String measureSimpleDateTimeFormatter() {
        return simpleDateFormat.get().format(date);
    }

    @Benchmark
    public String measureDateTimeFormatter() {
        return dateTimeFormatter.format(localDate);
    }

    @Benchmark
    public String measureDateTimeFormatterWhenDateConverted() {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return dateTimeFormatter.format(localDate);
    }
}
