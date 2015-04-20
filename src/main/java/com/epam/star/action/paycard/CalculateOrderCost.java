package com.epam.star.action.paycard;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.entity.Goods;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/calculate-order-cost")
public class CalculateOrderCost implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentAction.class);

    private ActionResult createOrder = new ActionResult("createOrder");
    private ActionResult json = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        String goodsName = request.getParameter("goodsname");
        int goodsCount = Integer.valueOf(request.getParameter("goodscount"));

        H2GoodsDao goodsDao = daoManager.getGoodsDao();

        Goods goods = goodsDao.findByGoodsName(goodsName);
        int cost = goods.getPrice().intValue() * goodsCount;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cost",cost);
        request.setAttribute("json", jsonObject);

        daoManager.closeConnection();

        return json;
    }
}
