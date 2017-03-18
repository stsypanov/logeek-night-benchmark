package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
