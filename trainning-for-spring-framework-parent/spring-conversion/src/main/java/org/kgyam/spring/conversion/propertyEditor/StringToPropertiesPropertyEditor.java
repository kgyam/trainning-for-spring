package org.kgyam.spring.conversion.propertyEditor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * 类型转换器，底层是利用java beans的propertyEditor实现。
 * spring后期放弃了该实现。因为这种实现存在一定缺陷：
 * 1. 由于PropertyEditor包含大量针对GUI的操作，因而从功能性来说违反了单一职责原则
 * 2. setValue和getValue并没有支持泛型操作，在每次获取value的时候需要开发者知道对应的转换类型，并不优雅
 *
 *
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
