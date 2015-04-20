package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;
import com.epam.star.dao.H2dao.H2OrderDao2;
import com.epam.star.dao.util.PaginatedList;
import com.epam.star.dao.util.Pagination;
import com.epam.star.entity.Client;
import com.epam.star.entity.Order2;
import com.epam.star.entity.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@MappedAction("GET/completion-order")
public class ShowCompletionOrderPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowCompletionOrderPageAction.class);

    private ActionResult completion = new ActionResult("completionOrder");
    private ActionResult shoppingCart = new ActionResult("shoppingCart",true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        Order2 cart = (Order2)request.getSession().getAttribute("shoppingCart");
        if (cart.getGoodsCount() < 1) return shoppingCart;

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2OrderDao2 orderDao2 = daoManager.getOrderDao2();
        H2ClientDao clientDao = daoManager.getClientDao();

        Client user = (Client)request.getSession().getAttribute("user");
        Pagination pagination = new Pagination();
        PaginatedList<Order2> orders = pagination.paginationEntityy(request, orderDao2, null);

        List<Period> periods = daoManager.getPeriodDao().getAllPeriods();
        request.setAttribute("periods", periods);
        request.setAttribute("ordersPaginatedList",orders);

        if (user != null)
        request.setAttribute("clientBalance", clientDao.findById(user.getId()).getVirtualBalance());

        daoManager.closeConnection();

        return completion;
    }
}

