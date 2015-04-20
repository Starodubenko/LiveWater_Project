package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.DiscountDao;
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

@MappedAction("GET/fastCreateOrder")
public class FastCreateOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(FastCreateOrderAction.class);
    private static Random rnd = new Random();

    ActionResult message = new ActionResult("message");
    ActionResult result = new ActionResult("json");
    ActionResult client = new ActionResult("client", true);

    private static String CLIENT = "userForOrder";
    private static String SHOPPING_CART = "shoppingCartForClient";
    private static String DELIVERY_TIME = "deliverytime";
    private static String DELIVERY_DATE = "deliverydate";
    private static String ADDITIONAL_INFO = "additionalinformation";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        JSONObject jsonObject = new JSONObject();
        try {
            H2OrderDao2 orderDao = daoManager.getOrderDao2();

            Order2 order = createOrder(request, daoManager, jsonObject);

            if (order != null) {
                orderDao.insert(order);
                insertOrderedGoods(order.getGoods(), daoManager, order.getNumber());

                Cart cart = (Cart) request.getSession().getAttribute(SHOPPING_CART);
                cart.clear();
                request.getSession().setAttribute(SHOPPING_CART, cart);
                jsonObject.put("ok",true);
            }
            daoManager.commit();
        } catch (Exception e) {
            daoManager.rollback();
            request.setAttribute("errormessage", "during.creating.error.occurred");
            return message;
        } finally {
            daoManager.closeConnection();
        }

        if (jsonObject.length() > 0) {
            request.setAttribute("json", jsonObject);
            return result;
        }
        return client;
    }

    private Order2 createOrder(HttpServletRequest request, DaoManager daoManager, JSONObject jsonObject) throws ActionException, UnsupportedEncodingException, ParseException {

        Validator validator = new Validator(daoManager);

        Cart cart = (Cart) request.getSession().getAttribute(SHOPPING_CART);

        Client user = (Client) request.getSession().getAttribute(CLIENT);

        PeriodDao periodDao = daoManager.getPeriodDao();
        StatusDao statusDao = daoManager.getStatusDao();
        DiscountDao discountDao = daoManager.getDiscountDao();

        Map<String, String[]> parameterMap = request.getParameterMap();

        Order2 order;
        order = new Order2();
        order.setGoods(cart.getGoods());
        order.setNumber(rnd.nextInt(999999));
        order.setUser(user);
        order.setPeriod(periodDao.findById(Integer.valueOf(request.getParameter(DELIVERY_TIME))));

        validator.checkDate(request.getParameter(DELIVERY_DATE));

        order.setOrderDate(new Date());
        order.setAdditionalInfo(new String(request.getParameter(ADDITIONAL_INFO).getBytes("ISO-8859-1"), "UTF-8"));
        order.setPaid(false);
        if (user != null)
        order.setDiscount(user.getDiscount());
        else order.setDiscount(discountDao.findByPercentage(0));
        order.setStatus(statusDao.findByStatusName("active"));

        if (validator.isValide()) {
            order.setDeliveryDate(new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter(DELIVERY_DATE)));
            return order;
        } else {
            Map<String, String> invalidFields = validator.getInvalidFields();
            for (Map.Entry<String, String> field : invalidFields.entrySet()) {
                jsonObject.put(field.getKey(), field.getValue());
            }
        }
        return null;
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
