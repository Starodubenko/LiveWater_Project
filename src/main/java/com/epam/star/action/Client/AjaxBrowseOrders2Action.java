package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao2;
import com.epam.star.entity.Client;
import com.epam.star.entity.Order2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@MappedAction("GET/browseOrders2")
public class AjaxBrowseOrders2Action implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxBrowseOrders2Action.class);
    ActionResult clientOrders = new ActionResult("ajaxOrdersForClientTable");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2OrderDao2 orderDao = daoManager.getOrderDao2();
        Client user = (Client) request.getSession().getAttribute("user");

        List todayOrders = orderDao.findAllByClientIdToday(user.getId());
        List pastOrders = orderDao.findAllByClientIdLastDays(user.getId());
        List<Order2> allOrders = orderDao.findAllByClientId(user.getId());

        request.setAttribute("todayOrders", todayOrders);
        request.setAttribute("pastOrders", pastOrders);
        request.setAttribute("allOrders", allOrders);

        return clientOrders;
    }
}
