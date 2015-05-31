package com.epam.star.dao;

import com.epam.star.entity.GoodsCharacteristic;

import java.util.List;

public interface GoodsCharacteristicsDao extends Dao<GoodsCharacteristic> {
    List<GoodsCharacteristic> findByGoodsId(int goodsId);
}
