package main.controllers;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Set;

/**
 * Contest listener, makes log4j initiation
 */
public class Listner implements ServletContextListener { //TODO Это норм что он зеленым подчеркивает?
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PropertyConfigurator.configure(WelcomeController.class.getClassLoader()
                .getResource("log4j.properties"));
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
