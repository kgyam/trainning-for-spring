package com.kgyam.spring.annotation.enabledAnnotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ImportSelector实现类
 * 内部还是需要指定Configuration类的路径
 * @author kg yam
 * @date 2021-05-06 12:06
 * @since
 */
public class FCImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.kgyam.spring.annotation.enabledAnnotation.FCConfiguration"};
    }
}
