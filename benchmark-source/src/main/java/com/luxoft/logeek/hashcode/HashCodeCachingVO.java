package com.luxoft.logeek.hashcode;

import java.util.List;

public class HashCodeCachingVO {
	private final Long id;
	private final String name;
	private final List<Long> list;
	
	private volatile int hashCode = -1;

	public HashCodeCachingVO(Long id, String name, List<Long> list) {
		this.id = id;
		this.name = name;
		this.list = list;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HashCodeCachingVO HashCodeCachingVO = (HashCodeCachingVO) o;

		if (id != null ? !id.equals(HashCodeCachingVO.id) : HashCodeCachingVO.id != null) return false;
		if (name != null ? !name.equals(HashCodeCachingVO.name) : HashCodeCachingVO.name != null) return false;
		return list != null ? list.equals(HashCodeCachingVO.list) : HashCodeCachingVO.list == null;
	}

	@Override
	public int hashCode() {
		if (hashCode == -1) {
			int result = id != null ? id.hashCode() : 0;
			result = 31 * result + (name != null ? name.hashCode() : 0);
			hashCode = 31 * result + (list != null ? list.hashCode() : 0);
		}
		return hashCode;
	}

	public Long getId() {
		return id;
	}
}

