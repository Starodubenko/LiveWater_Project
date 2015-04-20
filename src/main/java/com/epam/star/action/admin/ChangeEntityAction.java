package com.epam.star.action.admin;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.Dao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.util.EntityFromParameters.GetEntityFields;
import com.epam.star.dao.util.EntityFromParameters.GetRefFieldOptions;
import com.epam.star.dao.util.PaginatedList;
import com.epam.star.dao.util.Pagination;
import com.epam.star.dao.util.UtilDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@MappedAction("GET/changeEntity")
public class ChangeEntityAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeEntityAction.class);
    private static final UtilDao UTIL_DAO = new UtilDao();

    ActionResult genericActionResultBlock = new ActionResult("genericEntityBlock");
    ActionResult genericActionResultTable = new ActionResult("genericEntityTable");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        String entityNameForJSP = UTIL_DAO.getString("entityName", request);
        String entityName = entityNameForJSP.substring(0,entityNameForJSP.length()-1);
        Integer page = UTIL_DAO.getIntValue("page", request);
        Integer rows = UTIL_DAO.getIntValue("rows", request);

//        Map<String, String[]> parameterMap = request.getParameterMap();

        try(DaoManager daoManager = DaoFactory.getInstance().getDaoManager()){
            Map<String, String> fieldsValueMap = GetEntityFields.getByEntityName(request, daoManager, entityName);

            Dao dao = daoManager.getDao(entityName);
            Pagination pagination = new Pagination();
            PaginatedList entities = pagination.paginationEntityy(request, dao, fieldsValueMap);

            Map<String, List> entitysNames = GetRefFieldOptions.getByEntityName(daoManager, entityName);

            if (entitysNames != null)
            for (Map.Entry<String, List> entry : entitysNames.entrySet()) {
                if (request.getAttribute(entry.getKey()) == null)
                    request.setAttribute(entry.getKey(), entry.getValue());
            }

            request.setAttribute("paginatedList", entities);
            request.setAttribute("entityName", entityNameForJSP);

            if (!entityName.equals(request.getSession().getAttribute("entityName"))) request.setAttribute("page", "1");
            if (UTIL_DAO.getString("page", request) == null) request.setAttribute("page", "1");
//
//            request.getSession().setAttribute("entityName", entityName);
//            request.getSession().setAttribute("page", request.getParameter("page"));
//            request.getSession().setAttribute("rows", request.getParameter("rows"));
        } catch (Exception e) {
            throw new ActionException(e);
        }

        if (request.getParameter("changePage") != null) return genericActionResultTable;
        else return genericActionResultBlock;
    }
}
