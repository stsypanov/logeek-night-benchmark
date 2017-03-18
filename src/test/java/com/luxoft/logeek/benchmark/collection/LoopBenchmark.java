package com.luxoft.logeek.benchmark.collection;

import com.luxoft.logeek.dto.UserDto;
import com.luxoft.logeek.entity.User;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Measure retrieving collection of entities in loop one by one
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class LoopBenchmark extends EntityCollectionBenchmark {

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

	@Benchmark
	public Set<User> execute() {
		return service.findInLoop(userDtos);
	}
}
