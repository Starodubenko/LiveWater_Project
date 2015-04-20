package com.epam.star.action.goods;

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

@MappedAction("GET/editGoods")
public class GoToEditAction implements Action {

    ActionResult clientEdit = new ActionResult("goodsEdit");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2GoodsDao goodsDao = daoManager.getGoodsDao();

//        List<Position> positions = positionDao.findAll();
//        List<Discount> discounts = discountDao.findAll();

        Goods goods = null;
        if (request.getParameter("goodsId") != null)
        goods = goodsDao.findById(Integer.valueOf(request.getParameter("goodsId")));
        request.getSession().setAttribute("editGoods", goods);
        if (goods != null)
        request.setAttribute("goodsImage", goods.getImage());
        request.setAttribute("purpose",request.getParameter("purpose"));
        daoManager.closeConnection();
        return clientEdit;
    }
}
