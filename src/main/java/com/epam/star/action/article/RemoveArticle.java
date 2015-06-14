package com.epam.star.action.article;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ArticleDao;
import com.epam.star.dao.util.EntityFromParameters.GetEntityUpdate;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("POST/remove-article")
public class RemoveArticle implements Action{

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveArticle.class);
    GetEntityUpdate entityForUpdate = new GetEntityUpdate();
    ActionResult json = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        JSONObject jsonObject = new JSONObject();
        H2ArticleDao articleDao = daoManager.getArticleDao();
        Integer artId = Integer.parseInt(request.getParameter("artId"));

        daoManager.beginTransaction();
        try {
            articleDao.deleteEntity(artId);

            daoManager.commit();
            LOGGER.info("Mark article as deleted was successful, {}", artId);
//            request.setAttribute("message", "article.delete.successful");
            jsonObject.put("message","article.delete.successful");
        } catch (Exception e) {
            daoManager.rollback();
//            request.setAttribute("message", "article.delete.error");
            jsonObject.put("message","article.delete.error");
        } finally {
            daoManager.closeConnection();
        }

        request.setAttribute("json",jsonObject);
        return json;
    }
}
