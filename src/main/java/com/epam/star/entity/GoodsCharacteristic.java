package com.epam.star.entity;

public class GoodsCharacteristic extends AbstractEntity {

    private Goods goods;
    private Characteristic characteristic;
    private String caracteristicDescription;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public String getCaracteristicDescription() {
        return caracteristicDescription;
    }

    public void setCaracteristicDescription(String caracteristicDescription) {
        this.caracteristicDescription = caracteristicDescription;
    }
}
