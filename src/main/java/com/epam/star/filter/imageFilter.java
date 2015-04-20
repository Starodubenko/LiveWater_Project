package com.epam.star.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebFilter("/image/*")
public class imageFilter implements Filter {

    private static int CASHE_AGE = 100000;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        long expiry = new Date().getTime() + CASHE_AGE*1000;

        HttpServletResponse httpResponse = (HttpServletResponse)resp;

        httpResponse.setDateHeader("Expires", expiry);
        httpResponse.setHeader("Cache-Control", "max-age=" + CASHE_AGE);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
