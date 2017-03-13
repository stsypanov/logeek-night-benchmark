package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.Dto;
import com.luxoft.logeek.dto.FlagDto;
import com.luxoft.logeek.entity.SomeEntity;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by Наталя on 13.03.2017.
 */
@SuppressWarnings("ALL")
public class ExampleService {

    private SimpleJpaRepository<SomeEntity, Long> jpaRepository;

    public void foo() {
        Long id = 1L;
        Dto dto = new Dto();

        Optional<FlagDto> optionalDto = dto.getValue();
        SomeEntity entity = jpaRepository.findOne(id);

        boolean hasGoodRating = entity.getChildEntity().getRatingEnity().hasGoodRating();
        boolean hasValidFlags = optionalDto.map(this::checkFlags).orElse(false);

        if (hasGoodRating && hasValidFlags) {
            //do smth
        }
    }

    private boolean checkFlags(FlagDto flagDto) {
        return flagDto.isValid() && flagDto.isActive();
    }
}
