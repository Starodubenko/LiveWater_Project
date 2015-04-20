package com.epam.star.action.admin;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.util.EntityFromParameters.GetRefFieldOptions;
import com.epam.star.dao.util.UtilDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@MappedAction("GET/entityFilter")
public class SelectEntityFilter implements Action {

    private DaoManager daoManager;
    private static final UtilDao UTIL_DAO = new UtilDao();

    ActionResult entityFilter = new ActionResult("genericEntityFilter");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        String entityNameForJSP = UTIL_DAO.getString("entityName", request);
        String entityName = entityNameForJSP.substring(0, entityNameForJSP.length() - 1);

        daoManager = DaoFactory.getInstance().getDaoManager();
        try{
            Map<String, List> entitysNames = GetRefFieldOptions.getByEntityName(daoManager, entityName);
            if (entitysNames != null)
                for (Map.Entry<String, List> entry : entitysNames.entrySet()) {
                    if (request.getAttribute(entry.getKey()) == null)
                        request.setAttribute(entry.getKey(), entry.getValue());
                }

            request.setAttribute("entityName", entityNameForJSP);
        } catch (Exception e) {
            throw new ActionException(e);
        }
        daoManager.closeConnection();
        return entityFilter;
    }
}
