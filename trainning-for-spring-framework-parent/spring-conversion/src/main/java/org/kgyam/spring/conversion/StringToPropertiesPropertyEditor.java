package org.kgyam.spring.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * @author kg yam
 * @date 2021-04-02 17:32
 * @since
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {


    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Properties properties = new Properties ();
        StringReader reader = new StringReader (text);
        try {
            properties.load (reader);
            //临时存储转换后的value
            setValue (properties);
        } catch (IOException e) {
            throw new IllegalArgumentException (e);
        }
    }

    /**
     * 将value转换成string输出
     * @return
     */
    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue ();
        StringBuilder stringBuilder = new StringBuilder ();
        for (Map.Entry<Object, Object> entry : properties.entrySet ()) {
            stringBuilder.append (entry.getKey () + ":" + entry.getValue ()).append (",");
        }
        return stringBuilder.deleteCharAt (stringBuilder.length () - 1).toString ();
    }

}
