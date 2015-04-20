package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2DiscountDao;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("POST/updateDiscount")
public class UpdateDiscountAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateDiscountAction.class);
    private ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        ClientDao clientDao = daoManager.getClientDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();

        Client currentUser = (Client) request.getSession().getAttribute("editUser");
        if (currentUser == null) LOGGER.info("User for update of discount is null, {}", currentUser);

        if (currentUser != null) {
            daoManager.beginTransaction();
            try {
                currentUser.setDiscount(discountDao.findByName(request.getParameter("discount")));
                clientDao.updateEntity(currentUser);

                daoManager.commit();
                LOGGER.info("User position updated successful, {}", currentUser);
                request.setAttribute("message", "user.discount.update.successful");
            } catch (Exception e) {
                daoManager.rollback();
                request.setAttribute("message", "user.discount.update.error");
            } finally {
                daoManager.closeConnection();
            }
        }
        return message;
    }
}
