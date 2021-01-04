package com.kgyam.domain;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class SubUser implements InitializingBean {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void lazyInit() {
        System.out.println("lazy init by @Lazy");
    }

    public void init() {
        System.out.println("init by xml method init config");
    }


    public void initByAnnotation() {
        System.out.println("init by @Bean init-method");
    }

    @PostConstruct
    public void initByPostConstruct() {
        System.out.println("init method by  @PostConstruct");
    }

    @Override
    public String toString() {
        return "SubUser{" +
                "type='" + type + '\'' +
                "} " + super.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" init by InitializingBean#afterPropertiesSet()");
    }
}
