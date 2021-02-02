package com.kgyam.spring.bean.scope.domain;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author kg yam
 * @date 2021-01-28 16:31
 * @since
 */
@Component(value ="myCommand" )
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Command {

    private long state;

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }
}
