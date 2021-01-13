package com.kgyam.domain;

import com.kgyam.enums.Region;

import java.util.List;

public class User {
    private String name;
    private int age;
    private Region region;
    private List<Region> workCity;

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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", region=" + region +
                ", workCity=" + workCity +
                '}';
    }

    public static User createUser() {
        return new User ();
    }
}
