package com.kgyam.spring.conversion.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * ConditionalGenericConverter 组合了 GenericConverter, ConditionalConverter这两个接口
 *
 * @author kg yam
 * @date 2021-04-06 17:27
 * @since
 */
public class PropertiesToStringGenericConverter implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Properties.class.equals (sourceType.getObjectType ())
                && String.class.equals (targetType.getObjectType ());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton (new ConvertiblePair (Properties.class, String.class));
    }

    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }
        Properties properties = (Properties) source;
        StringBuilder textBuilder = new StringBuilder ();
        for (Map.Entry<Object, Object> entry : properties.entrySet ()) {
            textBuilder.append (entry.getKey ()).append ("=").append (entry.getValue ()).append (System.getProperty ("line.separator"));
        }
        return textBuilder.toString ();
    }
}
