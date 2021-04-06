package org.kgyam.spring.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Properties;

/**
 * @author kg yam
 * @date 2021-04-06 17:45
 * @since
 */
public class PropertiesToStringConverterFactory implements ConverterFactory<Properties,String> {
    @Override
    public <T extends String> Converter<Properties, T> getConverter(Class<T> aClass) {
        return null;
    }
}
