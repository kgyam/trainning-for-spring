package com.kgyam.domain.service;

import com.kgyam.domain.User;

public class DefaultUserServiceImpl implements UserService {
    @Override
    public User createUser() {
        User user = User.createUser();
        user.setName("defaultUser");
        return user;
    }
}
