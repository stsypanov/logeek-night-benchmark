package com.luxoft.logeek.example;

import com.luxoft.logeek.entity.DatasetEntity;
import com.luxoft.logeek.entity.RefBookingBranchEntity;
import com.luxoft.logeek.utils.DatasetStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.luxoft.logeek.utils.DatasetStatus.*;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;

@SuppressWarnings("ALL")
public class GarbageProducer {
	
	public State createMatrixState(DatasetEntity ds, boolean showInEur) {
		DatasetStatus status = DatasetStatus.find(ds.getStatus());
		if (!EnumSet.of(Draft, Pending, Approved, Stored).contains(status)) {
			return new InvalidMatrixState();
		}
		
		findFilteredIds(null, null);
		
		return new BaseState(ds);
	}

	@Transactional
	public List<String> findFilteredIds(List<RefBookingBranchEntity> bookingBranchEntitiesSearched,
									  Set<String> notUsBookingEntitiesIds) {
		
		Predicate<RefBookingBranchEntity> nonUsPredicate =
				b -> notUsBookingEntitiesIds.contains(b.getId()) && b.isUsBookingEntity();

		return bookingBranchEntitiesSearched.stream()
				.filter(nonUsPredicate)
				.map(RefBookingBranchEntity::getId).collect(Collectors.toList());
	}


}
