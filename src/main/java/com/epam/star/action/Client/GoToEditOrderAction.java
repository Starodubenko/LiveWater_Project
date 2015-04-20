package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao2;
import com.epam.star.dao.util.UtilDao;
import com.epam.star.entity.Order2;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/editOrder")
public class GoToEditOrderAction implements Action {

    private UtilDao utilDao = new UtilDao();

    ActionResult orderEdit = new ActionResult("viewEditOrder");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2OrderDao2 orderDao = daoManager.getOrderDao2();

        Order2 order = null;
        Integer id = utilDao.getIntValue(request.getParameter("id"));
        if (id != null) order = orderDao.findById(id);

        request.getSession().setAttribute("editOrder", order);
        daoManager.closeConnection();
        return orderEdit;
    }
}
