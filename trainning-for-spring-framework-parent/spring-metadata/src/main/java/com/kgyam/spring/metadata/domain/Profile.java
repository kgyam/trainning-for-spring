package com.kgyam.spring.metadata.domain;

import java.util.Arrays;

/**
 * @author kg yam
 * @date 2021-02-25 15:27
 * @since
 */
public class Profile {
    private String env;
    private String desc;
    private Object[] params;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "env='" + env + '\'' +
                ", desc='" + desc + '\'' +
                ", params=" + Arrays.toString (params) +
                '}';
    }
}
