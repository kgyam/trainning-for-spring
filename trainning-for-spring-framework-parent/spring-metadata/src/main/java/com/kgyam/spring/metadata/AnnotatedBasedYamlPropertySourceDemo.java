package com.kgyam.spring.metadata;

import com.kgyam.domain.User;
import com.kgyam.enums.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 * 使用java注解方式加载yaml外部化配置
 * 通过{@link PropertySource} 方式配置,其中value指向资源路径,factory指向处理yaml资源的{@link PropertySourceFactory}实现类
 *
 * {@link YamlPropertySourceFactory} 是PropertySourceFactory的实现类
 * @author kg yam
 * @date 2021-02-25 11:24
 * @since
 */

@PropertySource(name = "yamlPropertySource", value = "classpath:/META-INF/user.yaml"
        , factory = YamlPropertySourceFactory.class)
public class AnnotatedBasedYamlPropertySourceDemo {

    @Bean
    public User user(@Value("${user.user-name}") String name, @Value("${user.region}") Region region) {
        User user = new User ();
        user.setName (name);
        user.setRegion (region);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (AnnotatedBasedYamlPropertySourceDemo.class);
        applicationContext.refresh ();
        User user = applicationContext.getBean ("user", User.class);
        System.out.println (user);
        applicationContext.close ();
    }
}



