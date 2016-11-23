package com.luxoft.logeek.benchmark.collection;

import com.luxoft.logeek.entity.User;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Measure retrieving collection of entities in loop one by one
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class LoopBenchmark extends EntityCollectionBenchmark {

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
		List<User> users = service.findInLoop(ids);
		bh.consume(users);
		return users;
	}
}
