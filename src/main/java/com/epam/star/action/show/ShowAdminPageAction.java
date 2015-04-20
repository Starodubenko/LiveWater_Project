package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.Dao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.util.EntityFromParameters.GetRefFieldOptions;
import com.epam.star.dao.util.PaginatedList;
import com.epam.star.dao.util.Pagination;
import com.epam.star.dao.util.UtilDao;
import com.epam.star.entity.UtilEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@MappedAction("GET/admin")
public class ShowAdminPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowAdminPageAction.class);
    private static final UtilDao UTIL_DAO = new UtilDao();

    private ActionResult admin = new ActionResult("admin");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        String entityName = UTIL_DAO.getString("entityName",request);
        if (entityName != null) {
            DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
            Dao dao = daoManager.getDao(entityName);

            Pagination pagination = new Pagination();
            PaginatedList paginatedList = pagination.paginationEntityy(request, dao, null);

            request.setAttribute("paginatedList", paginatedList);
            request.setAttribute("entityName", entityName);

            Map<String, List> entitysNames = GetRefFieldOptions.getByEntityName(daoManager, entityName);

            if (entitysNames != null)
                for (Map.Entry<String, List> entry : entitysNames.entrySet()) {
                    if (request.getAttribute(entry.getKey()) == null)
                        request.setAttribute(entry.getKey(), entry.getValue());
                }


            daoManager.closeConnection();
        }

        if (request.getSession().getAttribute("entitiesNames") == null)
        request.getSession().setAttribute("entitiesNames", UtilEntity.getEntityNames());

        return admin;
    }
}
