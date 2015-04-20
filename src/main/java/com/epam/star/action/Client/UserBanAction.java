package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("POST/userBan")
public class UserBanAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBanAction.class);
    private ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        ClientDao clientDao = daoManager.getClientDao();

        Client currentUser = (Client) request.getSession().getAttribute("editUser");
        if (currentUser == null) LOGGER.info("User for ban is null, {}", currentUser);

        if (currentUser != null) {
            daoManager.beginTransaction();
            try {
                currentUser.setDeleted(Boolean.valueOf(request.getParameter("deleted")));
                clientDao.updateEntity(currentUser);

                daoManager.commit();
                LOGGER.info("User banned successful, {}", currentUser);
                if (currentUser.isDeleted())
                request.setAttribute("message", "user.ban.successful");
                else request.setAttribute("message", "user.allow.successful");
            } catch (Exception e) {
                daoManager.rollback();
                if (currentUser.isDeleted())
                request.setAttribute("message", "user.ban.error");
                else request.setAttribute("message", "user.allow.error");
            } finally {
                daoManager.closeConnection();
            }
        }
        return message;
    }
}
