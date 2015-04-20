package com.epam.star.action.admin;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.Dao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.util.EntityFromParameters.GetEntity;
import com.epam.star.dao.util.UtilDao;
import com.epam.star.entity.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("POST/save-entity")
public class SaveEntityAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveEntityAction.class);
    private static final UtilDao UTIL_DAO = new UtilDao();

    ActionResult reviewentity = new ActionResult("changeEntity",true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        try (DaoManager daoManager = DaoFactory.getInstance().getDaoManager()) {
            String entityName = UTIL_DAO.getString("entityName",request);

            Dao dao = daoManager.getDao(entityName);
            AbstractEntity entity = GetEntity.getByEntityName(request, daoManager, entityName);
            try {
                daoManager.beginTransaction();
                dao.insert(entity);

                LOGGER.error("Entity saved successful{}", entity);
                daoManager.commit();
            } catch (Exception e) {
                LOGGER.error("During saving entity an error was occurred{}", entity);
                daoManager.rollback();
            }
            request.getSession().setAttribute("entityName", entityName);
            request.getSession().setAttribute("page", request.getParameter("page"));
            request.getSession().setAttribute("rows", request.getParameter("rows"));
        } catch (Exception e){
            LOGGER.error("During getting an instance of DaoManager has occurred an error{}", e);
        }

        return reviewentity;
    }
}
