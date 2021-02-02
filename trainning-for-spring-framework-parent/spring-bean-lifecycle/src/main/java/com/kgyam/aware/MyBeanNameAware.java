package com.kgyam.aware;

import org.springframework.beans.factory.BeanNameAware;

/**
 * @author kg yam
 * @date 2021-02-02 16:36
 * @since
 */
public class MyBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String name) {
        System.out.println ("setBeanName");
    }
}
