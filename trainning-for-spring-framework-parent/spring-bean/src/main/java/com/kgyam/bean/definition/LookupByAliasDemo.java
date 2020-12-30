package com.kgyam.bean.definition;

import com.kgyam.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过别名 依赖查找到对应的bean
 * 通过别名查找到的bean和通过name/id查找到的bean比较，结果是同一个bean
 * <p>
 * 使用别名，可以让bean具有更符合当前业务场景的一个名称。（bean有可能是第三方的依赖，name/id可能我们无法修改的）
 */
public class LookupByAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-alias-context.xml");
        User user = beanFactory.getBean("user", User.class);
        User user_alias = beanFactory.getBean("general_yam", User.class);
        System.out.println(user == user_alias);//true
    }
}
