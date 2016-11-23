package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

	@Query("select count(u.id) = 0 from User u where u.name = :name")
	boolean checkIfAvailableUserName(@Param("name") String name);
}
