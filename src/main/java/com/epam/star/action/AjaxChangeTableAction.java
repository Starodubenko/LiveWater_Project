package com.epam.star.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/changeTableAction")
public class AjaxChangeTableAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxChangeTableAction.class);

    ActionResult genericTable = new ActionResult("ajaxGenericTable");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        String name = request.getParameter("tableName");
        request.getSession().setAttribute("entityName", name);

        return genericTable;
    }
}
