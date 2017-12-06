package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.dto.Dto;
import com.luxoft.logeek.entity.SomeEntity;
import com.luxoft.logeek.repository.SomeJpaRepository;
import com.luxoft.logeek.service.ExampleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Predicate;

@SuppressWarnings("ALL")
@Service
@Transactional(readOnly = true)
public class ExampleServiceImpl implements ExampleService {

	private final SomeJpaRepository jpaRepository;
	private final Predicate<Long> hasGoodRatingPredicate;
	private final Predicate<Long> moreEffectiveRatingPredicate;

	public ExampleServiceImpl(SomeJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;

		this.hasGoodRatingPredicate = id -> {
			SomeEntity entity = jpaRepository.findOne(id);
			return entity.getChildEntity().getRatingEntity().hasGoodRating();
		};
		this.moreEffectiveRatingPredicate = id -> {
			return jpaRepository.findRating(id);
		};
	}

	@Override
	public long doIneffectively(Long id, Dto dto) {
		SomeEntity entity = jpaRepository.findOne(id);

		boolean hasGoodRating = entity.getChildEntity().getRatingEntity().hasGoodRating();
		boolean isValid = dto.isValid();

		if (hasGoodRating && isValid) {
			return System.currentTimeMillis() >> 2;
		} else {
			return System.currentTimeMillis() << 3;
		}
	}

	@Override
	public long doEffectively(Long id, Dto dto) {
		boolean isValid = dto.isValid();

		if (isValid && hasGoodRatingPredicate.test(id)) {
			return System.currentTimeMillis() >> 2;
		} else {
			return System.currentTimeMillis() << 3;
		}
	}

	@Override
	public long doMostEffectively(Long id, Dto dto) {
		boolean valid = dto.isValid();

		if (valid && moreEffectiveRatingPredicate.test(id)) {
			return System.currentTimeMillis() >> 2;
		} else {
			return System.currentTimeMillis() << 3;
		}
	}


}
