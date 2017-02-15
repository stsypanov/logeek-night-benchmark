package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.AppConfig;
import com.luxoft.logeek.JpaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

/**
 * Created by Сергей on 12.02.2017.
 */
public abstract class BenchmarkBase {
	protected Random random;
	protected AnnotationConfigApplicationContext context;

	protected void initContext() {
		context = new AnnotationConfigApplicationContext(JpaConfig.class, AppConfig.class);
		random = new Random(System.currentTimeMillis());
	}
}
