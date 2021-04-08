package org.kgyam.spring.conversion.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

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
        return null;
    }
}
