package com.epam.star.action.registration;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/translate")
public class GetTranslatedField implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetTranslatedField.class);

    private ActionResult result = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        String value = request.getParameter("value");
        request.setAttribute("message",value);
        return result;
    }
}
