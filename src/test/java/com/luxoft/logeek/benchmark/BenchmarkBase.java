package com.luxoft.logeek.benchmark;

import java.util.concurrent.ThreadLocalRandom;

public abstract class BenchmarkBase {
	protected ThreadLocalRandom random;
	
	protected void init() {
		random = ThreadLocalRandom.current();
	}
}
