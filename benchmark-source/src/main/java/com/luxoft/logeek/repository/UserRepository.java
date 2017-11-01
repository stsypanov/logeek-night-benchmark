package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.id from User u")//todo report bug if use native query return type is Integer on H2
    List<Long> findAllIds();
}
