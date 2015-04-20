package com.epam.star.action.login;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.entity.Order2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Random;

@MappedAction("GET/logout")
public class LogoutAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutAction.class);
    ActionResult welcome = new ActionResult("welcome", true);
    private static Random rnd = new Random();

    private static final String STATUS_NAME = "cart";
    private static final String SHOPPING_CART = "shoppingCart";
    private static final String USER = "user";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        Order2 cart = (Order2)request.getSession().getAttribute(SHOPPING_CART);
        request.getSession().invalidate();

        request.getSession().setAttribute(SHOPPING_CART,cart);
        return welcome;
    }
}
