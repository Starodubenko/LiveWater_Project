package com.epam.star.action.admin;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/entityOrderBy")
public class SelectEntityOrderBy implements Action {

    ActionResult entityOrderBy = new ActionResult("genericEntityOrderBy");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        String entityName = request.getParameter("entityName");
        request.setAttribute("entityName",entityName);
        return entityOrderBy;
    }
}
