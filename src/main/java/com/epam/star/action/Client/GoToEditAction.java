package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.*;
import com.epam.star.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@MappedAction("GET/editProfile")
public class GoToEditAction implements Action {

    ActionResult clientEdit = new ActionResult("clientEdit");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2PositionDao positionDao = daoManager.getPositionDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();
        H2ClientDao clientDao = daoManager.getClientDao();
        H2EmployeeDao employeeDao = daoManager.getEmployeeDao();
        H2ImageDao imageDao = daoManager.getImageDao();

        List<Position> positions = positionDao.findAll();
        List<Discount> discounts = discountDao.findAll();

        Client user = null;
        if (request.getParameter("userId") != null)
//        user = clientDao.findById(Integer.valueOf(request.getParameter("userId")));
        user = employeeDao.findById(Integer.valueOf(request.getParameter("userId")));
        if ( user == null) user = (Client) request.getSession().getAttribute("user");
        Image userAvatar = imageDao.findById(user.getAvatar());

        String simpleName = user.getClass().getSimpleName();
        if (simpleName.equals("Employee")) {
            Employee employee = (Employee) user;
            request.getSession().setAttribute("editUser", employee);
        }
        else request.getSession().setAttribute("editUser", user);

        request.setAttribute("positions",positions);
        request.setAttribute("discounts", discounts);
        request.setAttribute("userAvatar", userAvatar);
        daoManager.closeConnection();
        return clientEdit;
    }
}
