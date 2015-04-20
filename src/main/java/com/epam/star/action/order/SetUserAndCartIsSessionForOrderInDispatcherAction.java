package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;
import com.epam.star.entity.Client;
import com.epam.star.entity.Order2;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/setUserAndCart")
public class SetUserAndCartIsSessionForOrderInDispatcherAction implements Action {

    ActionResult result = new ActionResult("createOrderFormForDispatcher");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2ClientDao clientDao = daoManager.getClientDao();

        int id = Integer.valueOf(request.getParameter("userId"));

        Client user = clientDao.findById(id);

        request.getSession().setAttribute("userForOrder", user);
        request.getSession().setAttribute("shoppingCartForClient", new Order2());
        daoManager.closeConnection();

        return result;
    }
}
