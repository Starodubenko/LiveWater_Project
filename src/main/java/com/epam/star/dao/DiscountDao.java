package com.epam.star.dao;

import com.epam.star.entity.Discount;

public interface DiscountDao extends Dao<Discount> {
    public Discount findByName(String name);
    Discount findByPercentage(Integer discount);
}
