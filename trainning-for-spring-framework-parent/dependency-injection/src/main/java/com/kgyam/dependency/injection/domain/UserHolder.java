package com.kgyam.dependency.injection.domain;

import com.kgyam.domain.User;

public class UserHolder {

    private User user;

    public UserHolder(User user) {
        System.out.println ("UserHolder Constructor," + user);
        System.out.println ();
        this.user = user;
    }

    public UserHolder() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
