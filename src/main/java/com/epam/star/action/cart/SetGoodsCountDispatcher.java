package com.epam.star.action.cart;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.entity.Client;
import com.epam.star.entity.Goods;
import com.epam.star.entity.interfaces.ShoppingCart;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;

@MappedAction("GET/set-goods-count-dispatcher")
public class SetGoodsCountDispatcher implements Action{
    private static final Logger LOGGER = LoggerFactory.getLogger(SetGoodsCountDispatcher.class);
    ActionResult json = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2GoodsDao goodsDao = daoManager.getGoodsDao();
        HttpSession session = request.getSession();

        Client user = (Client) request.getSession().getAttribute("userForOrder");

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCartForClient");
        String goods_name = request.getParameter("goods_name");
        int goodsCount = Integer.valueOf(request.getParameter("goods_count"));

        Goods goods = goodsDao.findByGoodsName(goods_name);

        shoppingCart.setGoodsCount(goods,goodsCount);
        session.setAttribute("shoppingCartForClient", shoppingCart);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cost", shoppingCart.getCostByGoodsId(goods.getId()));
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
        jsonObject.put("id", goods.getId());
        request.setAttribute("json",jsonObject);

        daoManager.closeConnection();
        return json;
    }
}
