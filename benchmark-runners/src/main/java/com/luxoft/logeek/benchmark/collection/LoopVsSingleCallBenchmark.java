package com.luxoft.logeek.benchmark.collection;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.dto.UserDto;
import com.luxoft.logeek.entity.User;
import com.luxoft.logeek.repository.UserRepository;
import com.luxoft.logeek.service.ltav.UserService;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Measure retrieving collection of entities in loop one by one
 */
@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class LoopVsSingleCallBenchmark extends ContextAwareBenchmark {
	private static final int ENTITY_COUNT = 1000;

	private UserService service;
	private UserRepository repository;

	private List<UserDto> userDtos;

	@Setup()
	public void init() {
		super.init();
		service = context.getBean(UserService.class);
		repository = context.getBean(UserRepository.class);
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
	public Set<User> findInLoop() {
		return service.findInLoop(userDtos);
	}

	@Benchmark
	public List<User> findWithSingleCall() {
		return service.findWithSingleCall(userDtos);
	}

	protected List<Long> populateTable() {
		List<Long> userIds = new ArrayList<>(ENTITY_COUNT);
		List<User> users = new ArrayList<>(ENTITY_COUNT);

		for (long i = 1; i < ENTITY_COUNT + 1; i++) {
			userIds.add(i);

			User user = new User(
					i,
					"sergei" + i,
					i + "sergei",
					"sergei" + i + "@yandex.ru",
					"root"
			);
			users.add(user);
		}

		repository.save(users);

		Collections.shuffle(userIds);//different id sequence for every iteration

		return userIds;
	}
}
