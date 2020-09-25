package com.bridgelabz.usermanagement.configuration;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-configuration");
        PropertyConfigurator.configure(getClass().getClassLoader().getResourceAsStream(log4jConfigFile));
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
