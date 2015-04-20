package com.epam.star.action.image;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ImageDao;
import com.epam.star.entity.Client;
import com.epam.star.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.epam.star.action.util.ActionUtil.getImageFromRequestPart;

@MappedAction("POST/setImageForUser")
public class setImageForUserAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(setImageForUserAction.class);
    private static final String IMAGE = "image";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        String page = request.getHeader("referer");
        page = page.substring(page.indexOf("/do/")+4);

        ActionResult currentPage = new ActionResult(page,true);

        try {
            daoManager.beginTransaction();

            H2ImageDao imageDao = daoManager.getImageDao();
            ClientDao clientDao = daoManager.getClientDao();
            Image image = getImageFromRequestPart(request, IMAGE);

            Client user = (Client) request.getSession().getAttribute("user");

            imageDao.insert(image);
            Image imageForUser = imageDao.findLastAddedImage();
            if (user.getAvatar() != null) imageDao.deleteEntity(user.getAvatar().intValue());

            user.setAvatar(imageForUser.getId());
            clientDao.updateEntity(user);

            daoManager.commit();
        } catch (Exception e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }

        return currentPage;
    }
}
