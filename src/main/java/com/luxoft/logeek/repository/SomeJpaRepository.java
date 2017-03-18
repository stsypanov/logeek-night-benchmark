package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.SomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SomeJpaRepository extends JpaRepository<SomeEntity, Long> {
}
