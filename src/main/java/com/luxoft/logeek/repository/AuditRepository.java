package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Сергей on 12.02.2017.
 */
public interface AuditRepository extends JpaRepository<AuditEntity, Long>{
}
