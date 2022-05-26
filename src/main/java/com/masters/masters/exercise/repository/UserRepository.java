package com.masters.masters.exercise.repository;

import com.masters.masters.exercise.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
