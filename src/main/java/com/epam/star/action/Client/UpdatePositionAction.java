package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2PositionDao;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("POST/updatePosition")
public class UpdatePositionAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePositionAction.class);
    private ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        ClientDao clientDao = daoManager.getClientDao();
        H2PositionDao positionDao = daoManager.getPositionDao();

        Client currentUser = (Client) request.getSession().getAttribute("editUser");
        if (currentUser == null) LOGGER.info("User for update of position is null, {}", currentUser);

        if (currentUser != null) {
            daoManager.beginTransaction();
            try {
                currentUser.setRole(positionDao.findByPositionName(request.getParameter("position_name")));
                clientDao.updateEntity(currentUser);

                daoManager.commit();
                LOGGER.info("User position updated successful, {}", currentUser);
                request.setAttribute("message", "user.position.update.successful");
            } catch (Exception e) {
                daoManager.rollback();
                request.setAttribute("message", "user.position.update.error");
            } finally {
                daoManager.closeConnection();
            }
        }
        return message;
    }
}
