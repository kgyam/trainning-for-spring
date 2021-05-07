package com.kgyam.spring.annotation.enabledAnnotation;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ImportBeanDefinitionRegistrar实现类，
 * 根据FCConfiguration类注册beanDefinition
 * @author kg yam
 * @date 2021-05-06 14:15
 * @since
 */
public class FcImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition (FCConfiguration.class);
        BeanDefinitionReaderUtils.registerWithGeneratedName (beanDefinition, registry);
    }
}
