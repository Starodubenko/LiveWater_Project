package com.epam.star.servlet;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionFactory;
import com.epam.star.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/do/*")
@MultipartConfig
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String actionName = req.getMethod() + req.getPathInfo();
        if (req.getMethod().equals("HEAD")) actionName = "GET" + actionName.substring(actionName.indexOf("/"));//fix HEAD type

        LOGGER.debug("Action name which was obtained in Controller: {}", actionName);

        Action action = ActionFactory.getAction(actionName);

        if (action == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String actionS = actionName.substring(actionName.indexOf("/")+1);
        String banActions = "admin"+"clientInfo"+"createOrder"+"dispatcher"+"personal-cabinet";
        if (banActions.contains(actionS) && req.getSession().getAttribute("user") == null){
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        ActionResult result = null;
        try {
            result = action.execute(req);
        } catch (ActionException e) {
            LOGGER.info("Exception cached during executing of action {}", e);
            throw new ActionException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + "/do/" + result.getView());
            return;
        }

        req.getRequestDispatcher("/WEB-INF/" + result.getView() + ".jsp").forward(req, resp);
    }
}
