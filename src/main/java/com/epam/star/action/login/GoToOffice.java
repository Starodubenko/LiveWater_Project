package com.epam.star.action.login;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/office")
public class GoToOffice implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoToOffice.class);

    ActionResult dispatcher = new ActionResult("dispatcher", true);
    ActionResult admin = new ActionResult("admin", true);
    ActionResult welcome = new ActionResult("welcome", true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        PositionDao positionDao = daoManager.getPositionDao();

        Client user = (Client) request.getSession().getAttribute("user");
        if (user.getRole().equals(positionDao.findByPositionName("Dispatcher"))) return dispatcher;
        if (user.getRole().equals(positionDao.findByPositionName("Admin"))) return admin;

        daoManager.closeConnection();

        return welcome;
    }
}
