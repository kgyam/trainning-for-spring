package org.kgyam.spring.conversion.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;
import java.util.Properties;

/**
 * @author kg yam
 * @date 2021-04-06 17:45
 * @since
 */
public class PropertiesToStringConverterFactory implements ConverterFactory<Properties, String> {
    @Override
    public <T extends String> Converter<Properties, T> getConverter(Class<T> aClass) {

        return new InnerPropertiesToStringConverter ();
    }


    private static final class InnerPropertiesToStringConverter<T extends String> implements Converter<Properties, String> {


        @Override
        public String convert(Properties properties) {
            StringBuilder stringBuilder = new StringBuilder ();
            for (Map.Entry<Object, Object> entry : properties.entrySet ()) {
                stringBuilder.append (entry.getKey () + ":" + entry.getValue ()).append (",");
            }
            return stringBuilder.deleteCharAt (stringBuilder.length () - 1).toString ();
        }
    }
}
