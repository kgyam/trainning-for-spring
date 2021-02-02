package com.kgyam.spring.bean.scope.domain;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @author kg yam
 * @date 2021-01-28 16:30
 * @since
 */
@Component
public class CommandManger {

    @Lookup("myCommand")
    public Command createCommand() {
        // notice the Spring API dependency!
        return null;
    }

}
