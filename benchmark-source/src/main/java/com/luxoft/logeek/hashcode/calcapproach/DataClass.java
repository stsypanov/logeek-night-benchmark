package com.luxoft.logeek.hashcode.calcapproach;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataClass {
	private final Long id;
	private final String idString;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DataClass dataClass = (DataClass) o;

		if (id != null ? !id.equals(dataClass.id) : dataClass.id != null) return false;
		return idString != null ? idString.equals(dataClass.idString) : dataClass.idString == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (idString != null ? idString.hashCode() : 0);
		return result;
	}
}
