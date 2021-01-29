package com.kgyam.spring.bean.scope.singleton;

import com.kgyam.spring.bean.scope.domain.Command;
import com.kgyam.spring.bean.scope.domain.CommandManger;
import com.kgyam.spring.bean.scope.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import java.util.Random;

/**
 * 单例bean中注入prototype bean
 *
 * @author kg yam
 * @date 2021-01-28 15:54
 * @since
 */
@Configuration
@ComponentScan("com.kgyam.spring.bean.scope")
public class SingletonWithPrototypeDiDemo {

    @Autowired
    @Qualifier("employee")
    private Employee employee;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (SingletonWithPrototypeDiDemo.class);
//        applicationContext.scan ("com.kgyam.bean.scope");
        applicationContext.refresh ();

        for (int i = 0; i < 5; i++) {
            SingletonWithPrototypeDiDemo demo = applicationContext.getBean (SingletonWithPrototypeDiDemo.class);
            CommandManger manger = applicationContext.getBean (CommandManger.class);
            Employee e = demo.employee;
            Command command = manger.createCommand ();
            System.out.println ("field inject employee:" + e);
            System.out.println (command);
        }
        applicationContext.close ();
    }


    @Bean(name = "employee")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Employee employee() {
        Employee employee = new Employee ();
        employee.setId (new Random ().nextLong ());
        return employee;
    }

}
