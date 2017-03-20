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

	@SuppressWarnings("ALL")
	public void foo(){
		boolean first = getFirst();
		boolean second = getSecond();
		boolean third = getThird();

		if (first && second) {
			//two methods (first() and second()) must be called in any case
		} else if (third) {
			//two methods (first() and third()) must be called in the best case
			//all three methods must be called in the worst case
		} else {
			//two methods (first() and third()) must be called in the best case
			//all three methods must be called in worst case
		}
	}

	@SuppressWarnings("ALL")
	public void bar(){
		boolean first = getFirst();
		boolean second = getSecond();
		boolean third = getThird();

		if (first || second) {
			//two methods (first() and second()) must be called in the worst case
			//only first() method must be called in the best case
		} else if (third) {
			//all three methods must be called in any case
		} else {
			//all three methods must be called in any case
		}
	}

	private boolean getThird() {
		return false;
	}

	private boolean getSecond() {
		return false;
	}

	private boolean getFirst() {
		return false;
	}
}
