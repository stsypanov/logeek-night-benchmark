package com.luxoft.logeek.benchmark.example;

import com.luxoft.logeek.benchmark.ContextAwareBenchmarkBase;
import com.luxoft.logeek.dto.Dto;
import com.luxoft.logeek.entity.ChildEntity;
import com.luxoft.logeek.entity.RatingEntity;
import com.luxoft.logeek.entity.SomeEntity;
import com.luxoft.logeek.repository.SomeJpaRepository;
import com.luxoft.logeek.service.ExampleService;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
public class CircuitousTreasureHuntBenchmark {

    @Benchmark
    public long doIneffectively(Data data) {
        return data.exampleService.doIneffectively(data.id, data.dto);
    }

	@Benchmark
	public long doEffectively(Data data) {
		return data.exampleService.doEffectively(data.id, data.dto);
	}

	@Benchmark
	public long doMostEffectively(Data data) {
		return data.exampleService.doMostEffectively(data.id, data.dto);
	}

	@State(Scope.Thread)
	public static class Data extends ContextAwareBenchmarkBase{
		private ExampleService exampleService;
		private SomeJpaRepository repository;

		@Param({"true", "false"})
		private boolean valid;

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
					.collect(toList());
		}

		@Setup(value = Level.Iteration)
		public void setUp() {
			dto = new Dto(valid);
			id = ids.get(random.nextInt(ITEMS_COUNT));
		}

		@TearDown
		public void after() {
			repository.deleteAllInBatch();
		}

		private void prepareDataInDb(Long id) {
			RatingEntity rating = new RatingEntity();

			ChildEntity child = new ChildEntity();
			child.setRatingEntity(rating);

			SomeEntity someEntity = new SomeEntity();
			someEntity.setId(id);
			someEntity.setChildEntity(child);

			repository.save(someEntity);
		}
	}
}
