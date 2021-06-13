package com.WebMall.service;

import com.WebMall.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String email);
}
