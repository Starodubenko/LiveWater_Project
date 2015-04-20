package com.epam.star.dao;

import com.epam.star.entity.Goods;

public interface GoodsDao extends Dao<Goods> {
    public Goods findByGoodsName(String name);
}
