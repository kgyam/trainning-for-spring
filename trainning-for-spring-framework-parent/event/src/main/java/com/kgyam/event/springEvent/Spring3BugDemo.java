package com.kgyam.event.springEvent;

import com.kgyam.event.customizedSpringEvent.MyApplicationEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 重现Spring3的事件bug
 * @author kg yam
 * @date 2021-04-27 14:42
 * @since
 */
public class Spring3BugDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext ();
        applicationContext.register (BugInvoke.class);

        applicationContext.addApplicationListener (new ApplicationListener () {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println (event);
            }
        });

        applicationContext.refresh ();
        applicationContext.close ();
    }


    static class BugInvoke implements BeanFactoryPostProcessor, ApplicationContextAware {

        private ApplicationContext applicationContext;


        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
            applicationContext.publishEvent (new MyApplicationEvent ("hello world"));
        }
    }
}
