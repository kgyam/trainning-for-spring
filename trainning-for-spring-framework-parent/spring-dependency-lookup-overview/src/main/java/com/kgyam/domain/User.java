package com.kgyam.domain;

import com.kgyam.enums.Region;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class User implements InitializingBean, DisposableBean {
    private String name;
    private int age;
    private Region region;
    private List<Region> workCity;
    private Company company;
    private Properties context;
    private UserContext contextList;

    public User() {
    }

    public List<Region> getWorkCity() {
        return workCity;
    }

    public void setWorkCity(List<Region> workCity) {
        this.workCity = workCity;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Properties getContext() {
        return context;
    }

    public void setContext(Properties context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", region=" + region +
                ", workCity=" + workCity +
                ", company=" + company +
                ", context=" + context +
                ", contextList=" + contextList +
                '}';
    }

    public UserContext getContextList() {
        return contextList;
    }

    public void setContextList(UserContext contextList) {
        this.contextList = contextList;
    }

    public static User createUser() {
        return new User ();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println ("User afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println ("User destroy");
    }


    @PostConstruct
    public void init() {
        System.out.println ("User PostConstruct");
    }

    @PostConstruct
    public void init2() {
        System.out.println ("User PostConstruct2");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println ("User PreDestroy");
    }
}
