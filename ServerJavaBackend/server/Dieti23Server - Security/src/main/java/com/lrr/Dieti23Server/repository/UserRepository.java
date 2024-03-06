package com.lrr.Dieti23Server.repository;

import com.lrr.Dieti23Server.model.UserTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserTest,Integer> {

    Optional<UserTest> findByEmail(String email);
}
