package com.luxoft.logeek.benchmark.audit;

import com.luxoft.logeek.benchmark.ContextAwareBenchmarkBase;
import com.luxoft.logeek.dto.AuditDto;
import com.luxoft.logeek.repository.AuditRepository;
import com.luxoft.logeek.service.AuditLocalService;
import org.openjdk.jmh.annotations.*;

import java.util.HashSet;
import java.util.Set;

@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class AuditBenchmark {

    @Benchmark
    public void auditChanges(Data data) {
        data.service.auditChanges(data.inserts, data.updates, data.deletes);
    }

    @Benchmark
    public void auditChangesEffectively(Data data) {
        data.service.auditChangesEffectively(data.inserts, data.updates, data.deletes);
    }

    public static class Data extends ContextAwareBenchmarkBase {
        @Param({"10", "100", "1000"})
        private int count;

        private AuditLocalService service;
        private AuditRepository auditRepository;
        private Set<AuditDto> inserts;
        private Set<AuditDto> updates;
        private Set<AuditDto> deletes;

        @Setup
        public void init() {
            super.init();
            service = context.getBean(AuditLocalService.class);
            auditRepository = context.getBean(AuditRepository.class);
        }

        @Setup(Level.Iteration)
        public void prepareFreshData() {
            inserts = new HashSet<>(count);
            updates = new HashSet<>(count);
            deletes = new HashSet<>(count);

            for (long i = 0; i < count; i++) {
                inserts.add(new AuditDto(i));
                updates.add(new AuditDto(i * 2));
                deletes.add(new AuditDto(i * 3));
            }
        }

        @TearDown(Level.Iteration)
        public void tearDown() {
            auditRepository.deleteAllInBatch();
        }
    }
}
