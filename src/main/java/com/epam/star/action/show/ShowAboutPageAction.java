package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2AboutUsDao;
import com.epam.star.entity.AboutUs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@MappedAction("GET/about")
public class ShowAboutPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowAboutPageAction.class);

    private ActionResult login = new ActionResult("about");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2AboutUsDao aboutUsDao = daoManager.getAboutUsDao();

        List<AboutUs> all = aboutUsDao.findAll();

        request.setAttribute("about", all.get(0));

        daoManager.closeConnection();
        return login;
    }
}

