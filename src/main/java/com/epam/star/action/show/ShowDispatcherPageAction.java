package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Goods;
import com.epam.star.entity.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@MappedAction("GET/dispatcher")
public class ShowDispatcherPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowDispatcherPageAction.class);

    private ActionResult dispatcher = new ActionResult("dispatcher");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        List<Period> periods = daoManager.getPeriodDao().getAllPeriods();
        List<Goods> goods = daoManager.getGoodsDao().getAllGoods();

        request.getSession().setAttribute("entityName", "Client");

        HttpSession session = request.getSession();
        session.setAttribute("periods", periods);
        session.setAttribute("goods", goods);

        daoManager.closeConnection();

        return dispatcher;
    }
}