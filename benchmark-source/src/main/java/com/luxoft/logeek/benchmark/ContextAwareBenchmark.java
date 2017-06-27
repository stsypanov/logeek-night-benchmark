package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class ContextAwareBenchmark extends BaseBenchmark {
	protected AnnotationConfigApplicationContext context;

	protected void init() {
		super.init();
		context = new AnnotationConfigApplicationContext(AppConfig.class);
	}

	protected <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}
}
