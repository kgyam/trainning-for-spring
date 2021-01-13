package com.kgyam.domain;


import com.kgyam.annotation.Super;

import java.util.Arrays;

@Super
public class SuperUser extends User {
    private String superId;
    private String[] permissions;


    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

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
                ", permissions=" + Arrays.toString (permissions) +
                "} " + super.toString ();
    }
}
