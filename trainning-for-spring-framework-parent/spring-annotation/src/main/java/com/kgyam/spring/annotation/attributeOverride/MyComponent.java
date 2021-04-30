package com.kgyam.spring.annotation.attributeOverride;

import org.springframework.stereotype.Component;

/**
 * @author kg yam
 * @date 2021-04-29 17:09
 * @since
 */

@Component
public class MyComponent {
    private String name = "fieldOverrideComponent";
    private String id = "12345";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyComponent{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
