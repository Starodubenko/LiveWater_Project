package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.*;
import com.epam.star.dao.util.UtilDao;
import com.epam.star.entity.Client;
import com.epam.star.entity.Order2;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;

@MappedAction("GET/orderOperation2")
public class OrderOperations2Action implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderOperations2Action.class);
    private UtilDao utilDao = new UtilDao();

    private static String WAITING = "waiting";
    private static String CANCELED = "canceled";
    private static String EXECUTED = "executed";
    private static String ACTIVE = "active";

    private static String CLIENT = "client";
    private static String DISPATCHER = "dispatcher";
    private static String FORWARDER = "forwarder"; //4WA(R)D_ear

    private static String CANCEL_BY_CLIENT = "cancelW";
    private static String CANCEL_BY_DISPATCHER = "cancelA";
    private static String RESTORE = "restore";
    private static String EXECUTE = "execute";
    private static String ACCEPT = "accept";
    private static String REPEAT = "repeat";
    private static String EDIT = "edit";

    private static String WITHDRAW = "withdraw";
    private static String RETURN = "return";

    ActionResult reviewOrdersClient = new ActionResult("browseOrders2",true);
    ActionResult reviewOrdersDispatcher = new ActionResult("changeEntity",true);
    ActionResult repeatOrder = new ActionResult("shoppingCart",true);
    ActionResult json = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2StatusDao statusDao = daoManager.getStatusDao();
        H2OrderDao2 orderDao = daoManager.getOrderDao2();
        H2ClientDao clientDao = daoManager.getClientDao();

        String actor = request.getParameter("actor");
        String operation = request.getParameter("typeOfOperation");

        Order2 order = null;
        Integer id = utilDao.getIntValue(request.getParameter("id"));
        if (id != null) order = orderDao.findById(id);

        JSONObject jsonObject = new JSONObject();
        try {
            if (order != null) {
                daoManager.beginTransaction();
                boolean clientChange = false;
                boolean orderChange = false;

                if (actor.equals(CLIENT) && order.getStatus().getStatusName().equals(WAITING) && operation.equals(CANCEL_BY_CLIENT)) {
                    order.setStatus(statusDao.findByStatusName(CANCELED));
                    orderChange = true;
                } else if (actor.equals(CLIENT) && order.getStatus().getStatusName().equals(CANCELED) && operation.equals(RESTORE)) {
                    order.setStatus(statusDao.findByStatusName(WAITING));
                    orderChange = true;
                } else if (actor.equals(CLIENT) && order.getStatus().getStatusName().equals(CANCELED) && operation.equals(EDIT)) {
//                    order.setStatus(statusDao.findByStatusName(WAITING));
//                    order.setDeleted(true);
                } else if (actor.equals(CLIENT) && order.getStatus().getStatusName().equals(EXECUTED) && operation.equals(REPEAT)) {
                    request.getSession().setAttribute("shoppingCart", order);
                    return repeatOrder;
                } else if (actor.equals(DISPATCHER) && order.getStatus().getStatusName().equals(ACTIVE) && operation.equals(CANCEL_BY_DISPATCHER)) {
                    order.setStatus(statusDao.findByStatusName(CANCELED));
                    order.setDeleted(true);
                    order.getUser().setVirtualBalance(fundsOperation(order, RETURN));
                    orderChange = true;
                    clientChange = true;
//                    jsonObject.put("newButton",RESTORE);
                } else if (actor.equals(DISPATCHER) && order.getStatus().getStatusName().equals(WAITING) && operation.equals(ACCEPT)) {
                    order.setStatus(statusDao.findByStatusName(ACTIVE));
                    order.getUser().setVirtualBalance(fundsOperation(order, WITHDRAW));
                    orderChange = true;
                    clientChange = true;
                } else if (actor.equals(FORWARDER) && order.getStatus().getStatusName().equals(ACTIVE) && operation.equals(EXECUTE)) {
                    order.setStatus(statusDao.findByStatusName(EXECUTED));
                    order.setDeleted(true);
                    orderChange = true;
                }

                if (clientChange) {
                    clientDao.updateEntity(order.getUser());
                }
                if (orderChange) orderDao.updateEntity(order);
                daoManager.commit();
            }
        }catch (Exception e){
            daoManager.rollback();
        }finally {
            daoManager.closeConnection();
        }

        jsonObject.put("newStatus", order.getStatus().getStatusName());
        request.setAttribute("json",jsonObject);
        return json;
    }

    private BigDecimal fundsOperation(Order2 order, String operation) {

        BigDecimal percent = new BigDecimal(order.getDiscount().getPercentage()).divide(new BigDecimal(100));
        BigDecimal orderCost = order.getTotalSum();
        BigDecimal result = orderCost.subtract(orderCost.multiply(percent));

        Client client = order.getUser();

            if (operation.equals(WITHDRAW)){
                return client.getVirtualBalance().subtract(result);
            } else {
                if (operation.equals(RETURN)){
                    return client.getVirtualBalance().add(result);
                }
            }
        return order.getUser().getVirtualBalance();
    }
}
