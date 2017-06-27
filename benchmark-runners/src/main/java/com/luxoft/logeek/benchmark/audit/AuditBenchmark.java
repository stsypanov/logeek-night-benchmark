package com.luxoft.logeek.benchmark.audit;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.dto.AuditDto;
import com.luxoft.logeek.repository.AuditRepository;
import com.luxoft.logeek.service.AuditLocalService;
import org.openjdk.jmh.annotations.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class AuditBenchmark extends ContextAwareBenchmark {
	private AuditLocalService service;
	private AuditRepository auditRepository;
	private Set<AuditDto> inserts;
	private Set<AuditDto> updates;
	private Set<AuditDto> deletes;

	@Setup()
	public void init() {
		super.init();
		service = context.getBean(AuditLocalService.class);
		auditRepository = context.getBean(AuditRepository.class);
	}

	@Setup(Level.Iteration)
	public void prepareFreshData() {
		inserts = new HashSet<>();
		updates = new HashSet<>();
		deletes = new HashSet<>();

		for (long i = 0; i < 100; i++) {
			inserts.add(new AuditDto(i));
			updates.add(new AuditDto(i * 2));
			deletes.add(new AuditDto(i * 3));
		}
	}

	@TearDown(Level.Iteration)
	public void tearDown() {
		auditRepository.deleteAllInBatch();
	}

	@Benchmark
	public void auditChanges() {
		service.auditChanges(inserts, updates, deletes);
	}

	@Benchmark
	public void auditChangesEffectively() {
		service.auditChangesEffectively(inserts, updates, deletes);
	}
}
