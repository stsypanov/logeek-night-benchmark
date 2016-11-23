package com.luxoft.logeek.benchmark.collection;

import com.luxoft.logeek.AppConfig;
import com.luxoft.logeek.JpaConfig;
import com.luxoft.logeek.entity.User;
import com.luxoft.logeek.repository.UserRepository;
import com.luxoft.logeek.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityCollectionBenchmark {

	private static final int ENTITY_COUNT = 1000;
	
	protected UserService service;
	protected UserRepository userRepository;
	
	protected void initContext() {
		if (service == null) {
			AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(JpaConfig.class, AppConfig.class);
			service = appContext.getBean(UserService.class);
			userRepository = appContext.getBean(UserRepository.class);
		}
	}
	
	protected List<Long> populateTable() {
		List<Long> userIds = new ArrayList<>(ENTITY_COUNT);
		List<User> users = new ArrayList<>(ENTITY_COUNT);

		for (long i = 1; i < ENTITY_COUNT + 1; i++) {
			userIds.add(i);
			
			User user = new User(
					i,
					"sergei" + i,
					i + "sergei",
					"sergei" + i + "@yandex.ru",
					"root"
					);
			users.add(user);
		}
		
		userRepository.save(users);
		
		Collections.shuffle(userIds);//different id sequence for every iteration
		
		return userIds;
	}
}
