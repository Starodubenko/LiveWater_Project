package com.epam.star.dao;

import com.epam.star.entity.OrderedGoods;

import java.util.List;

public interface OrderedGoodsDao extends Dao<OrderedGoods> {

    public List<OrderedGoods> findByOrderNumber(int orderNumber);
    public OrderedGoods findById(int ID);
}
