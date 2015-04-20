package com.epam.star.dao.util.EntityFromParameters;

import com.epam.star.dao.H2dao.*;
import com.epam.star.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRefFieldOptions {

    public static Map<String, List> getByEntityName(DaoManager daoManager, String entityName){

        if (entityName.toLowerCase().equals("client")){
            return getClient(daoManager);
        }

        if (entityName.toLowerCase().equals("employee")){
            return getClient(daoManager);
        }

//        if (entityName.toLowerCase().equals("goods")){
//            return getGoods(request, daoManager);
//        }
//
//        if (entityName.toLowerCase().equals("image")){
//            return getImage(request, daoManager);
//        }
//
        if (entityName.toLowerCase().equals("order2")){
            return getOrder(daoManager);
        }
//
//        if (entityName.toLowerCase().equals("orderedgoods")){
//            return getOrderedGoods(request, daoManager);
//        }

        if (entityName.toLowerCase().equals("paycard")){
            return getPayCard(daoManager);
        }

        return null;
    }

    private static Map<String, List> getOrder(DaoManager daoManager) {

        Map<String, List>  orderRefFields = new HashMap<>();

        H2PeriodDao periodDao = daoManager.getPeriodDao();
        H2StatusDao statusDao = daoManager.getStatusDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();

        List<Period> periods = periodDao.findAll();
        List<Status> statuses = statusDao.findAll();
        List<Discount> discounts = discountDao.findAll();

        orderRefFields.put("periods", periods);
        orderRefFields.put("statuses", statuses);
        orderRefFields.put("discounts", discounts);

        return orderRefFields;
    }

    private static Map<String, List> getClient(DaoManager daoManager) {
        H2PositionDao positionDao = daoManager.getPositionDao();

        Map<String, List>  payCardRefFields = new HashMap<>();

        List<Position> all = positionDao.findAll();
        List<String> names = new ArrayList<>();

        for (Position position : all) {
            names.add(position.getPositionName());
        }

        payCardRefFields.put("positions",names);

        return payCardRefFields;
    }

    private static AbstractEntity getOrderedGoods(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }

    private static AbstractEntity getOrder(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }

    private static AbstractEntity getImage(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }

    private static AbstractEntity getGoods(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }

    private static Map<String, List>  getPayCard(DaoManager daoManager){

        H2PayCardStatusDao payCardStatusDao = daoManager.getPayCardStatusDao();

        Map<String, List>  payCardRefFields = new HashMap<>();

        List<StatusPayCard> all = payCardStatusDao.findAll();
        List<String> names = new ArrayList<>();

        for (StatusPayCard statusPayCard : all) {
            names.add(statusPayCard.getStatusName());
        }

        payCardRefFields.put("cardStatuses",names);

        return payCardRefFields;
    }
}
