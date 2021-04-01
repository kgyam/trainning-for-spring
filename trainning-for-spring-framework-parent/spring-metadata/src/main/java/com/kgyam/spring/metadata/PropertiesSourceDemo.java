package com.kgyam.spring.metadata;

import com.kgyam.spring.metadata.domain.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载以Properties格式的外部化配置
 *
 * @author kg yam
 * @date 2021-02-25 14:46
 * @since
 */
@PropertySource(value = "classpath:/META-INF/env-source.properties", encoding = "GBK")
public class PropertiesSourceDemo {

    @Bean
    private Profile profile(@Value("${profile.env}") String env
            , @Value("${profile.desc}") String desc) {
        Profile profile = new Profile ();
        profile.setEnv (env);
        profile.setDesc (desc);
        return profile;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (PropertiesSourceDemo.class);
        applicationContext.refresh ();
        Profile profile = applicationContext.getBean (Profile.class);
        System.out.println (profile);
        applicationContext.close ();

    }

}
