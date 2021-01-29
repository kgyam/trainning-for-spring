package com.kgyam.spring.bean.scope.domain;

import java.util.List;

/**
 * @author kg yam
 * @date 2021-01-28 15:57
 * @since
 */
public class Employee {
    protected long id;
    protected String name;
    protected List<Employee> underling;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getUnderling() {
        return underling;
    }

    public void setUnderling(List<Employee> underling) {
        this.underling = underling;
    }
}
