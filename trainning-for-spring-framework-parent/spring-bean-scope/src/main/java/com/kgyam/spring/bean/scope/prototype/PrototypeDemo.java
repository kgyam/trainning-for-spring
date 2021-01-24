package com.kgyam.spring.bean.scope.prototype;

import com.kgyam.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Spring Bean Scope为Prototype 示例
 *
 *
 */
public class PrototypeDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(PrototypeDemo.class);
        applicationContext.refresh();
        for (int i = 0; i < 10; i++) {
            User user = applicationContext.getBean(User.class);
            System.out.println(user);
            System.out.println(user.hashCode());
        }
        applicationContext.close();
    }


    /**
     * 使用注解方式定义Bean Scope
     *
     * @return
     */
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User user() {
        User user = new User();
        user.setName("prototype-user");
        return user;
    }


    /**
     * 使用BeanDefinition定义Bean Scope
     *
     * @return
     */
    private static BeanDefinition buildBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("name", "prototype-user-by-bd")
                .setScope(BeanDefinition.SCOPE_PROTOTYPE);
        return builder.getBeanDefinition();
    }

}
