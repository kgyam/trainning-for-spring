package com.kgyam.dependency.injection.domain;

import com.kgyam.domain.SuperUser;
import com.kgyam.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 方法注入的组建类
 *
 * @author kg yam
 * @date 2021-01-28 17:38
 * @since
 */
@Component
public class UserManger implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public User createUser() {
        return applicationContext.getBean (User.class);
    }

    @Lookup
    public User createUserByAnnotation() {
        return null;
    }

    @Lookup(value = "superUser")
    public SuperUser createUserByBeanAnnotation() {
        return null;
    }

    public User createUserXml() {
        return null;
    }
}
