package com.epam.star.dao.util.EntityFromParameters;

import com.epam.star.dao.H2dao.*;
import com.epam.star.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static com.epam.star.action.util.ActionUtil.getImageFromRequestPart;

public class GetEntity {

    public static AbstractEntity getByEntityName(HttpServletRequest request, DaoManager daoManager, String entityName){

        if (entityName.toLowerCase().equals("client")){
            return getClient(request, daoManager);
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

        if (entityName.toLowerCase().equals("goods")){
            return getGoods(request, daoManager);
        }

        if (entityName.toLowerCase().equals("image")){
            return getImage(request, daoManager);
        }

        if (entityName.toLowerCase().equals("order")){
            return getOrder(request, daoManager);
        }

        if (entityName.toLowerCase().equals("orderedgoods")){
            return getOrderedGoods(request, daoManager);
        }

        if (entityName.toLowerCase().equals("paycard")){
            return getPayCard(request,daoManager);
        }

        if (entityName.toLowerCase().equals("period")){
            return getPeriod(request, daoManager);
        }

        if (entityName.toLowerCase().equals("position")){
            return getPosition(request,daoManager);
        }

        if (entityName.toLowerCase().equals("status")){
            return getOrderStatus(request, daoManager);
        }

        if (entityName.toLowerCase().equals("statuspaycard")){
            return getStatusPayCard(request, daoManager);
        }

        return null;
    }

    private static AbstractEntity getStatusPayCard(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }

    private static AbstractEntity getOrderStatus(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }

    private static AbstractEntity getPosition(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }

    private static AbstractEntity getPeriod(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }

    private static AbstractEntity getOrderedGoods(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }

    private static AbstractEntity getOrder(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }

    private static Image getImage(HttpServletRequest request, DaoManager daoManager) {
        Image image = getImageFromRequestPart(request, "filename");
        if (request.getParameter("deleted").equals("on")) image.setDeleted(true);
        else image.setDeleted(false);
        return image;
    }

    private static AbstractEntity getGoods(HttpServletRequest request, DaoManager daoManager) {

        Goods goods = new Goods();

//        if (request.getParameter("goodsImageFileName") != null)
            goods.setImage(getImageFromRequestPart(request,"goodsImageFileName"));
        if (request.getParameter("goodsname") != null)
            goods.setGoodsName(request.getParameter("goodsname"));
        if (request.getParameter("price") != null)
            goods.setPrice(new BigDecimal(request.getParameter("price")));
        if (request.getParameter("deleted") != null)
            goods.setDeleted(Boolean.valueOf(request.getParameter("deleted")));

        return goods;
    }

    private static AbstractEntity getDiscount(HttpServletRequest request, DaoManager daoManager) {
        return null;
    }


    private static Client getClient(HttpServletRequest request, DaoManager daoManager){

        H2PositionDao positionDao = daoManager.getPositionDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();

        Client client = new Client();

        client.setLogin(request.getParameter("login"));
        client.setPassword(request.getParameter("pasword"));
        client.setFirstName(request.getParameter("firstname"));
        client.setLastName(request.getParameter("lastname"));
        client.setMiddleName(request.getParameter("middlename"));
        client.setAddress(request.getParameter("address"));
        client.setTelephone(request.getParameter("telephone"));
        client.setMobilephone(request.getParameter("mobilephone"));
        client.setRole(positionDao.findByPositionName(request.getParameter("position_id")));
        client.setVirtualBalance(new BigDecimal(request.getParameter("virtual_balance")));
        client.setAvatar(Integer.valueOf(request.getParameter("avatar")));
        client.setDiscount(discountDao.findById(Integer.valueOf(request.getParameter("discount"))));
        if (request.getParameter("deleted").equals("on")) client.setDeleted(true);
        else client.setDeleted(false);

        return client;
    }

    private static Employee getEmployee(HttpServletRequest request, DaoManager daoManager){

        H2PositionDao positionDao = daoManager.getPositionDao();
        H2ImageDao imageDao = daoManager.getImageDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();

        Employee employee = new Employee();

        employee.setLogin(request.getParameter("login"));
        employee.setPassword(request.getParameter("pasword"));
        employee.setFirstName(request.getParameter("firstname"));
        employee.setLastName(request.getParameter("lastname"));
        employee.setMiddleName(request.getParameter("middlename"));
        employee.setAddress(request.getParameter("address"));
        employee.setTelephone(request.getParameter("telephone"));
        employee.setMobilephone(request.getParameter("mobilephone"));
        employee.setIdentityCard(request.getParameter("identitycard"));
        employee.setWorkBook(request.getParameter("workbook"));
        employee.setRNN(request.getParameter("rnn"));
        employee.setSIK(request.getParameter("sik"));
        employee.setRole(positionDao.findByPositionName(request.getParameter("position_id")));
        employee.setVirtualBalance(new BigDecimal(request.getParameter("virtual_balance")));
        employee.setAvatar(Integer.valueOf(request.getParameter("avatar")));
        employee.setDiscount(discountDao.findById(Integer.valueOf(request.getParameter("discount"))));
        if (request.getParameter("deleted").equals("on")) employee.setDeleted(true);
        else employee.setDeleted(false);

        return employee;
    }

    private static Contact getContact(HttpServletRequest request, DaoManager daoManager){

        H2PayCardStatusDao payCardStatusDao = daoManager.getPayCardStatusDao();

        Contact contact = new Contact();

        contact.setTelephone(request.getParameter("telephone"));
        contact.setOwner(request.getParameter("owner"));
        contact.setPart(request.getParameter("part"));
        if (request.getParameter("deleted").equals("on")) contact.setDeleted(true);
        else contact.setDeleted(false);

        return contact;
    }


    private static PayCard getPayCard(HttpServletRequest request, DaoManager daoManager){

        H2PayCardStatusDao payCardStatusDao = daoManager.getPayCardStatusDao();

        PayCard payCard = new PayCard();

        payCard.setSerialNumber(request.getParameter("serial_number"));
        payCard.setSecretNumber(request.getParameter("secret_number"));
        payCard.setBalance(new BigDecimal(request.getParameter("balance")));
        if (request.getParameter("not activated") == null) payCard.setStatusPayCard(payCardStatusDao.findByStatusName("not activated"));
        else payCard.setStatusPayCard(payCardStatusDao.findByStatusName(request.getParameter("status_name")));
        if (request.getParameter("deleted") == null) payCard.setDeleted(false);
        else payCard.setDeleted(true);

        return payCard;
    }
}
