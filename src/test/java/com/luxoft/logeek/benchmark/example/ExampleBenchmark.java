package com.luxoft.logeek.benchmark.example;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.dto.Dto;
import com.luxoft.logeek.entity.ChildEntity;
import com.luxoft.logeek.entity.RatingEntity;
import com.luxoft.logeek.entity.SomeEntity;
import com.luxoft.logeek.repository.SomeJpaRepository;
import com.luxoft.logeek.service.ExampleService;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ExampleBenchmark extends BenchmarkBase {
	protected ExampleService exampleService;
	protected SomeJpaRepository repository;
	
	protected Long id;
	protected Dto dto;

	@Setup
	public void init() {
		super.initContext();
		exampleService = context.getBean(ExampleService.class);
		repository = context.getBean(SomeJpaRepository.class);
	}
	
	protected void prepareDataInDb(Long id) {
		RatingEntity rating = new RatingEntity();

		ChildEntity child = new ChildEntity();
		child.setRating(rating);

		SomeEntity someEntity = new SomeEntity();
		someEntity.setId(id);
		someEntity.setChildEntity(child);

		repository.save(someEntity);
	}

}
