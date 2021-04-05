package org.kgyam.spring.conversion;

import java.util.Properties;

public class PropertyEditorDemo {

    public static void main(String[] args) {
        StringToPropertiesPropertyEditor editor = new StringToPropertiesPropertyEditor();
        editor.setAsText("name=dalipapa");
        Properties properties = (Properties) editor.getValue();
        System.out.println(properties.toString());
    }

}
