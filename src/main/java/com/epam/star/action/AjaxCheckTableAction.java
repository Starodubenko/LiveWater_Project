package com.epam.star.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/checkTable")
public class AjaxCheckTableAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxCheckTableAction.class);

    ActionResult tableRow = new ActionResult("ajaxSearchRow");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        String name = request.getParameter("entityName");
        request.setAttribute("entityName", name);

        return tableRow;
    }
}
