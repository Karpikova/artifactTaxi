package main.beans;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 26.04.2017
 * 
 * Karpikova
 */
public class ProfilingBeanHandler implements BeanPostProcessor{
    private Map<String, Class> map = new HashMap<String, Class>();

    static {
        PropertyConfigurator.configure(ProfilingBeanHandler.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(ProfilingBeanHandler.class);


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass(); ///???
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        System.out.println("qw");
        Class beanClass = map.get(beanName);
        if (beanClass != null){
            return Proxy.newProxyInstance(beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    new InvocationHandler() {
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            long before = System.nanoTime();
                            Object ob = method.invoke(bean, args); //why bean&&&
                            long after = System.nanoTime();
                            System.out.println(after-before);
                            return ob;
                        }
                    });
        }
        return bean;
    }
}
