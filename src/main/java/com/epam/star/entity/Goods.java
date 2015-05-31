package com.epam.star.entity;

import java.math.BigDecimal;
import java.util.List;

@MappedEntityForAdmin("Goods")
public class Goods extends AbstractEntity {

    private Image image;
    private String goodsName;
    private BigDecimal price;
    private List<GoodsCharacteristic> characteristics;
    private boolean inCart;

    public List<GoodsCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<GoodsCharacteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGoodsName() {

        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Goods)) return false;

        Goods goods = (Goods) o;

        if (inCart != goods.inCart) return false;
        if (goodsName != null ? !goodsName.equals(goods.goodsName) : goods.goodsName != null) return false;
        if (image != null ? !image.equals(goods.image) : goods.image != null) return false;
        if (price != null ? !price.equals(goods.price) : goods.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = image != null ? image.hashCode() : 0;
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (inCart ? 1 : 0);
        return result;
    }

    public GoodsCharacteristic haveCharacteristic(String name){
        for (GoodsCharacteristic characteristic : characteristics) {
            if (characteristic.getCharacteristic().getCharacteristicName().equals(name)) return characteristic;
        }
        return null;
    }
}
