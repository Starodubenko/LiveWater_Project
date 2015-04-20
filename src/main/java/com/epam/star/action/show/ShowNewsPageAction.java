package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ArticleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/news")
public class ShowNewsPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowNewsPageAction.class);

    private ActionResult news = new ActionResult("news");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2ArticleDao articleDao = daoManager.getArticleDao();

        request.setAttribute("articles",articleDao.findLastTenArticles());

        daoManager.closeConnection();
        return news;
    }
}

