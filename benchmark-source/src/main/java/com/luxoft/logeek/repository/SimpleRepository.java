package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.SimpleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleRepository extends JpaRepository<SimpleEntity, Long> {
    SimpleEntity findByName(String name);
}
