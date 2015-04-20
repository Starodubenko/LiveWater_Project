package com.epam.star.action.image;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.epam.star.action.util.ActionUtil.getImageFromRequestPart;

@MappedAction("POST/ajaxShowCurrentAvatar")
public class AjaxShowImageAfterSelectFileAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxShowImageAfterSelectFileAction.class);
    private static final String IMAGE = "image";

    ActionResult currentImage = new ActionResult("currentImage");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        Image image = getImageFromRequestPart(request, IMAGE);

        request.setAttribute("currentAvatar", image);

        return currentImage;
    }
}
