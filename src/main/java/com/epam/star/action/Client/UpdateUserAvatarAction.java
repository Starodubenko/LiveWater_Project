package com.epam.star.action.Client;

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

@MappedAction("POST/updateUserAvatar")
public class UpdateUserAvatarAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserAvatarAction.class);
    private static final String IMAGE = "image";
    private ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        ClientDao clientDao = daoManager.getClientDao();
        H2ImageDao imageDao = daoManager.getImageDao();

        Client currentUser = (Client) request.getSession().getAttribute("editUser");
        if (currentUser == null) LOGGER.info("User for update an avatar is null, {}", currentUser);

        if (currentUser != null) {
            daoManager.beginTransaction();
            try {
                Image image = getImageFromRequestPart(request, IMAGE);
                imageDao.insert(image);
                Image imageForUser = imageDao.findLastAddedImage();
                if (currentUser.getAvatar() != null) imageDao.deleteEntity(currentUser.getAvatar().intValue());

                currentUser.setAvatar(imageForUser.getId());
                clientDao.updateEntity(currentUser);

                daoManager.commit();
                LOGGER.info("Change user avatar is successful, {}", currentUser);
                request.setAttribute("message", "user.update.avatar.successful");
            } catch (Exception e) {
                daoManager.rollback();
                request.setAttribute("message", "user.update.avatar.error");
            } finally {
                daoManager.closeConnection();
            }
        }
        return message;
    }
}
