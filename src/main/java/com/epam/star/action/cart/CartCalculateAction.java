package com.epam.star.action.cart;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Client;
import com.epam.star.entity.interfaces.ShoppingCart;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;

@MappedAction("POST/cartCalculate")
public class CartCalculateAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartCalculateAction.class);

    ActionResult result = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        HttpSession session = request.getSession();

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCartForClient");
        Client user = (Client) session.getAttribute("userForOrder");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("total", shoppingCart.getTotalSum());
        if (user != null) {
            jsonObject.put("discount", user.getDiscount().getPercentage());
            if (user.getDiscount().getPercentage() > 0) {
                BigDecimal coeff = new BigDecimal(user.getDiscount().getPercentage()).divide(new BigDecimal(100));
                BigDecimal totalWithDiscount = coeff.multiply(shoppingCart.getTotalSum());
                jsonObject.put("totalWithDiscount", totalWithDiscount.intValue());
            }
            else jsonObject.put("totalWithDiscount", shoppingCart.getTotalSum().intValue());
        }
        else {
            jsonObject.put("discount", 0);
            jsonObject.put("totalWithDiscount", shoppingCart.getTotalSum().intValue());
        }

        request.setAttribute("json",jsonObject);

        daoManager.closeConnection();

        return result;
    }
}
