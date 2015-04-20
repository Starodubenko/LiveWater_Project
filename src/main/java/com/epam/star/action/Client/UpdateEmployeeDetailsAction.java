package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Employee;
import com.epam.star.util.Validator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

@MappedAction("POST/updateEmployeeDetails")
public class UpdateEmployeeDetailsAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateEmployeeDetailsAction.class);
    private ActionResult result = new ActionResult("json");
    private ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        ClientDao clientDao = daoManager.getClientDao();
        Validator validator = new Validator(daoManager);
        JSONObject jsonObject = new JSONObject();

        Employee currentUser = (Employee) request.getSession().getAttribute("editUser");
        if (currentUser == null) LOGGER.info("User for update of employee details is null, {}", currentUser);
        currentUser = setEmployeeDetails(request, validator, jsonObject, currentUser);

        if (currentUser != null) {
            daoManager.beginTransaction();
            try {
                clientDao.updateEntity(currentUser);

                daoManager.commit();
                LOGGER.info("User employee details updated successful, {}", currentUser);
                request.setAttribute("message", "user.employee.details.update.successful");
            } catch (Exception e) {
                daoManager.rollback();
                request.setAttribute("message", "user.employee.details.update.error");
            } finally {
                daoManager.closeConnection();
            }
        } else {
            LOGGER.info("Employee details updating does not passed through the validation, {}", currentUser);
        }
        if (jsonObject.length() > 0) return result;
        return message;
    }

    private Employee setEmployeeDetails(HttpServletRequest request, Validator validator, JSONObject jsonObject, Employee client) {

        boolean idCard = validator.checkUserIdentityCard(request.getParameter("identitycard"));
        boolean workbook = validator.checkUserWorkBook(request.getParameter("workbook"));
        boolean rnn = validator.checkUserRNN(request.getParameter("rnn"));
        boolean sik = validator.checkUserSIK(request.getParameter("sik"));
        try {
            if (validator.isValide() && client != null) {
                client.setIdentityCard(request.getParameter("identitycard"));
                client.setWorkBook(request.getParameter("workbook"));
                client.setRNN(request.getParameter("rnn"));
                client.setSIK(request.getParameter("sik"));
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
