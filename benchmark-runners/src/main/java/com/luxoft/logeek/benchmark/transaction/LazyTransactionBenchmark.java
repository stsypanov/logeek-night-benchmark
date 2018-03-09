package com.luxoft.logeek.benchmark.transaction;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.dto.CashFlowDto;
import com.luxoft.logeek.service.ltav.EagerLtavService;
import com.luxoft.logeek.service.ltav.LazyLtavService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class LazyTransactionBenchmark {

    @Benchmark
    public long measureLazyTransaction(Data data) {
        return data.lazyService.createCashFlow(data.dto);
    }

    @Benchmark
    public long measureEagerTransaction(Data data) {
        return data.eagerService.createCashFlow(data.dto);
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
