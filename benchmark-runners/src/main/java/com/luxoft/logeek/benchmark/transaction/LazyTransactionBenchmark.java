package com.luxoft.logeek.benchmark.transaction;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.dto.CashFlowDto;
import com.luxoft.logeek.service.ltav.EagerLtavService;
import com.luxoft.logeek.service.ltav.LazyLtavService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class LazyTransactionBenchmark {

    @Benchmark
    public void measureLazyTransaction(Blackhole bh, Data data) {
        try {
            bh.consume(data.lazyService.createCashFlow(data.dto));
        } catch (Exception e) {
            bh.consume(e);
        }
    }

    @Benchmark
    public void measureEagerTransaction(Blackhole bh, Data data) {
        try {
            bh.consume(data.eagerService.createCashFlow(data.dto));
        } catch (Exception e) {
            bh.consume(e);
        }
    }

    @State(Scope.Thread)
    public static class Data extends ContextAwareBenchmark {
        private CashFlowDto dto;
        private LazyLtavService lazyService;
        private EagerLtavService eagerService;

        @Setup
        public void init() {
            super.init();
            lazyService = context.getBean(LazyLtavService.class);
            eagerService = context.getBean(EagerLtavService.class);
            dto = new CashFlowDto(3);
        }
    }
}
