package com.masters.masters.exercise.services;

import com.masters.masters.exercise.model.User;

import java.util.List;

public interface IUserService {
    User save(User user);
    List<User> findAll();
    void delete(long id);
}
