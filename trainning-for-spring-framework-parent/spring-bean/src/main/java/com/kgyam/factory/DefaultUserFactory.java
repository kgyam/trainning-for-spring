package com.kgyam.factory;

import com.kgyam.domain.User;

/**
 * User实例工厂
 */
public class DefaultUserFactory implements UserFactory {
    @Override
    public User createUser() {
        return User.createUser();
    }
}
