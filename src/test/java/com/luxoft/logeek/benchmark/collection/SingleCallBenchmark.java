package com.luxoft.logeek.benchmark.collection;

import com.luxoft.logeek.dto.UserDto;
import com.luxoft.logeek.entity.User;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Measure retrieving collection of entities with single call
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SingleCallBenchmark extends EntityCollectionBenchmark {

	private List<UserDto> userDtos;

	@Setup()
	public void init() {
		initContext();
	}

	@Setup(Level.Iteration)
	public void initIds() {
		userDtos = populateTable().stream()
				.map(UserDto::new)
				.collect(Collectors.toList());
	}

	@TearDown
	public void tearDown() throws Exception {
		repository.deleteAllInBatch();
	}

	@Benchmark
	public List<User> execute() {
		return service.findWithSingleCall(userDtos);
	}
}
