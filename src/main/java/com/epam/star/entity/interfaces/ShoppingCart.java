package com.epam.star.entity.interfaces;

import com.epam.star.entity.Goods;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCart {

    public BigDecimal getTotalSum();
    public int getGoodsCount();

    public void addGoods(Goods goods);

    public void setGoodsCount(Goods goods, int count);

    public void removeGoods(Goods goods);

    public BigDecimal getCostByGoodsId(int Id);

    List<Goods> getGoodsList();
}
