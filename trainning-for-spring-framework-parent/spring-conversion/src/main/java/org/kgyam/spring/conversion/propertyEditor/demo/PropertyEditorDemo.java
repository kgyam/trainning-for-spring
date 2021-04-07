package org.kgyam.spring.conversion.propertyEditor.demo;

import org.kgyam.spring.conversion.propertyEditor.StringToPropertiesPropertyEditor;

import java.util.Properties;

public class PropertyEditorDemo {

    public static void main(String[] args) {
        StringToPropertiesPropertyEditor editor = new StringToPropertiesPropertyEditor();
        editor.setAsText("name=dalipapa");
        Properties properties = (Properties) editor.getValue();
        System.out.println(properties.toString());
    }

}
