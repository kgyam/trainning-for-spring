package com.kgyam.domain;

import javax.annotation.PreDestroy;

/**
 * @author kg yam
 * @date 2021-02-08 16:31
 * @since
 */
public class DestroyDomain {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PreDestroy
    public void destroy() {
        System.out.println ("DestroyDemo destroy");
    }
}
