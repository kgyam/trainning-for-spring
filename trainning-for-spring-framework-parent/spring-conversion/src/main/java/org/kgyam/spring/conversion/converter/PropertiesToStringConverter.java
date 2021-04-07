package org.kgyam.spring.conversion.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Map;
import java.util.Properties;

/**
 * Converter转换器，
 * <p>
 * 1. 缺少条件判断。有类型不安全风险
 * 2. 只能转换单一的SourceType和TargetType
 *
 * @author kg yam
 * @date 2021-04-06 17:24
 * @since
 */
public class PropertiesToStringConverter implements Converter<Properties, String> {
    @Override
    public String convert(Properties properties) {
        StringBuilder stringBuilder = new StringBuilder ();
        for (Map.Entry<Object, Object> entry : properties.entrySet ()) {
            stringBuilder.append (entry.getKey () + ":" + entry.getValue ()).append (",");
        }
        return stringBuilder.deleteCharAt (stringBuilder.length () - 1).toString ();
    }
}
