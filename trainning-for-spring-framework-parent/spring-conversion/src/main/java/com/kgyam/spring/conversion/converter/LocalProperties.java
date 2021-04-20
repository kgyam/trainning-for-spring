package com.kgyam.spring.conversion.converter;

/**
 * @author kg yam
 * @date 2021-04-07 15:25
 * @since
 */

public class LocalProperties {
    private String propertiesAsText;

    public String getPropertiesAsText() {
        return propertiesAsText;
    }

    public void setPropertiesAsText(String propertiesAsText) {
        this.propertiesAsText = propertiesAsText;
    }

    @Override
    public String toString() {
        return "LocalProperties{" +
                "propertiesAsText='" + propertiesAsText + '\'' +
                '}';
    }
}
