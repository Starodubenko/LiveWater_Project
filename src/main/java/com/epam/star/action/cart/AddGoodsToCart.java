package com.epam.star.action.cart;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.entity.Goods;
import com.epam.star.entity.Order2;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@MappedAction("POST/addGoods")
public class AddGoodsToCart implements Action{
    private static final Logger LOGGER = LoggerFactory.getLogger(AddGoodsToCart.class);

    ActionResult json = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        try{
            daoManager.beginTransaction();
            H2GoodsDao goodsDao = daoManager.getGoodsDao();
            HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();

            Order2 shoppingCart = (Order2) session.getAttribute("shoppingCart");
            int goodsId = Integer.parseInt(request.getParameter("id"));

            Goods goods = goodsDao.findById(goodsId);

            shoppingCart.addGoods(goods);
            session.setAttribute("shoppingCart", shoppingCart);
            jsonObject.put("addSuccess", true);
            jsonObject.put("currentGoodsCount", shoppingCart.getGoodsCount());
            jsonObject.put("total", shoppingCart.getTotalSum());
            request.setAttribute("json",jsonObject);
            daoManager.commit();
        }catch (Exception e){
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }
        return json;
    }
}
