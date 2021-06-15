package com.WebMall.service;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String email, String password, HttpServletRequest request);
}
