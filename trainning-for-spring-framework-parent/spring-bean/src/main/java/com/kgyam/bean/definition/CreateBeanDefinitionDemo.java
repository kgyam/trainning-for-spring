package com.kgyam.bean.definition;

import com.kgyam.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class CreateBeanDefinitionDemo {

    public static void main(String[] args) {

    }


    /**
     * 通过beanDefinitionBuilder构建beanDefinition
     * <p>
     * builder模式
     */
    static void createBeanDefinitionByBuilder() {
        //
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("name", "kgyam").addPropertyValue("age", 18);
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        System.out.println(beanDefinition);
    }


    static void createBeanDefinitionByAbstractBeanDefinition() {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
//        mutablePropertyValues.addPropertyValue("name","kidchan") ;
        //                                      addPropertyValue不能链式调用
        mutablePropertyValues.add("name", "kidchan")
                .add("age", 30);
        beanDefinition.setPropertyValues(mutablePropertyValues);

    }

}
