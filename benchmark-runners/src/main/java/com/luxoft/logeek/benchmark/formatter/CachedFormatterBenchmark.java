package com.luxoft.logeek.benchmark.formatter;

import org.openjdk.jmh.annotations.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CachedFormatterBenchmark {

    @Benchmark
    public String measureSimpleDateTimeFormatter(Data data) {
        return data.simpleFormatter.get().format(data.date);
    }

    @Benchmark
    public String measureDateTimeFormatterWhenDateConverted(Data data) {
        LocalDate localDate = data.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return data.dateTimeFormatter.format(localDate);
    }

    @State(Scope.Thread)
    public static class Data {
        private static final String pattern = "dd.MM.yyyy";

        DateTimeFormatter dateTimeFormatter;
        ThreadLocal<SimpleDateFormat> simpleFormatter;

        Date date;

        @Setup
        public void setup() {
            date = new Date();
            dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            simpleFormatter = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
        }
    }
}
