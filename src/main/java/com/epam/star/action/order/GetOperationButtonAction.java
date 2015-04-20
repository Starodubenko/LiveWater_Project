package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/getOperationButton")
public class GetOperationButtonAction implements Action {

    ActionResult result = new ActionResult("getOperationButton");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        request.setAttribute("operation",request.getParameter("operation"));
        request.setAttribute("id",request.getParameter("id"));
        return result;
    }
}
