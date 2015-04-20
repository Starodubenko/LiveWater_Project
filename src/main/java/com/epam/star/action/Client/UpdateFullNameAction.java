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

@MappedAction("POST/updateFullName")
public class UpdateFullNameAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateFullNameAction.class);
    private ActionResult result = new ActionResult("json");
    private ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        ClientDao clientDao = daoManager.getClientDao();
        Validator validator = new Validator(daoManager);
        JSONObject jsonObject = new JSONObject();

        Client currentUser = (Client) request.getSession().getAttribute("editUser");
        if (currentUser == null) LOGGER.info("User for update of full name is null, {}", currentUser);
        currentUser = setClientNewFullName(request, validator, jsonObject, currentUser);

        if (currentUser != null) {
            daoManager.beginTransaction();
            try {
                clientDao.updateEntity(currentUser);

                daoManager.commit();
                LOGGER.info("User password updated successful, {}", currentUser);
                request.setAttribute("message", "user.fullname.update.successful");
            } catch (Exception e) {
                daoManager.rollback();
                request.setAttribute("message", "user.fullname.update.error");
            } finally {
                daoManager.closeConnection();
            }
        } else {
            LOGGER.info("Full name updating does not passed through the validation, {}", currentUser);
        }
        if (jsonObject.length() > 0) return result;
        return message;
    }

    private Client setClientNewFullName(HttpServletRequest request, Validator validator, JSONObject jsonObject, Client client) {

        boolean firstName = validator.checkUserFirstName(request.getParameter("firstname"));
        boolean lastName = validator.checkUserLastName(request.getParameter("lastname"));
        boolean middleName = validator.checkUserMiddleName(request.getParameter("middlename"));
        try {
            if (validator.isValide() && client != null) {
                client.setFirstName(request.getParameter("firstname"));
                client.setLastName(request.getParameter("lastname"));
                client.setMiddleName(request.getParameter("middlename"));
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
