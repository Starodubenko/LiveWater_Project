package com.epam.star.action.admin;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.Dao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.util.EntityFromParameters.GetEntityUpdate;
import com.epam.star.dao.util.UtilDao;
import com.epam.star.entity.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("POST/update-entity")
public class UpdateEntityAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateEntityAction.class);
    private static final UtilDao UTIL_DAO = new UtilDao();

//    ActionResult reviewentity = new ActionResult("changeEntity",true);
    ActionResult reviewentity = new ActionResult("json");
    GetEntityUpdate entityForUpdate = new GetEntityUpdate();

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        try (DaoManager daoManager = DaoFactory.getInstance().getDaoManager()) {
            String entityNameForJSP = UTIL_DAO.getString("entityName", request);
            String entityName = entityNameForJSP.substring(0, entityNameForJSP.length() - 1);

            Dao dao = daoManager.getDao(entityName);

            AbstractEntity entity = dao.findById(Integer.valueOf(request.getParameter("id")));
            AbstractEntity newEntity = entityForUpdate.getByEntityName(request, daoManager, entityName, entity);

            try {
                daoManager.beginTransaction();
                dao.updateEntity(newEntity);

                LOGGER.info("Entity updated successful{}", entity);
                daoManager.commit();
            } catch (Exception e) {
                LOGGER.error("During updating entity an error was occurred{}", entity);
                daoManager.rollback();
            }
        } catch (Exception e){
            LOGGER.error("During getting an instance of DaoManager has occurred an error{}", e);
        }

        return reviewentity;
    }
}
