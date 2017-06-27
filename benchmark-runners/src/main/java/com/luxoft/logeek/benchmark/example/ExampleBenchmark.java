package com.luxoft.logeek.benchmark.example;

import com.luxoft.logeek.benchmark.BenchmarkWithAppContext;
import com.luxoft.logeek.dto.Dto;
import com.luxoft.logeek.entity.ChildEntity;
import com.luxoft.logeek.entity.RatingEntity;
import com.luxoft.logeek.entity.SomeEntity;
import com.luxoft.logeek.repository.SomeJpaRepository;
import com.luxoft.logeek.service.ExampleService;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class ExampleBenchmark extends BenchmarkWithAppContext {
	private ExampleService exampleService;
	private SomeJpaRepository repository;

	private static final int ITEMS_COUNT = 50;
	private List<Long> ids;
	private Long id;
	private Dto dto;

	@Setup
	public void init() {
		super.init();
		exampleService = getBean(ExampleService.class);
		repository = getBean(SomeJpaRepository.class);

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
	public long doEffectively() {
		return exampleService.doEffectively(id, dto);
	}

	@Benchmark
	public long doIneffectively() {
		return exampleService.doIneffectively(id, dto);
	}

	protected void prepareDataInDb(Long id) {
		RatingEntity rating = new RatingEntity();

		ChildEntity child = new ChildEntity();
		child.setRatingEntity(rating);

		SomeEntity someEntity = new SomeEntity();
		someEntity.setId(id);
		someEntity.setChildEntity(child);

		repository.save(someEntity);
	}
}
