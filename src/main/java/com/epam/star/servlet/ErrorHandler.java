package com.epam.star.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/error")
public class ErrorHandler extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable)
                req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                req.getAttribute("javax.servlet.error.status_code");
        String message = (String)
                req.getAttribute("javax.servlet.error.message");//TODO add properties
        String servletName = (String)
                req.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String)
                req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        LOGGER.error("Status code: {}; ", statusCode, message, requestUri, servletName, throwable);
        req.setAttribute("statusCode", statusCode);
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
    }
}
