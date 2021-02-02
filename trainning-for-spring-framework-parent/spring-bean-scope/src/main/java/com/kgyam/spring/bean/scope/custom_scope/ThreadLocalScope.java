package com.kgyam.spring.bean.scope.custom_scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 *根据线程作为作用域的Scope实现
 *
 * @author kg yam
 * @date 2021-01-29 11:25
 * @since
 */
public class ThreadLocalScope implements Scope {


    public static final String SCOPE_NAME = "thread-local";

    private final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<Map<String, Object>> () {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<> ();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map map = getContext ();
        Object o = map.get (name);
        if (o == null) {
            o = objectFactory.getObject ();
            map.put (name, o);
        }
        return o;
    }

    @Override
    public Object remove(String name) {
        Map map = getContext ();
        return map.remove (name);
    }


    private Map<String, Object> getContext() {
        return THREAD_LOCAL.get ();
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        System.out.println ("registerDestructionCallback");
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return String.valueOf (Thread.currentThread ().getId ());
    }
}
