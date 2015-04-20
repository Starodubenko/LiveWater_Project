package com.epam.star.action.cart;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.entity.Goods;
import com.epam.star.entity.interfaces.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@MappedAction("POST/addGoodsDispatcher")
public class AddGoodsDispatcher implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddGoodsDispatcher.class);

    ActionResult result = new ActionResult("orderedGoodsRowForDispatcher");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2GoodsDao goodsDao = daoManager.getGoodsDao();
        HttpSession session = request.getSession();

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCartForClient");

        String goodsName = request.getParameter("goods_name");
        Goods goods = goodsDao.findByGoodsName(goodsName);

        shoppingCart.addGoods(goods);
        session.setAttribute("shoppingCartForClient", shoppingCart);

        daoManager.closeConnection();

        return result;
    }
}
