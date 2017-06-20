package com.luxoft.logeek.hashcode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class SomeVO {
	private final Long id;
	private final String name;
	private final List<Long> list;
}
