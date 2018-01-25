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
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class CachedFormatterBenchmark {

    @Benchmark
    public String simpleDateTimeFormat(Data data) {
        return data.simpleFormatter.get().format(data.date);
    }

    @Benchmark
    public String dateTimeFormatter(Data data) {
        return data.dateTimeFormatter.format(data.localDate);
    }

    @Benchmark
    public String dateTimeFormatter_dateConverted(Data data) {
        LocalDate localDate = toLocalDate(data.date);
        return data.dateTimeFormatter.format(localDate);
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @State(Scope.Thread)
    public static class Data {
        private static final String pattern = "dd.MM.yyyy";

        private DateTimeFormatter dateTimeFormatter;
        private ThreadLocal<SimpleDateFormat> simpleFormatter;

        private Date date;
        private LocalDate localDate;

        @Setup
        public void setup() {
            date = new Date();
            localDate = LocalDate.now();

            dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            simpleFormatter = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
        }
    }
}
