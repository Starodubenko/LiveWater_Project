package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao2;
import com.epam.star.dao.H2dao.H2OrderedGoodsDao;
import com.epam.star.dao.PeriodDao;
import com.epam.star.dao.StatusDao;
import com.epam.star.entity.*;
import com.epam.star.util.Validator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

@MappedAction("GET/createFullOrder")
public class CreateFullOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateFullOrderAction.class);
    private static Random rnd = new Random();

    ActionResult message = new ActionResult("message");
    ActionResult result = new ActionResult("json");
    ActionResult client = new ActionResult("client", true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        JSONObject jsonObject = new JSONObject();
        Client user = (Client) request.getSession().getAttribute("user");
        String paymentType = request.getParameter("paymentType");

        Order2 order = null;
        boolean haveMoney = false;
        try {
            H2OrderDao2 orderDao = daoManager.getOrderDao2();

            order = createOrder(request, daoManager, jsonObject);

            if (order != null) {
                haveMoney = checkBalance(user, order);
                Boolean isInsert = false;
                if (haveMoney && paymentType.equals("online")) {
                    orderDao.insert(order);
                    insertOrderedGoods(order.getGoods(), daoManager, order.getNumber());
                    isInsert = true;
                } else jsonObject.put("payment", "order.not.enough.money");

                if (paymentType.equals("cache")) {
                    orderDao.insert(order);
                    insertOrderedGoods(order.getGoods(), daoManager, order.getNumber());
                    isInsert = true;
                }

                if (isInsert) {
                    Cart cart = (Cart) request.getSession().getAttribute("shoppingCart");
                    cart.clear();
                    request.getSession().setAttribute("shoppingCart", cart);
                    jsonObject.put("goToPC", "client");
                    request.getSession().setAttribute("orderSuccessful", "ok");
                    request.getSession().setAttribute("orderNumber", order.getNumber());
                }
            }
            daoManager.commit();
        } catch (Exception e) {
            daoManager.rollback();
            jsonObject.put("final-message", "during.creating.error.occurred");
            LOGGER.error("During creating the order error occurred on the server{}");
        } finally {
            daoManager.closeConnection();
        }

        if (jsonObject.length() > 0) {
            request.setAttribute("json", jsonObject);
            return result;
        }
        return client;
    }

    private boolean checkBalance(Client client, Order2 order) {
        if (client.getVirtualBalance().intValue()
                < order.getTotalSum().intValue() * (1 - (double) client.getDiscount().getPercentage() / 100))
            return false;
        else return true;
    }

    private Order2 createOrder(HttpServletRequest request, DaoManager daoManager, JSONObject jsonObject) throws ActionException, UnsupportedEncodingException, ParseException {

        Validator validator = new Validator(daoManager);

        Cart cart = (Cart) request.getSession().getAttribute("shoppingCart");


        Order2 order = null;
        String idString = request.getParameter("id");
        int userId = -1;
        if (idString != null)
            userId = Integer.parseInt(request.getParameter("id"));

        Client user = daoManager.getClientDao().findById(userId);
        if (user == null) daoManager.getEmployeeDao().findById(userId);
        if (user == null) user = (Client) request.getSession().getAttribute("user");

        if (cart != null && user != null) {
            PeriodDao periodDao = daoManager.getPeriodDao();
            StatusDao statusDao = daoManager.getStatusDao();

            order = new Order2();
            order.setGoods(cart.getGoods());
            order.setNumber(rnd.nextInt(999999));
            order.setUser(user);
            order.setPeriod(periodDao.findById(Integer.valueOf(request.getParameter("deliverytime"))));

            validator.checkDate(request.getParameter("deliverydate"));

            order.setOrderDate(new Date());
            order.setAdditionalInfo(new String(request.getParameter("additionalinformation").getBytes("ISO-8859-1"), "UTF-8"));
            boolean isOnline = debitFunds(request);
            if (isOnline) {
                order.setPaid(true);
            } else {
                order.setPaid(false);
            }
            order.setDiscount(user.getDiscount());
            order.setStatus(statusDao.findByStatusName("waiting"));

            if (validator.isValide()) {
                order.setDeliveryDate(new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("deliverydate")));
                return order;
            } else {
                Map<String, String> invalidFields = validator.getInvalidFields();
                for (Map.Entry<String, String> field : invalidFields.entrySet()) {
                    jsonObject.put(field.getKey(), field.getValue());
                }
            }
        } else {
            jsonObject.put("final-message", "user.need.reenter");
            LOGGER.error("Create order, User not authorized{}");
        }
        return null;
    }

    private boolean debitFunds(HttpServletRequest request) {

        String paymentType = request.getParameter("paymentType");
        if (paymentType != null) {
            boolean online = paymentType.equals("online");
            return online;
        } else return false;
    }

    private void insertOrderedGoods(Map<Goods, Integer> goods, DaoManager daoManager, int orderNumber) {

        H2OrderedGoodsDao orderedGoodsDao = daoManager.getOrderedGoodsDao();
        for (Map.Entry<Goods, Integer> good : goods.entrySet()) {
            OrderedGoods orderedGoods = new OrderedGoods();
            orderedGoods.setOrderNumber(orderNumber);
            orderedGoods.setGoods(good.getKey());
            orderedGoods.setGoodsCount(good.getValue());
            orderedGoodsDao.insert(orderedGoods);
        }
    }
}
