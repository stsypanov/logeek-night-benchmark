package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.Dto;
import com.luxoft.logeek.dto.FlagDto;
import com.luxoft.logeek.entity.SomeEntity;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ExampleServiceImpl implements ExampleService {

    private final SimpleJpaRepository<SomeEntity, Long> jpaRepository;
    private final Predicate<Long> hasGoodRatingPredicate;

    public ExampleServiceImpl(SimpleJpaRepository<SomeEntity, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;

        this.hasGoodRatingPredicate = id -> {
			SomeEntity entity = jpaRepository.findOne(id);
			return entity.getChildEntity().getRatingEnity().hasGoodRating();
		};
    }

    @Override
    public long doIneffectively() {
        Long id = 1L;
        Dto dto = new Dto();

        Optional<FlagDto> optionalDto = dto.getValue();
        SomeEntity entity = jpaRepository.findOne(id);

        boolean hasGoodRating = entity.getChildEntity().getRatingEnity().hasGoodRating();
        boolean hasValidFlags = optionalDto.map(FlagDto::isValid).orElse(false);

        if (hasGoodRating && hasValidFlags) {
            return System.currentTimeMillis() >> 2;
        } else {
            return System.currentTimeMillis() << 3;
        }
    }

    @Override
    public long doEffectively() {
        Long id = 1L;
        Dto dto = new Dto();

        Optional<FlagDto> optionalDto = dto.getValue();

        boolean hasValidFlags = optionalDto.map(FlagDto::isValid).orElse(false);

        if (hasValidFlags && hasGoodRatingPredicate.test(id)) {
            return System.currentTimeMillis() >> 2;
        } else {
            return System.currentTimeMillis() << 3;
        }
    }


}