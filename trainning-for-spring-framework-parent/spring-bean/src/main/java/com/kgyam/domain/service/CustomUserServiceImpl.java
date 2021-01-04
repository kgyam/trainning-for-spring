package com.kgyam.domain.service;

import com.kgyam.domain.User;

public class CustomUserServiceImpl implements UserService {
    @Override
    public User createUser() {
        User user = User.createUser();
        user.setName("customUser");
        return user;
    }
}
