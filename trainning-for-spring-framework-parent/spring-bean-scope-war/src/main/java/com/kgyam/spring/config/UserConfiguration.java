package com.kgyam.spring.config;

import com.kgyam.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
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
    public User user() {
        User user = new User ();
        user.setName ("将军");
        user.setAge (40);
        return user;
    }

}
