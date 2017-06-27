package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.UserDto;
import com.luxoft.logeek.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

	Set<User> findInLoop(List<UserDto> ids);

	List<User> findWithSingleCall(List<UserDto> ids);
}
