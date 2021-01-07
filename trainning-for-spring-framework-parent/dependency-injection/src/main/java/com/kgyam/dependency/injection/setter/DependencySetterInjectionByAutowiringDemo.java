package com.kgyam.dependency.injection.setter;

import com.kgyam.dependency.injection.domain.UserHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自动绑定模式进行setter注入
 *
 * @author kg yam
 * @date 2021-01-07 16:59
 * @since
 */
public class DependencySetterInjectionByAutowiringDemo {

    private static final String XML_LOCATION = "classpath:META-INF/autowiring-dependency-setter-injection-context.xml";


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext (XML_LOCATION);
        autowiringByName (applicationContext);
        autowiringByType (applicationContext);
    }


    /**
     * autowiringByName
     *
     * @param applicationContext
     */
    static void autowiringByName(ApplicationContext applicationContext) {
        UserHolder userHolder = applicationContext.getBean ("userHolderByName", UserHolder.class);
        System.out.println (userHolder);
        /*
        UserHolder{user=User{name='kgyam', age=18}}
         */
    }

    /**
     * autowiringByType
     *
     * @param applicationContext
     */
    static void autowiringByType(ApplicationContext applicationContext) {
        UserHolder userHolder = applicationContext.getBean ("userHolderByType", UserHolder.class);
        System.out.println (userHolder);
        /*
        UserHolder{user=SuperUser{superId='super_id_123'} User{name='kgyam', age=18}}

        ByType注入的是superUser,因为User类型的bean存在多个，所以就着primary标记的bean。
         */
    }
}
