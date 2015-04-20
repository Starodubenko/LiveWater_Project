package com.epam.star.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Order extends AbstractEntity {

    private Client user;
    private int count;
    private Period period;
    private Goods goods;
    private Date deliveryDate;
    private String additionalInfo;
    private Status status;
    private Date orderDate;
    private BigDecimal orderCost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (count != order.count) return false;
        if (!additionalInfo.equals(order.additionalInfo)) return false;
        if (!deliveryDate.equals(order.deliveryDate)) return false;
        if (!goods.equals(order.goods)) return false;
        if (!orderCost.equals(order.orderCost)) return false;
        if (!orderDate.equals(order.orderDate)) return false;
        if (!paid.equals(order.paid)) return false;
        if (!period.equals(order.period)) return false;
        if (!status.equals(order.status)) return false;
        if (!user.equals(order.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + count;
        result = 31 * result + period.hashCode();
        result = 31 * result + goods.hashCode();
        result = 31 * result + deliveryDate.hashCode();
        result = 31 * result + additionalInfo.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + orderDate.hashCode();
        result = 31 * result + orderCost.hashCode();
        result = 31 * result + paid.hashCode();
        return result;
    }

    public BigDecimal getPaid() {

        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    private BigDecimal paid;

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(java.util.Date orderDate) {
        this.orderDate = new Date(orderDate.getTime());
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(java.util.Date deliveryDate) {
        this.deliveryDate = new Date(deliveryDate.getTime());
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (goods != null) orderCost = goods.getPrice().multiply(new BigDecimal(count));
        this.count = count;
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        orderCost = goods.getPrice().multiply(new BigDecimal(count));
        this.goods = goods;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
