package org.kgyam.spring.conversion.converter;

import com.kgyam.domain.User;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个例子说明了，Converter的接口是可以针对复杂类型的做类型转换的
 * <p>
 * 猜想：复杂类型的转换接口实现，如果定义了具体的成员类型，这个转换器的转换功能会比较受限
 * 但是如果不定义成员类型，source则无法获取具体的成员类型造成转换失败。而且复杂类型target也无得知具体的成员类型
 */
public class ListToMapConverter implements Converter<List, Map> {

    @Override
    public Map convert(List source) {
        if (source == null) {
            return null;
        }
        Map<String, Object> map = new HashMap();
        for (Object o : source) {
            map.putIfAbsent(o.getClass().getName(), o);
        }

        return map;
    }
}
