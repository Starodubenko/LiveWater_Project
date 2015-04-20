package com.epam.star.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/do/*")
public class UrlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter((HttpServletRequest) req, (HttpServletResponse) resp, chain);
    }

    private void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String pathInfo = req.getRequestURI().substring(req.getContextPath().length());

        if (pathInfo.startsWith("/style") || pathInfo.startsWith("/image")) {
            chain.doFilter(req, resp);
            return;
        }

        req.getRequestDispatcher(pathInfo).forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}
