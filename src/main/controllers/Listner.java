package main.controllers;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Contest listener, makes log4j initiation
 */
public class Listner implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PropertyConfigurator.configure(Hello.class.getClassLoader()
                .getResource("log4j.properties"));
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
