package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.User;
import com.luxoft.logeek.repository.UserRepository;
import com.luxoft.logeek.service.ltav.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findInLoop(List<Long> ids) {
		return ids.stream().map(userRepository::findOne).collect(Collectors.toList());
	}

	@Override
	public List<User> findWithSingleCall(List<Long> ids) {
		return userRepository.findAll(ids);
	}
}
