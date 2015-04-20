package com.epam.star.action;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface Action {
    ActionResult execute(HttpServletRequest request) throws ActionException, SQLException;
}
