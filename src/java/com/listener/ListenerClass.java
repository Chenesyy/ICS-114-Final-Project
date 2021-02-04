package com.listener;

import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerClass implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        Date date = new Date();
        context.setAttribute("date", date);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}