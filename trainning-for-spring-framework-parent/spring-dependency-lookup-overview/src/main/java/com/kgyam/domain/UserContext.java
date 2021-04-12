package com.kgyam.domain;

import java.util.Arrays;

/**
 * @author kg yam
 * @date 2021-04-12 11:27
 * @since
 */
public class UserContext {
    private String[] context;

    public String[] getContext() {
        return context;
    }

    public void setContext(String[] context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "UserContext{" +
                "context=" + Arrays.toString (context) +
                '}';
    }
}
