package com.luxoft.logeek.benchmark.example;

import com.luxoft.logeek.dto.Dto;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class EffectiveExampleBenchmark extends ExampleBenchmark {

	private static final int ITEMS_COUNT = 50;
	private List<Long> ids;

	@Setup
	public void init() {
		super.init();
		ids = random.longs(ITEMS_COUNT, 1, 50)
				.boxed()
				.peek(this::prepareDataInDb)
				.collect(Collectors.toList());
	}

	@Setup(value = Level.Iteration)
	public void setUp() throws Exception {
		dto = new Dto(false);
		id = ids.get(random.nextInt(ITEMS_COUNT));
	}
	
	@TearDown
	public void after() {
		repository.deleteAll();
	}

	@Benchmark
	public long execute() {
		return exampleService.doEffectively(id, dto);
	}
}
