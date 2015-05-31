package com.epam.star.action.goods;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/about-goods")
public class GoToAboutGoods implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoToAboutGoods.class);

    private ActionResult aboutGoods = new ActionResult("aboutGoods");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2GoodsDao goodsDao = daoManager.getGoodsDao();

        Integer id = Integer.valueOf(request.getParameter("id"));

        assert id != null;
        Goods goods = goodsDao.findById(id);
        goods.setCharacteristics(goodsDao.getGoodsCharacteristics(goods.getId()));
        request.setAttribute("goods", goods);

        daoManager.closeConnection();
        return aboutGoods;
    }
}

