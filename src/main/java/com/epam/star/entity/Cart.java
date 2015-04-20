package com.epam.star.entity;

import com.epam.star.entity.interfaces.ShoppingCart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart extends AbstractEntity implements ShoppingCart {

    private Map<Goods, Integer> goods = new HashMap<>();

    public Map<Goods, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<Goods, Integer> cart) {
        this.goods = cart;
    }

    @Override
    public BigDecimal getTotalSum() {
        BigDecimal totalSum = new BigDecimal(0);

        for (Map.Entry<Goods, Integer> goods : this.goods.entrySet()) {
            BigDecimal price = goods.getKey().getPrice();
            Integer count = goods.getValue().intValue();
            BigDecimal cost = price.multiply(new BigDecimal(count));
            totalSum = totalSum.add(cost);
        }
        return totalSum;
    }

    @Override
    public int getGoodsCount() {
        return goods.size();
    }

    @Override
    public void addGoods(Goods goods) {
        this.goods.put(goods, 1);
    }

    @Override
    public void setGoodsCount(Goods goods, int count) {
        for (Map.Entry<Goods, Integer> entry : this.goods.entrySet()) {
            if (entry.getKey().equals(goods)) entry.setValue(count);
        }
    }

    @Override
    public void removeGoods(Goods goods) {
        this.goods.remove(goods);
    }

    @Override
    public BigDecimal getCostByGoodsId(int id) {
        for (Map.Entry<Goods, Integer> entry : goods.entrySet()) {
            if (entry.getKey().getId() == id) {
                return entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue()));
            }
        }
        return null;
    }

    @Override
    public List<Goods> getGoodsList() {
        return new ArrayList<>(goods.keySet());
    }

    public void clear() {
        goods.clear();
    }
}
