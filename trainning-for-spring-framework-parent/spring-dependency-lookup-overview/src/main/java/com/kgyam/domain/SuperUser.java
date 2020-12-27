package com.kgyam.domain;


import com.kgyam.annotation.Super;

@Super
public class SuperUser extends User {
    private String superId;

    public String getSuperId() {
        return superId;
    }

    public void setSuperId(String superId) {
        this.superId = superId;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "superId='" + superId + '\'' +
                "} " + super.toString();
    }
}
