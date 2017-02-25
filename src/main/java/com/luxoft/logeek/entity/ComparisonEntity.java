package com.luxoft.logeek.entity;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Сергей on 16.01.2017.
 */
@AllArgsConstructor
public class ComparisonEntity {
	private boolean booleanField;
	private char charField;
	private int intField;
	private long longField;
	private int[] intArrayField;
	private String stringField;
	private Integer integerField;
	private Long longWrapperField;
	private Collection<Integer> intCollection;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ComparisonEntity that = (ComparisonEntity) o;

		if (booleanField != that.booleanField) return false;
		if (charField != that.charField) return false;
		if (intField != that.intField) return false;
		if (longField != that.longField) return false;
		if (!Arrays.equals(intArrayField, that.intArrayField)) return false;
		if (stringField != null ? !stringField.equals(that.stringField) : that.stringField != null) return false;
		if (integerField != null ? !integerField.equals(that.integerField) : that.integerField != null) return false;
		if (longWrapperField != null ? !longWrapperField.equals(that.longWrapperField) : that.longWrapperField != null)
			return false;
		return intCollection != null ? intCollection.equals(that.intCollection) : that.intCollection == null;

	}

	@Override
	public int hashCode() {
		int result = (booleanField ? 1 : 0);
		result = 31 * result + (int) charField;
		result = 31 * result + intField;
		result = 31 * result + (int) (longField ^ (longField >>> 32));
		result = 31 * result + Arrays.hashCode(intArrayField);
		result = 31 * result + (stringField != null ? stringField.hashCode() : 0);
		result = 31 * result + (integerField != null ? integerField.hashCode() : 0);
		result = 31 * result + (longWrapperField != null ? longWrapperField.hashCode() : 0);
		result = 31 * result + (intCollection != null ? intCollection.hashCode() : 0);
		return result;
	}
}
