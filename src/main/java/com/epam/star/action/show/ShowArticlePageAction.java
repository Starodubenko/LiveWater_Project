package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ArticleDao;
import com.epam.star.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/article")
public class ShowArticlePageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowArticlePageAction.class);

    private ActionResult article = new ActionResult("article");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2ArticleDao articleDao = daoManager.getArticleDao();

        if (request.getParameter("artId") != null) {
            Integer artId = Integer.valueOf(request.getParameter("artId"));
            Article art = articleDao.findById(artId);

            request.setAttribute("article", art);
        }

        daoManager.closeConnection();
        return article;
    }
}

