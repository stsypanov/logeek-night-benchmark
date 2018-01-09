package com.luxoft.logeek.hashcode;

import java.util.List;
import java.util.Objects;

public class SomeVO {
	private final Long id;
	private final String name;
	private final List<Long> list;

	public SomeVO(Long id, String name, List<Long> list) {
		this.id = id;
		this.name = name;
		this.list = list;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SomeVO someVO = (SomeVO) o;
		return Objects.equals(id, someVO.id) &&
				Objects.equals(name, someVO.name) &&
				Objects.equals(list, someVO.list);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, list);
	}

	public Long getId() {
		return id;
	}
}
