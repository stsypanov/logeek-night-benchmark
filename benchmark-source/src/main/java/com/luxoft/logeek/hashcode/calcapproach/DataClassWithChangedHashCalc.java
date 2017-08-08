package com.luxoft.logeek.hashcode.calcapproach;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class DataClassWithChangedHashCalc {
	private final Long id;
	private final String idString;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DataClassWithChangedHashCalc dataClass = (DataClassWithChangedHashCalc) o;

		if (id != null ? !id.equals(dataClass.id) : dataClass.id != null) return false;
		return idString != null ? idString.equals(dataClass.idString) : dataClass.idString == null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idString);
	}
}
