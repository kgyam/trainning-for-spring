package com.kgyam.domain;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Map;

public class Users {

    private Map<String, User> users;

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }
}
