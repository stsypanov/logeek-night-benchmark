package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class BenchmarkWithAppContext extends BenchmarkBase {
	protected AnnotationConfigApplicationContext context;

	protected void init() {
		super.init();
		context = new AnnotationConfigApplicationContext(AppConfig.class);
	}
}
