package com.epam.star.listener;

import com.epam.star.dao.H2dao.DaoFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener{

    public ContextListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("daoFactory", DaoFactory.getInstance());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        DaoFactory dbManager = (DaoFactory) servletContext.getAttribute("daoFactory");
        dbManager.destroy();
    }
}
