package com.kgyam.spring.annotation.enabledAnnotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kg yam
 * @date 2021-05-06 12:00
 * @since
 */
@Configuration
public class FCConfiguration {

    @Bean
    public FCClient fcClient() {
        return new FCClient ();
    }

}
