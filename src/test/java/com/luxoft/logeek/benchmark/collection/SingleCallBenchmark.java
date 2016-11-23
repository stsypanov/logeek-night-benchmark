package com.luxoft.logeek.benchmark.collection;

import com.luxoft.logeek.entity.User;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Measure retrieving collection of entities with single call
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SingleCallBenchmark extends EntityCollectionBenchmark {
	
	private List<Long> ids;

	@Setup()
	public void init() {
		initContext();
	}

	@Setup(Level.Iteration)
	public void initIds() {
		ids = populateTable();
	}

	@Benchmark
	public List<User> execute(Blackhole bh) {
		List<User> users = service.findWithSingleCall(ids);
		bh.consume(users);
		return users;
	}
}
