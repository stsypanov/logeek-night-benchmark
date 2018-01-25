package com.luxoft.logeek.benchmark.audit;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.benchmark.ContextAwareBenchmarkBase;
import com.luxoft.logeek.dto.AuditDto;
import com.luxoft.logeek.repository.AuditRepository;
import com.luxoft.logeek.service.AuditLocalService;
import org.openjdk.jmh.annotations.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Measure saving entities in loop one by one and with bulk save
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class AuditBenchmark {

    @Benchmark
    public void auditChanges(Data data) {
        data.service.auditChanges(data.inserts);
    }

    @Benchmark
    public void auditChangesEffectively(Data data) {
        data.service.auditChangesEffectively(data.inserts);
    }

    @State(Scope.Thread)
    public static class Data extends ContextAwareBenchmark {
        @Param({"10", "100", "1000"})
        private int count;

        private AuditLocalService service;
        private AuditRepository auditRepository;
        private Set<AuditDto> inserts;

        @Setup
        public void init() {
            super.init();
            service = context.getBean(AuditLocalService.class);
            auditRepository = context.getBean(AuditRepository.class);
        }

        @Setup(Level.Iteration)
        public void prepareFreshData() {
            inserts = new HashSet<>(count);

            for (long i = 0; i < count; i++) {
                inserts.add(new AuditDto(i));
            }
        }

        @TearDown(Level.Iteration)
        public void tearDown() {
            auditRepository.deleteAllInBatch();
        }
    }
}
