package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.dao.util.PaginatedList;
import com.epam.star.dao.util.Pagination;
import com.epam.star.entity.Cart;
import com.epam.star.entity.Goods;
import com.epam.star.entity.Order2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/services")
public class ShowServicesPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowServicesPageAction.class);

    private ActionResult services = new ActionResult("services");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2GoodsDao goodsDao = daoManager.getGoodsDao();
        Pagination pagination = new Pagination();

        PaginatedList<Goods> goods = pagination.paginationEntityy(request, goodsDao, null);
        Cart shoppingCart = (Cart) request.getSession().getAttribute("shoppingCart");
        if (shoppingCart == null){
            shoppingCart = new Order2();
            request.getSession().setAttribute("shoppingCart", shoppingCart);
        }

        for (Goods good : goods) {
            for (Goods goods1 : shoppingCart.getGoods().keySet()) {
                if (good.equals(goods1))
                    good.setInCart(true);
            }
        }

        request.setAttribute("paginatedList", goods);

        daoManager.closeConnection();

        return services;
    }
}

