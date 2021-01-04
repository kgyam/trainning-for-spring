package com.kgyam.domain;

import org.springframework.beans.factory.FactoryBean;

/**
 * User的FactoryBean
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        User user = User.createUser();
        user.setAge(30);
        user.setName("kidchan");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
