package com.kgyam.spring.metadata;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 通过{@link PropertySourceFactory}策略接口,针对不用的resource类型进行不同处理
 * ,并返回一个{@link PropertiesPropertySource} Properties的包装类。
 *
 * {@link YamlPropertiesFactoryBean} 用于读取yaml格式配置的工厂类
 * @author kg yam
 * @date 2021-02-25 11:28
 * @since
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        //创建读取yaml的工厂bean
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean ();
        //将resource资源设置到里面
        yamlPropertiesFactoryBean.setResources (resource.getResource ());
        //将resource转换为properties
        Properties yamlProperties = yamlPropertiesFactoryBean.getObject ();
        //返回一个properties的wrapper
        return new PropertiesPropertySource (name, yamlProperties);
    }
}
