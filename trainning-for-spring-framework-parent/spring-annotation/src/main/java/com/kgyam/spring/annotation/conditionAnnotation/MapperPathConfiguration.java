package com.kgyam.spring.annotation.conditionAnnotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Conditional注解，条件化注解。
 * 该注解根据注解中的{@link org.springframework.context.annotation.Condition}的实现类，实现类可看{@link EnvCondition}
 * 判断是否实例化该Bean对象
 * 当前的这种案例下可以用Conditional注解代替{@link org.springframework.context.annotation.Profile}
 *
 *
 * 这个案例中需要注意，在相同beanName的情况下，如果第一个beanName成功实例化，第二个就不会实例化了。
 * 所以顺序是非常重要的，或者这两个Bean的都需要通过条件注解判断是否该实例化
 *
 * 该类为条件注解重要逻辑类
 * {@link org.springframework.context.annotation.ConditionEvaluator}
 *
 * @author kg yam
 * @date 2021-05-07 14:07
 * @since
 */

@Configuration
public class MapperPathConfiguration {


    //    @Profile("prod")
    @Conditional({EnvCondition.class})
    @Bean("mapperPath")
    public String prodPath() {
        return "prod/mapper/*.xml";
    }

    //    @Profile("dev")
    @Bean("mapperPath")
    public String devPath() {
        return "dev/mapper/*.xml";
    }
}
