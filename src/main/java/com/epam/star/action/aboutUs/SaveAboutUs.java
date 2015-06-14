package com.epam.star.action.aboutUs;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2AboutUsDao;
import com.epam.star.dao.H2dao.H2ArticleDao;
import com.epam.star.dao.util.EntityFromParameters.GetEntityUpdate;
import com.epam.star.entity.AboutUs;
import com.epam.star.entity.AbstractEntity;
import com.epam.star.entity.Article;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("POST/save-about")
public class SaveAboutUs implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveAboutUs.class);
    GetEntityUpdate entityForUpdate = new GetEntityUpdate();
    ActionResult json = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2AboutUsDao aboutUsDao = daoManager.getAboutUsDao();
        AboutUs oldAbout = aboutUsDao.findById(Integer.parseInt(request.getParameter("aboutId")));

        JSONObject jsonObject = new JSONObject();

        if (oldAbout != null) {
            daoManager.beginTransaction();
            try {
                AboutUs newAbout = (AboutUs) entityForUpdate.getByEntityName(request, daoManager, "about", oldAbout);

                aboutUsDao.updateEntity(newAbout);

                daoManager.commit();
                LOGGER.info("Update about was successful, {}", newAbout);
                jsonObject.put("message","about.update.successful");
            } catch (Exception e) {
                daoManager.rollback();
                jsonObject.put("message","about.update.error");
            } finally {
                daoManager.closeConnection();
            }
        } else {

        }

        request.setAttribute("json",jsonObject);

        return json;
    }
}
