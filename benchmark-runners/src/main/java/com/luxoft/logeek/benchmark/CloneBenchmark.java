package com.luxoft.logeek.benchmark;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.SerializationUtils;
import org.openjdk.jmh.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class CloneBenchmark {

    @Benchmark
    public Value measureSerialization(ValueHolder holder) {
        Value value = holder.value;
        return SerializationUtils.clone(value);
    }

    @Benchmark
    public Value measureJson(ValueHolder holder) {
        String json = holder.mapper.toJson(holder.value);
        return holder.mapper.fromJson(json, Value.class);
    }

    @State(Scope.Thread)
    public static class ValueHolder {

        Value value;
        Gson mapper;

        @Setup
        public void init() {
            value = new Value(BigDecimal.TEN, 10L, "azaza", Boolean.TRUE);
            mapper = new Gson();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Value implements Serializable {
        private BigDecimal bigDecimal;
        private Long aLong;
        private String str;
        private Boolean aBoolean;
    }
}
