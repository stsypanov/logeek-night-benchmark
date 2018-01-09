package com.luxoft.logeek.base;

import java.util.concurrent.ThreadLocalRandom;

public abstract class BaseBenchmark {
	protected ThreadLocalRandom random;
	
	protected void init() {
		random = ThreadLocalRandom.current();
	}
}
