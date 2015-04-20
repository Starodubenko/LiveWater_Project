package com.epam.star.entity;


import java.sql.Date;

@MappedEntityForAdmin("Order2")
public class Order2 extends Cart {

    private int number;
    private Client user;
    private Period period;
    private Date deliveryDate;
    private Date orderDate;
    private String additionalInfo;
    private boolean paid;
    private Discount discount;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(java.util.Date deliveryDate) {
        this.deliveryDate = new Date(deliveryDate.getTime());
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(java.util.Date orderDate) {
        this.orderDate = new Date(orderDate.getTime());
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period periodId) {
        this.period = periodId;
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client userID) {
        this.user = userID;
    }
}
