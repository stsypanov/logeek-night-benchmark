package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.CpyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CpyRepository extends JpaRepository<CpyEntity, Long>{
	
	@Query("select c.pdRating from CpyEntity c where c.id = :cpyId")
	boolean findIfCpyShowsContracts(@Param("cpyId") Long cpyId);
	
	@Query("select cpy.pdRating from FacilityEntity f " +
			"join f.cpy cpy " +
			"where f.id = :facId")
	boolean findIfCpyShowsContractsByFacId(@Param("facId") Long facId);
	
}
