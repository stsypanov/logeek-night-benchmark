package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.FacilityDto;
import com.luxoft.logeek.entity.FacilityEntity;
import com.luxoft.logeek.repository.CpyRepository;
import com.luxoft.logeek.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ValidationServiceImpl implements ValidationService {

	private final CpyRepository cpyRepository;
	private final FacilityRepository facilityRepository;
	private final Random random;

	@Autowired
	public ValidationServiceImpl(CpyRepository cpyRepository, FacilityRepository facilityRepository) {
		this.cpyRepository = cpyRepository;
		this.facilityRepository = facilityRepository;
		random = new Random(System.currentTimeMillis());
	}

	@Override
	public long validateFacility(FacilityDto dto) {
		Long facilityId = dto.getId();

		boolean isNewFacility = facilityId == null;
		boolean isNewMdrFacility = isNewFacility && dto.isMdrFacility();

		if (isNewMdrFacility) {
			boolean cpyShowsContracts = cpyRepository.findIfCpyShowsContracts(dto.getCpyId());
			if (!cpyShowsContracts) {
				return random.nextLong() + random.nextInt();
			}
		}

		boolean isDepositFacility = dto.isDepositFacility();
		if (isDepositFacility) {
			boolean cpyShowsContracts = cpyRepository.findIfCpyShowsContractsByFacId(dto.getCpyId());
			if (!cpyShowsContracts) {
				return random.nextLong() + random.nextInt();
			}
		}

		if (!isNewFacility) {
			FacilityEntity facility = facilityRepository.findOne(facilityId);

			boolean cpyHasGoodRating = !facility.getCpy().hasBadPdRating();
			
			if(cpyHasGoodRating) {
				return random.nextLong() + random.nextInt();
			}
		}
		return random.nextLong() + random.nextInt();
	}
}
