package com.epam.star.action.article;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ArticleDao;
import com.epam.star.dao.util.EntityFromParameters.GetEntityUpdate;
import com.epam.star.dao.util.UtilDao;
import com.epam.star.entity.AbstractEntity;
import com.epam.star.entity.Article;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


@MappedAction("POST/save-article")
public class SaveArticle implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveArticle.class);
    GetEntityUpdate entityForUpdate = new GetEntityUpdate();
    ActionResult json = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2ArticleDao articleDao = daoManager.getArticleDao();
        AbstractEntity oldArticle = articleDao.findById(Integer.parseInt(request.getParameter("artId")));

        JSONObject jsonObject = new JSONObject();

        if (oldArticle != null) {
            daoManager.beginTransaction();
            try {
                Article newArticle = (Article) entityForUpdate.getByEntityName(request, daoManager, "article", oldArticle);

                articleDao.updateEntity(newArticle);

                daoManager.commit();
                LOGGER.info("Update article was successful, {}", newArticle);
                jsonObject.put("message","article.update.successful");
//                request.setAttribute("message", "article.update.successful");
            } catch (Exception e) {
                daoManager.rollback();
//                request.setAttribute("message", "article.update.error");
                jsonObject.put("message","article.update.error");
            } finally {
                daoManager.closeConnection();
            }
        } else {

        }

        request.setAttribute("json",jsonObject);

        return json;
    }
}
