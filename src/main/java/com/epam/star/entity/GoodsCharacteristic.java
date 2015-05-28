package com.epam.star.entity;

public class GoodsCharacteristic extends AbstractEntity {

    private Goods goods;
    private String characteristicName;
    private String caracteristicValue;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

    public String getCaracteristicValue() {
        return caracteristicValue;
    }

    public void setCaracteristicValue(String caracteristicValue) {
        this.caracteristicValue = caracteristicValue;
    }
}
