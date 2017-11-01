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
    public String measureSimpleDateTimeFormat(Data data) {
        return data.simpleFormatter.get().format(data.date);
    }

    @Benchmark
    public String measureDateTimeFormatter(Data data) {
        return data.dateTimeFormatter.format(data.localDate);
    }

    @Benchmark
    public String measureDateTimeFormatterWhenDateConverted(Data data) {
        LocalDate localDate = data.date.toInstant().atZone(data.zoneId).toLocalDate();
        return data.dateTimeFormatter.format(localDate);
    }

    @State(Scope.Thread)
    public static class Data {
        private static final String pattern = "dd.MM.yyyy";

        private DateTimeFormatter dateTimeFormatter;
        private ThreadLocal<SimpleDateFormat> simpleFormatter;

        private Date date;
        private LocalDate localDate;
        private ZoneId zoneId;

        @Setup
        public void setup() {
            date = new Date();
            localDate = LocalDate.now();

            zoneId = ZoneId.systemDefault();
            dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            simpleFormatter = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
        }
    }
}
