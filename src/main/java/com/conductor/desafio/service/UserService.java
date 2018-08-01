package com.conductor.desafio.service;

import com.conductor.desafio.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
