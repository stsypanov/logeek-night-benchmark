package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.User;

import java.util.List;

public interface UserService {

	List<User> findInLoop(List<Long> ids);

	List<User> findWithSingleCall(List<Long> ids);
}
