package com.luxoft.logeek.benchmark.audit;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.entity.AuditEntity;
import com.luxoft.logeek.repository.AuditTrailRepository;
import com.luxoft.logeek.service.AuditAware;
import com.luxoft.logeek.service.AuditLocalService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class AuditBenchmark extends BenchmarkBase {
	private AuditLocalService service;
	private AuditTrailRepository auditTrailRepository;
	private Set<AuditAware> inserts;
	private Set<AuditAware> updates;
	private Set<AuditAware> deletes;

	@Setup()
	public void init() {
		super.initContext();
		service = context.getBean(AuditLocalService.class);
		auditTrailRepository = context.getBean(AuditTrailRepository.class);
	}

	@Setup(Level.Iteration)
	public void prepareFreshData() {
		inserts = new HashSet<>();
		updates = new HashSet<>();
		deletes = new HashSet<>();

		for (int i = 0; i < 100; i++) {
			inserts.add(new AuditEntity());
		}
	}

	@TearDown(Level.Iteration)
	public void tearDown() {
		auditTrailRepository.deleteAll();
	}

	@Benchmark
	public void execute(Blackhole bh) {
		try {
			service.auditChanges(inserts, updates, deletes);
		} catch (RuntimeException e) {
			bh.consume(e);
		}
	}

}
