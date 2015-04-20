package com.epam.star.dao.util.EntityFromParameters;

import com.epam.star.dao.H2dao.*;
import com.epam.star.dao.util.UtilDao;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class GetEntityFields {

    public static Map<String, String> getByEntityName(HttpServletRequest request, DaoManager daoManager, String entityName){

        if (entityName.toLowerCase().equals("client")){
            return getClientFields(request, daoManager);
        }

        if (entityName.toLowerCase().equals("contact")){
            return getContact(request, daoManager);
        }

        if (entityName.toLowerCase().equals("discount")){
            return getDiscount(request, daoManager);
        }

        if (entityName.toLowerCase().equals("employee")){
            return getEmployee(request, daoManager);
        }

        if (entityName.toLowerCase().equals("goods")) return getGoods(request, daoManager);

        if (entityName.toLowerCase().equals("image")){
            return getImage(request, daoManager);
        }

        if (entityName.toLowerCase().equals("order2")){
            return getOrder(request, daoManager);
        }
//
//        if (entityName.toLowerCase().equals("orderedgoods")){
//            return getPayCard(request,daoManager);
//        }

        if (entityName.toLowerCase().equals("paycard")){
            return getPayCard(request,daoManager);
        }

        if (entityName.toLowerCase().equals("period")){
            return getPeriod(request, daoManager);
        }

        if (entityName.toLowerCase().equals("position")){
            return getPosition(request, daoManager);
        }

        if (entityName.toLowerCase().equals("status")){
            return getStatus(request, daoManager);
        }

        if (entityName.toLowerCase().equals("statuspaycard")){
            return getStatus(request, daoManager);
        }

        return null;
    }

    private static Map<String, String> getOrder(HttpServletRequest request, DaoManager daoManager) {
        Map<String, String> fieldsMap = new HashMap<>();

        UtilDao utilDao = new UtilDao();

        H2ClientDao clientDao = daoManager.getClientDao();
        H2PeriodDao periodDao = daoManager.getPeriodDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();
        H2StatusDao statusDao = daoManager.getStatusDao();

//        if (request.getParameter("firstname") != null && !request.getParameter("firstname").equals(""))
//            fieldsMap.put("firstname", request.getParameter("firstname"));
//        if (request.getParameter("lastname") != null && !request.getParameter("lastname").equals(""))
//            fieldsMap.put("lastname", request.getParameter("lastname"));
//        if (request.getParameter("middlename") != null && !request.getParameter("middlename").equals(""))
//            fieldsMap.put("middlename", request.getParameter("middlename"));
        if (request.getParameter("number") != null && !request.getParameter("number").equals(""))
            fieldsMap.put("number", request.getParameter("number"));
        if (request.getParameter("delivery_time") != null && !request.getParameter("delivery_time").equals(""))
            fieldsMap.put("period_id", String.valueOf(periodDao.findById(Integer.valueOf(request.getParameter("delivery_time"))).getId()));
        if (request.getParameter("delivery_date") != null && !request.getParameter("delivery_date").equals(""))
            fieldsMap.put("delivery_date", utilDao.getDateValue(request.getParameter("delivery_date")).toString());
        if (request.getParameter("order_date") != null && !request.getParameter("order_date").equals(""))
            fieldsMap.put("order_date", utilDao.getDateValue(request.getParameter("order_date")).toString());
        if (request.getParameter("paid") == null) fieldsMap.put("paid", "false");
        else fieldsMap.put("paid", "true");
        if (request.getParameter("order_status") != null && !request.getParameter("order_status").equals(""))
            fieldsMap.put("status_id", String.valueOf(statusDao.findById(Integer.valueOf(request.getParameter("order_status"))).getId()));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }

    private static Map<String, String> getStatus(HttpServletRequest request, DaoManager daoManager) {
        Map<String, String> fieldsMap = new HashMap<>();

        if (request.getParameter("status_name") != null && !request.getParameter("status_name").equals(""))
            fieldsMap.put("status_name", request.getParameter("status_name"));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }

    private static Map<String, String> getPeriod(HttpServletRequest request, DaoManager daoManager) {
        Map<String, String> fieldsMap = new HashMap<>();

        if (request.getParameter("period") != null && !request.getParameter("period").equals(""))
            fieldsMap.put("period", request.getParameter("period"));
        if (request.getParameter("describe") != null && !request.getParameter("describe").equals(""))
            fieldsMap.put("describe", request.getParameter("describe"));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }

    private static Map<String, String> getImage(HttpServletRequest request, DaoManager daoManager) {
        Map<String, String> fieldsMap = new HashMap<>();

        if (request.getParameter("filename") != null && !request.getParameter("filename").equals(""))
            fieldsMap.put("filename", request.getParameter("filename"));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }

    private static Map<String, String> getPosition(HttpServletRequest request, DaoManager daoManager) {
        Map<String, String> fieldsMap = new HashMap<>();

        if (request.getParameter("position_name") != null && !request.getParameter("position_name").equals(""))
            fieldsMap.put("position_name", request.getParameter("position_name"));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }

    private static Map<String, String> getGoods(HttpServletRequest request, DaoManager daoManager) {
        Map<String, String> fieldsMap = new HashMap<>();

        if (request.getParameter("goods_name") != null && !request.getParameter("goods_name").equals(""))
            fieldsMap.put("goods_name", request.getParameter("goods_name"));
        if (request.getParameter("price") != null && !request.getParameter("price").equals(""))
            fieldsMap.put("price", request.getParameter("price"));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }

    private static Map<String, String> getDiscount(HttpServletRequest request, DaoManager daoManager) {
        Map<String, String> fieldsMap = new HashMap<>();

        if (request.getParameter("name") != null && !request.getParameter("name").equals(""))
            fieldsMap.put("name", request.getParameter("name"));
        if (request.getParameter("discount_percentage") != null && !request.getParameter("discount_percentage").equals(""))
            fieldsMap.put("discount_percentage", request.getParameter("discount_percentage"));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }


    private static Map<String, String> getClientFields(HttpServletRequest request, DaoManager daoManager){

        H2PositionDao positionDao = daoManager.getPositionDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();

        Map<String, String> fieldsMap = new HashMap<>();

        if (request.getParameter("login") != null && !request.getParameter("login").equals(""))
        fieldsMap.put("login", request.getParameter("login"));
        if (request.getParameter("password") != null && !request.getParameter("password").equals(""))
        fieldsMap.put("password", request.getParameter("pasword"));
        if (request.getParameter("firstname") != null && !request.getParameter("firstname").equals(""))
        fieldsMap.put("firstname", request.getParameter("firstname"));
        if (request.getParameter("lastname") != null && !request.getParameter("lastname").equals(""))
        fieldsMap.put("lastname", request.getParameter("lastname"));
        if (request.getParameter("middlename") != null && !request.getParameter("middlename").equals(""))
        fieldsMap.put("middlename", request.getParameter("middlename"));
        if (request.getParameter("address") != null && !request.getParameter("address").equals(""))
        fieldsMap.put("address", request.getParameter("address"));
        if (request.getParameter("telephone") != null && !request.getParameter("telephone").equals(""))
        fieldsMap.put("telephone", request.getParameter("telephone"));
        if (request.getParameter("mobilephone") != null && !request.getParameter("mobilephone").equals(""))
        fieldsMap.put("mobilephone", request.getParameter("mobilephone"));
        if (request.getParameter("position_name") != null && !request.getParameter("position_name").equals(""))
        fieldsMap.put("position_id", String.valueOf(positionDao.findByPositionName(request.getParameter("position_name")).getId()));
        if (request.getParameter("virtual_balance") != null && !request.getParameter("virtual_balance").equals(""))
        fieldsMap.put("virtual_balance", request.getParameter("virtual_balance"));
        if (request.getParameter("avatar") != null && !request.getParameter("avatar").equals(""))
        fieldsMap.put("avatar", request.getParameter("avatar"));
        if (request.getParameter("discount") != null && !request.getParameter("discount").equals(""))
        fieldsMap.put("discount", String.valueOf(discountDao.findByPercentage(Integer.valueOf(request.getParameter("discount"))).getId()));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }

    private static Map<String, String> getEmployee(HttpServletRequest request, DaoManager daoManager){

        H2PositionDao positionDao = daoManager.getPositionDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();

        Map<String, String> fieldsMap = new HashMap<>();

        if (request.getParameter("login") != null && !request.getParameter("login").equals(""))
            fieldsMap.put("login", request.getParameter("login"));
        if (request.getParameter("password") != null && !request.getParameter("password").equals(""))
            fieldsMap.put("password", request.getParameter("pasword"));
        if (request.getParameter("firstname") != null && !request.getParameter("firstname").equals(""))
            fieldsMap.put("firstname", request.getParameter("firstname"));
        if (request.getParameter("lastname") != null && !request.getParameter("lastname").equals(""))
            fieldsMap.put("lastname", request.getParameter("lastname"));
        if (request.getParameter("middlename") != null && !request.getParameter("middlename").equals(""))
            fieldsMap.put("middlename", request.getParameter("middlename"));
        if (request.getParameter("address") != null && !request.getParameter("address").equals(""))
            fieldsMap.put("address", request.getParameter("address"));
        if (request.getParameter("telephone") != null && !request.getParameter("telephone").equals(""))
            fieldsMap.put("telephone", request.getParameter("telephone"));
        if (request.getParameter("mobilephone") != null && !request.getParameter("mobilephone").equals(""))
            fieldsMap.put("mobilephone", request.getParameter("mobilephone"));

        if (request.getParameter("identitycard") != null && !request.getParameter("identitycard").equals(""))
            fieldsMap.put("identitycard", request.getParameter("identitycard"));
        if (request.getParameter("workbook") != null && !request.getParameter("workbook").equals(""))
            fieldsMap.put("workbook", request.getParameter("workbook"));
        if (request.getParameter("sik") != null && !request.getParameter("sik").equals(""))
            fieldsMap.put("sik", request.getParameter("sik"));
        if (request.getParameter("rnn") != null && !request.getParameter("rnn").equals(""))
            fieldsMap.put("rnn", request.getParameter("rnn"));

        if (request.getParameter("position_name") != null && !request.getParameter("position_name").equals(""))
            fieldsMap.put("position_id", String.valueOf(positionDao.findByPositionName(request.getParameter("position_name")).getId()));
        if (request.getParameter("virtual_balance") != null && !request.getParameter("virtual_balance").equals(""))
            fieldsMap.put("virtual_balance", request.getParameter("virtual_balance"));
        if (request.getParameter("avatar") != null && !request.getParameter("avatar").equals(""))
            fieldsMap.put("avatar", request.getParameter("avatar"));
        if (request.getParameter("discount") != null && !request.getParameter("discount").equals(""))
            fieldsMap.put("discount", String.valueOf(discountDao.findByPercentage(Integer.valueOf(request.getParameter("discount"))).getId()));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }

    private static Map<String, String> getContact(HttpServletRequest request, DaoManager daoManager){

        Map<String, String> fieldsMap = new HashMap<>();

        if (request.getParameter("telephone") != null && !request.getParameter("telephone").equals(""))
            fieldsMap.put("telephone", request.getParameter("telephone"));
        if (request.getParameter("owner") != null && !request.getParameter("owner").equals(""))
            fieldsMap.put("owner", request.getParameter("owner"));
        if (request.getParameter("part") != null && !request.getParameter("part").equals(""))
            fieldsMap.put("part", request.getParameter("part"));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }


    private static Map<String, String> getPayCard(HttpServletRequest request, DaoManager daoManager){

        H2PayCardStatusDao payCardStatusDao = daoManager.getPayCardStatusDao();

        Map<String, String> fieldsMap = new HashMap<>();

        if (request.getParameter("serial_number") != null && !request.getParameter("serial_number").equals(""))
            fieldsMap.put("serial_number", request.getParameter("serial_number"));
        if (request.getParameter("secret_number") != null && !request.getParameter("secret_number").equals(""))
            fieldsMap.put("secret_number", request.getParameter("secret_number"));
        if (request.getParameter("balance") != null && !request.getParameter("balance").equals(""))
            fieldsMap.put("balance", request.getParameter("balance"));
        if (request.getParameter("card_status") != null && !request.getParameter("card_status").equals(""))
            fieldsMap.put("id_status_pay_card", String.valueOf(payCardStatusDao.findByStatusName(request.getParameter("card_status")).getId()));
        if (request.getParameter("deleted") == null) fieldsMap.put("deleted", "false");
        else fieldsMap.put("deleted", "true");

        return fieldsMap;
    }
}
