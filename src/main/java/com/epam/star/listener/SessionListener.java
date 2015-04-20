package com.epam.star.listener;

import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao2;
import com.epam.star.dao.H2dao.H2OrderedGoodsDao;
import com.epam.star.dao.StatusDao;
import com.epam.star.entity.Client;
import com.epam.star.entity.Goods;
import com.epam.star.entity.Order2;
import com.epam.star.entity.OrderedGoods;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;
import java.util.Map;
import java.util.Random;


@WebListener
public class SessionListener implements HttpSessionListener{

    private static Random rnd = new Random();

    private static final String STATUS_NAME = "cart";
    private static final String SHOPPING_CART = "shoppingCart";
    private static final String USER = "user";

    public SessionListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("entered");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {


        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2OrderDao2 orderDao2 = daoManager.getOrderDao2();
        StatusDao orderStatusDao = daoManager.getStatusDao();

        Order2 cart = (Order2)se.getSession().getAttribute(SHOPPING_CART);
        Client user = (Client)se.getSession().getAttribute(USER);


        if (orderDao2.isAlreadyExist(cart.getId())){
            orderDao2.updateEntity(cart);
            if (cart.getGoods().size() > 0)
                updateOrderedGoods(cart.getGoods(),daoManager,cart.getNumber());
        } else {
            cart.setGoods(cart.getGoods());
            cart.setNumber(rnd.nextInt(999999));
            cart.setUser(user);
            cart.setStatus(orderStatusDao.findByStatusName(STATUS_NAME));
            orderDao2.insert(cart);
            updateOrderedGoods(cart.getGoods(), daoManager, cart.getNumber());
        }


        daoManager.closeConnection();
    }

    private void updateOrderedGoods(Map<Goods, Integer> goods, DaoManager daoManager, int orderNumber) {

        H2OrderedGoodsDao orderedGoodsDao = daoManager.getOrderedGoodsDao();

        List<OrderedGoods> orderedGoods = orderedGoodsDao.findByOrderNumber(orderNumber);
        for (OrderedGoods goodsInDataBase : orderedGoods) {
            orderedGoodsDao.fullDeleteEntity(goodsInDataBase.getId());
        }

        for (Map.Entry<Goods, Integer> good : goods.entrySet()) {
            OrderedGoods orderedGood = orderedGoodsDao.findByOrderNumberAndGoods(orderNumber, good.getKey());

            if (orderedGood != null) {
                orderedGood.setGoodsCount(good.getValue());
                orderedGoodsDao.updateEntity(orderedGood);
            } else {
                orderedGood = new OrderedGoods();
                orderedGood.setOrderNumber(orderNumber);
                orderedGood.setGoods(good.getKey());
                orderedGood.setGoodsCount(good.getValue());
                orderedGoodsDao.insert(orderedGood);
            }
        }

    }
}
