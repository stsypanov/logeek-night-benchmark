package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.SomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SomeJpaRepository extends JpaRepository<SomeEntity, Long> {

	@Query("select child.ratingEntity.hasGoodRating from SomeEntity se " +
			" join se.childEntity child " +
			"where se.id = :id")
	boolean findRating(@Param("id") Long id);
}
