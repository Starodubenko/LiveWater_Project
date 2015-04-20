package com.epam.star.dao.util.EntityFromParameters;

import com.epam.star.dao.H2dao.*;
import com.epam.star.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Time;

import static com.epam.star.action.util.ActionUtil.getImageFromRequestPart;

public class GetEntityUpdate<T extends AbstractEntity> {

    public AbstractEntity getByEntityName(HttpServletRequest request, DaoManager daoManager, String entityName, T oldEntity){

        if (entityName.toLowerCase().equals("client")){
            return getClient(request, daoManager, (Client) oldEntity);
        }

        if (entityName.toLowerCase().equals("contact")){
            return getContact(request, (Contact) oldEntity);
        }

        if (entityName.toLowerCase().equals("discount")){
            return getDiscount(request, (Discount) oldEntity);
        }

        if (entityName.toLowerCase().equals("employee")){
            return getEmployee(request, daoManager);
        }

        if (entityName.toLowerCase().equals("goods")){
            return getGoods(request, daoManager, (Goods) oldEntity);
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
            return getPayCard(request,daoManager, (PayCard) oldEntity);
        }

        if (entityName.toLowerCase().equals("period")){
            return getPeriod(request, daoManager, (Period) oldEntity);
        }

        if (entityName.toLowerCase().equals("position")){
            return getPosition(request, (Position) oldEntity);
        }

        if (entityName.toLowerCase().equals("status")){
            return getOrderStatus(request, (Status) oldEntity);
        }

        if (entityName.toLowerCase().equals("statuspaycard")){
            return getStatusPayCard(request, (StatusPayCard) oldEntity);
        }

        return null;
    }

    private static AbstractEntity getStatusPayCard(HttpServletRequest request, StatusPayCard oldStatus) {

        if (request.getParameter("status_name") != null && !oldStatus.getStatusName().equals(request.getParameter("status_name")))
            oldStatus.setStatusName(request.getParameter("status_name"));
        if (request.getParameter("deleted") != null && oldStatus.isDeleted() != request.getParameter("deleted").equals("on"))
            oldStatus.setDeleted(request.getParameter("deleted").equals("on"));

        return oldStatus;
    }

    private static AbstractEntity getOrderStatus(HttpServletRequest request, Status oldStatus) {

        if (request.getParameter("status_name") != null && !oldStatus.getStatusName().equals(request.getParameter("status_name")))
            oldStatus.setStatusName(request.getParameter("status_name"));
        if (request.getParameter("deleted") != null && oldStatus.isDeleted() != request.getParameter("deleted").equals("on"))
            oldStatus.setDeleted(Boolean.valueOf(request.getParameter("deleted")));

        return oldStatus;
    }

    private static AbstractEntity getPosition(HttpServletRequest request, Position oldPosition) {

        if (request.getParameter("position") != null && !oldPosition.getPositionName().equals(request.getParameter("position")))
            oldPosition.setPositionName(request.getParameter("position"));
        if (request.getParameter("deleted") != null && oldPosition.isDeleted() != Boolean.valueOf(request.getParameter("deleted")))
            oldPosition.setDeleted(Boolean.valueOf(request.getParameter("deleted")));

        return oldPosition;
    }

    private static AbstractEntity getPeriod(HttpServletRequest request, DaoManager daoManager, Period oldPeriod) {

        if (request.getParameter("period") != null && !oldPeriod.getPeriod().equals(Time.valueOf(request.getParameter("period"))))
            oldPeriod.setPeriod(Time.valueOf(request.getParameter("period")));
        if (request.getParameter("describe") != null && !oldPeriod.getDescribe().equals(request.getParameter("describe")))
            oldPeriod.setDescribe(request.getParameter("describe"));
        if (request.getParameter("deleted") != null && oldPeriod.isDeleted() != Boolean.valueOf(request.getParameter("deleted")))
            oldPeriod.setDeleted(Boolean.valueOf(request.getParameter("deleted")));

        return oldPeriod;
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

    private static AbstractEntity getGoods(HttpServletRequest request, DaoManager daoManager, Goods oldGoods) {

        if (request.getParameter("goodsImageFileName") != null && !oldGoods.getImage().getFilename().equals(request.getParameter("goodsImageFileName")))
            oldGoods.setImage(getImageFromRequestPart(request,"goodsImageFileName"));
        if (request.getParameter("goodsname") != null && !oldGoods.getGoodsName().equals(request.getParameter("goodsname")))
            oldGoods.setGoodsName(request.getParameter("goodsname"));
        if (request.getParameter("price") != null && !oldGoods.getPrice().equals(new BigDecimal(request.getParameter("price"))))
            oldGoods.setPrice(new BigDecimal(request.getParameter("price")));
        if (request.getParameter("deleted") != null && oldGoods.isDeleted() != Boolean.valueOf(request.getParameter("deleted")))
            oldGoods.setDeleted(Boolean.valueOf(request.getParameter("deleted")));

        return oldGoods;
    }

    private static AbstractEntity getDiscount(HttpServletRequest request, Discount oldDiscount) {

        if (request.getParameter("name") != null && !oldDiscount.getName().equals(request.getParameter("name")))
            oldDiscount.setName(request.getParameter("name"));
        if (request.getParameter("discount_percentage") != null && oldDiscount.getPercentage() == Integer.valueOf(request.getParameter("discount_percentage")))
            oldDiscount.setPercentage(Integer.valueOf(request.getParameter("discount_percentage")));
        if (request.getParameter("deleted") != null && oldDiscount.isDeleted() != Boolean.valueOf(request.getParameter("deleted")))
            oldDiscount.setDeleted(Boolean.valueOf(request.getParameter("deleted")));

        return oldDiscount;
    }


    private Client getClient(HttpServletRequest request, DaoManager daoManager, Client client){

        H2PositionDao positionDao = daoManager.getPositionDao();
        H2ImageDao imageDao = daoManager.getImageDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();

        if (request.getParameter("login") != null && !client.getLogin().equals(request.getParameter("login")))
            client.setLogin(request.getParameter("login"));
        if (request.getParameter("pasword") != null && !client.getPassword().equals(request.getParameter("pasword")))
            client.setPassword(request.getParameter("pasword"));
        if (request.getParameter("firstname") != null && !client.getFirstName().equals(request.getParameter("firstname")))
            client.setFirstName(request.getParameter("firstname"));
        if (request.getParameter("lastname") != null && !client.getLastName().equals(request.getParameter("lastname")))
            client.setLastName(request.getParameter("lastname"));
        if (request.getParameter("middlename") != null && !client.getMiddleName().equals(request.getParameter("middlename")))
            client.setMiddleName(request.getParameter("middlename"));
        if (request.getParameter("address") != null && !client.getAddress().equals(request.getParameter("address")))
            client.setAddress(request.getParameter("address"));
        if (request.getParameter("telephone") != null && !client.getTelephone().equals(request.getParameter("telephone")))
            client.setTelephone(request.getParameter("telephone"));
        if (request.getParameter("mobilephone") != null && !client.getMobilephone().equals(request.getParameter("mobilephone")))
            client.setMobilephone(request.getParameter("mobilephone"));

        if (request.getParameter("position_id") != null && client.getRole().getId() != Integer.valueOf(request.getParameter("position_id")))
            client.setRole(positionDao.findById(Integer.valueOf(request.getParameter("position_id"))));
        if (request.getParameter("virtual_balance") != null && client.getVirtualBalance().intValue() != Integer.valueOf(request.getParameter("virtual_balance")))
            client.setVirtualBalance(new BigDecimal(request.getParameter("virtual_balance")));
        if (request.getParameter("avatar") != null && !client.getAvatar().equals(Integer.valueOf(request.getParameter("avatar"))))
            client.setAvatar(Integer.valueOf(request.getParameter("avatar")));
        if (request.getParameter("discount") != null && client.getDiscount().getId() != Integer.valueOf(request.getParameter("discount")))
            client.setDiscount(discountDao.findById(Integer.valueOf(request.getParameter("discount"))));
        if (request.getParameter("deleted") != null && client.isDeleted() != Boolean.valueOf(request.getParameter("deleted")))
            client.setDeleted(Boolean.valueOf(request.getParameter("deleted")));

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
        if (request.getParameter("deleted") != null && employee.isDeleted() != Boolean.valueOf(request.getParameter("deleted")))
            employee.setDeleted(Boolean.valueOf(request.getParameter("deleted")));

        return employee;
    }

    private static Contact getContact(HttpServletRequest request, Contact oldContact){

        if (request.getParameter("telephone") != null && !oldContact.getTelephone().equals(request.getParameter("telephone")))
            oldContact.setTelephone(request.getParameter("telephone"));
        if (request.getParameter("owner") != null && !oldContact.getOwner().equals(request.getParameter("owner")))
            oldContact.setOwner(request.getParameter("owner"));
        if (request.getParameter("part") != null && !oldContact.getPart().equals(request.getParameter("part")))
            oldContact.setPart(request.getParameter("part"));
        if (request.getParameter("deleted") != null && oldContact.isDeleted() != Boolean.valueOf(request.getParameter("deleted")))
            oldContact.setDeleted(Boolean.valueOf(request.getParameter("deleted")));

        return oldContact;
    }


    private static PayCard getPayCard(HttpServletRequest request, DaoManager daoManager, PayCard oldPayCard){

        H2PayCardStatusDao payCardStatusDao = daoManager.getPayCardStatusDao();

        if (request.getParameter("serial_number") != null && !oldPayCard.getSerialNumber().equals(request.getParameter("serial_number")))
            oldPayCard.setSerialNumber(request.getParameter("serial_number"));
        if (request.getParameter("secret_number") != null && !oldPayCard.getSecretNumber().equals(request.getParameter("secret_number")))
            oldPayCard.setSecretNumber(request.getParameter("secret_number"));
        if (request.getParameter("balance") != null && !oldPayCard.getBalance().equals(new BigDecimal(request.getParameter("balance"))))
            oldPayCard.setBalance(new BigDecimal(request.getParameter("balance")));
        if (request.getParameter("status_name") != null && !oldPayCard.getStatusPayCard().equals(payCardStatusDao.findByStatusName("status_name")))
            oldPayCard.setStatusPayCard(payCardStatusDao.findByStatusName(request.getParameter("status_name")));
        if (request.getParameter("deleted") != null && oldPayCard.isDeleted() != Boolean.valueOf(request.getParameter("deleted")))
            oldPayCard.setDeleted(Boolean.valueOf(request.getParameter("deleted")));

        return oldPayCard;
    }
}
