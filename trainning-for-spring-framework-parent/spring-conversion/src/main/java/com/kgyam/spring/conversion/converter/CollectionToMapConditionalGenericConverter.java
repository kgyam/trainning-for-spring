package com.kgyam.spring.conversion.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.*;

/**
 * 如果list是从xml中以<util:list>方式配置，无法获取list的成员类型，这里存在一定缺陷
 * <p>
 * <p>
 * 正常情况下通过elementTypeDescriptor可以获取成员类型，得知source和target的成员类型后，可以从conversionService中找到对应的converter进行转换。
 * 这个接口实现将无需耦合特定单一类型转换过程代码，让复杂类型转换接口的代码更加抽象通用
 * <p>
 * 参考这个转换器理解复杂类型的转换过程
 *
 * @see org.springframework.core.convert.support.CollectionToArrayConverter
 */
public class CollectionToMapConditionalGenericConverter implements ConditionalGenericConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Collection.class.isAssignableFrom(sourceType.getType()) && Map.class.isAssignableFrom(targetType.getType());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Collection.class, Map.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        Collection<?> collection = (Collection<?>) source;
        TypeDescriptor elementTypeDescriptor = sourceType.getElementTypeDescriptor();
        Class sourceElementType = null;
        if (elementTypeDescriptor != null) {
            sourceElementType = elementTypeDescriptor.getType();
        } else {

        }

        Map<?, ?> map = new HashMap<>(collection.size());
        TypeDescriptor targetElementTypeDescriptor = targetType.getElementTypeDescriptor();
        for (Object o : collection) {
            sourceElementType.cast(o);
        }
        return null;
    }
}
