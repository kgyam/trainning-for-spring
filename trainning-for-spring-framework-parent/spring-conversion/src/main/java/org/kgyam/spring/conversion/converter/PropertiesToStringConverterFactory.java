package org.kgyam.spring.conversion.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;
import java.util.Properties;

/**
 * 使用工厂模式，定义自具体的source和target类型，那么在getConverter(Class<T> class)中可以获取target类型子类的converter
 * 这种情况下能够让工厂根据不同的传入类型回传不同的converter
 *
 * @author kg yam
 * @date 2021-04-06 17:45
 * @since
 */
public class PropertiesToStringConverterFactory implements ConverterFactory<Properties, String> {
    @Override
    public <T extends String> Converter<Properties, T> getConverter(Class<T> aClass) {

        return new InnerPropertiesToStringConverter ();
    }


    private static final class InnerPropertiesToStringConverter<T extends String> implements Converter<Properties, String>
            , ConditionalConverter {


        @Override
        public String convert(Properties properties) {
            StringBuilder stringBuilder = new StringBuilder ();
            for (Map.Entry<Object, Object> entry : properties.entrySet ()) {
                stringBuilder.append (entry.getKey () + ":" + entry.getValue ()).append (",");
            }
            return stringBuilder.deleteCharAt (stringBuilder.length () - 1).toString ();
        }

        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
            return Properties.class.equals (sourceType.getType ()) && String.class.equals (targetType.getType ());
        }
    }
}
