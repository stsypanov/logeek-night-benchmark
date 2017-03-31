package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ThreadLocalRandom;

public abstract class BenchmarkWithAppContext {
	protected ThreadLocalRandom random;
	protected AnnotationConfigApplicationContext context;

	protected void initContext() {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		random = ThreadLocalRandom.current();
	}
}
