package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.dto.UserDto;
import com.luxoft.logeek.entity.User;
import com.luxoft.logeek.repository.UserRepository;
import com.luxoft.logeek.service.ltav.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Set<User> findInLoop(List<UserDto> userDtos) {
		return userDtos.stream()
				.map(UserDto::getUserId)
				.map(userRepository::findOne)
				.collect(Collectors.toSet());
	}

	@Override
	public List<User> findWithSingleCall(List<UserDto> userDtos) {
		List<Long> ids = userDtos.stream()
				.map(UserDto::getUserId)
				.collect(Collectors.toList());

		return userRepository.findAll(ids);  //todo check if it returns unique values
	}
}
