package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.service.AspectedService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AspectedServiceImpl implements AspectedService {
	private final Random random = new Random(System.nanoTime());

	@Override
	public long getRandomLong() {
		return random.nextLong();
	}
}
