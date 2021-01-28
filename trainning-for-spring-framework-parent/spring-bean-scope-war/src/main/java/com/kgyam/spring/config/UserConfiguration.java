package com.kgyam.spring.config;

import com.kgyam.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author kg yam
 * @date 2021-01-25 15:32
 * @since
 */
@Configuration
@EnableWebMvc
public class UserConfiguration {

    @Bean
    @RequestScope
    public User requestScopeUser() {
        User user = new User ();
        user.setName ("requestScopeUser");
        user.setAge (40);
        return user;
    }


    @Bean
    @SessionScope
    public User sessionScopeUser() {
        User user = new User ();
        user.setName ("sessionScopeUser");
        user.setAge (30);
        return user;
    }

    @Bean
    @ApplicationScope
    public User applicationScopeUser() {
        User user = new User ();
        user.setName ("applicationScopeUser");
        user.setAge (20);
        return user;
    }

}
