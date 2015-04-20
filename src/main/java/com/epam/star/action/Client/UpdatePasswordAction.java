package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Client;
import com.epam.star.util.Validator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

@MappedAction("POST/updatePassword")
public class UpdatePasswordAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePasswordAction.class);
    private ActionResult result = new ActionResult("json");
    private ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        ClientDao clientDao = daoManager.getClientDao();
        Validator validator = new Validator(daoManager);
        JSONObject jsonObject = new JSONObject();

        Client currentUser = (Client) request.getSession().getAttribute("editUser");
        if (currentUser == null) LOGGER.info("User for update of password is null, {}", currentUser);
        currentUser = setClientNewPassword(request, validator, jsonObject, currentUser);

        if (currentUser != null) {
            daoManager.beginTransaction();
            try {
                clientDao.updateEntity(currentUser);

                daoManager.commit();
                LOGGER.info("User password updated successful, {}", currentUser);
                request.setAttribute("message", "user.password.update.successful");
            } catch (Exception e) {
                daoManager.rollback();
                request.setAttribute("message", "user.password.update.error");
            } finally {
                daoManager.closeConnection();
            }
        } else {
            LOGGER.info("Password updating does not passed through the validation, {}", currentUser);
        }
        if (jsonObject.length() > 0) return result;
        return message;
    }

    private Client setClientNewPassword(HttpServletRequest request, Validator validator, JSONObject jsonObject, Client client) {

        boolean oldPassword = validator.checkOldPassword(request.getParameter("oldpassword"), client);
        String oldPass = null;
        if (oldPassword) oldPass = request.getParameter("oldpassword");
        boolean newPassword = validator.checkUserPassword(request.getParameter("password"), oldPass);
        boolean confirmPassword = validator.checkConfirmUserPassword(request.getParameter("password"), request.getParameter("confirmpassword"));
        try {
            if (validator.isValide() && client != null) {
                client.setPassword(request.getParameter("password"));
                return client;
            } else {
                Map<String, String> invalidFields = validator.getInvalidFields();
                for (Map.Entry<String, String> field : invalidFields.entrySet()) {
                    jsonObject.put(field.getKey(), field.getValue());
                }
            }
        } finally {
            request.setAttribute("json", jsonObject);
        }
        return null;
    }
}
