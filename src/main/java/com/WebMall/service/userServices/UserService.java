package com.WebMall.service.userServices;

import com.WebMall.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String email);
}
