package com.luxoft.logeek.example;

import java.util.ArrayList;

public class PatchedArrayList<T> extends ArrayList<T> {

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o instanceof ArrayList)
			return equalsAsArrayList((ArrayList) o);

		return super.equals(o);
	}

	private boolean equalsAsArrayList(ArrayList that) {
		int size = size();

		if (size != that.size()) {
			return false;
		}

		for (int i = 0; i < size; i++) {
			T o1 = get(i);
			Object o2 = that.get(i);
			if (o1 == null ? o2 != null : !o1.equals(o2)) {
				return false;
			}
		}
		return true;
	}
}
