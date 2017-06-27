package com.luxoft.logeek.repository;


import com.luxoft.logeek.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

@SuppressWarnings("SpringDataJpaMethodInconsistencyInspection")
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {

	@Query("select sum(lc.compensation) from ContractEntity lc where lc.facility = :facility")
	BigDecimal findCompensationByFacility(@Param("facility") Long facility);

	@Query("select sum(lc.deposit) from ContractEntity lc where lc.facility = :facility")
	BigDecimal findDepositByFacilityId(@Param("facility") Long facility);

	@Query("select sum(lc.outstanding) from ContractEntity lc where lc.facility = :facility")
	BigDecimal findOutstandingByFacility(@Param("facility") Long facility);
}
