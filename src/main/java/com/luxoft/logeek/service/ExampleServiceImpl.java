package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.Dto;
import com.luxoft.logeek.entity.RatingEntity;
import com.luxoft.logeek.entity.SomeEntity;
import com.luxoft.logeek.repository.SomeJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Predicate;

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
			return entity.getChildEntity().getRating().hasGoodRating();
		};
		this.moreEffectiveRatingPredicate = id -> {
			RatingEntity ratingEntity = jpaRepository.findRating(id);
			if (ratingEntity == null) {
				return false;
			}
			return ratingEntity.hasGoodRating();
		};
	}

	@Override
	public long doIneffectively(Long id, Dto dto) {
		SomeEntity entity = jpaRepository.findOne(id);

		boolean hasGoodRating = entity.getChildEntity().getRating().hasGoodRating();
		boolean hasValidFlags = dto.isValid();

		if (hasGoodRating && hasValidFlags) {
			return System.currentTimeMillis() >> 2;
		} else {
			return System.currentTimeMillis() << 3;
		}
	}

	@Override
	public long doEffectively(Long id, Dto dto) {
		boolean valid = dto.isValid();

		if (valid && hasGoodRatingPredicate.test(id)) {
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
