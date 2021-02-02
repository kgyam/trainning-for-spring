package com.kgyam.domain;

import org.springframework.beans.factory.FactoryBean;

/**
 * User的FactoryBean
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new User ();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
