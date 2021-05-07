package com.kgyam.spring.annotation.conditionAnnotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author kg yam
 * @date 2021-05-07 14:10
 * @since
 */
public class EnvCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment ();
        return environment.acceptsProfiles ("prod");
    }
}
