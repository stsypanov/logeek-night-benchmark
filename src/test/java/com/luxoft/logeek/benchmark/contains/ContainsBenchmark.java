package com.luxoft.logeek.benchmark.contains;

import com.luxoft.logeek.entity.BranchEntity;
import com.luxoft.logeek.example.ContainsExample;
import com.luxoft.logeek.service.Service;
import org.openjdk.jmh.annotations.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by Сергей on 10.02.2017.
 */
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class ContainsBenchmark {
	private ThreadLocalRandom random = ThreadLocalRandom.current();

	private ContainsExample example;
	private Service service;
	private List<String> usItems;
	private List<String> nonUsItems;

	private Set<String> usItemsSet;
	private Set<String> nonUsItemsSet;

	@Setup(Level.Iteration)
	public void setup() {
		usItems = random.ints(1000, 1, 1000).boxed().map(Object::toString).collect(Collectors.toList());
		nonUsItems = random.ints(1000, 1, 1000).boxed().map(Object::toString).collect(Collectors.toList());

		usItemsSet = new HashSet<>(usItems);
		nonUsItemsSet = new HashSet<>(nonUsItems);

		service = createService();
		example = new ContainsExample(service);
	}

	@Benchmark
	public Long measureContains() {
		return example.save(usItems, nonUsItems);
	}

	@Benchmark
	public Long measureContainsInSet() {
		return example.save(usItemsSet, nonUsItemsSet);
	}

	private Service createService() {
		return () -> random.ints(1000, 1, 1000)
				.boxed()
				.map(Object::toString)
				.map(BranchEntity::new)
				.collect(Collectors.toList());
	}
}