package com.epam.star.action.article;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ArticleDao;
import com.epam.star.dao.util.EntityFromParameters.GetEntity;
import com.epam.star.dao.util.EntityFromParameters.GetEntityUpdate;
import com.epam.star.entity.AbstractEntity;
import com.epam.star.entity.Article;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("POST/add-article")
public class AddArticle implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddArticle.class);
    GetEntityUpdate entityForUpdate = new GetEntityUpdate();
    ActionResult json = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2ArticleDao articleDao = daoManager.getArticleDao();
        Article newArticle = (Article) GetEntity.getByEntityName(request, daoManager, "article");

        JSONObject jsonObject = new JSONObject();

        if (newArticle != null) {
            daoManager.beginTransaction();
            try {
                articleDao.insert(newArticle);

                daoManager.commit();
                LOGGER.info("Insert article was successful, {}", newArticle);
//                request.setAttribute("message", "article.insert.successful");
                jsonObject.put("message","article.insert.successful");
            } catch (Exception e) {
                daoManager.rollback();
//                request.setAttribute("message", "article.insert.error");
                jsonObject.put("message","article.insert.error");
            } finally {
                daoManager.closeConnection();
            }
        } else {
            request.setAttribute("message", "article.insert.error.some.field.empty");
        }

        request.setAttribute("json",jsonObject);

        return json;
    }
}
