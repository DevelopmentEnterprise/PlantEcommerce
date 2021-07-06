package com.WebMall.service.userServices;

import com.WebMall.model.User;

public interface UserService {
    void createNewUser(User user);
    boolean save(User user);
    User findByUsername(String email);
    boolean checkAuthUser();
    User getLoggedUser();
    boolean checkForRole(String roleName);
}
