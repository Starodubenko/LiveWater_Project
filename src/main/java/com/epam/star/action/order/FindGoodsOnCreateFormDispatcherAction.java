package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.entity.Goods;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@MappedAction("GET/findGoodsOnCreateFormForDispatcher")
public class FindGoodsOnCreateFormDispatcherAction implements Action {

    ActionResult result = new ActionResult("goodsRowForDispatcher");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2GoodsDao goodsDao = daoManager.getGoodsDao();

        String searchGoods = request.getParameter("searchGoods");
        List<Goods> byGoodsNameRange = goodsDao.findByGoodsNameRange(searchGoods);

        request.setAttribute("goods", byGoodsNameRange);

        daoManager.closeConnection();

        return result;
    }
}
