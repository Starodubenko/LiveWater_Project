package com.epam.star.entity;

public class Characteristic extends AbstractEntity {
    private String characteristicName;

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

    @Override
    public String toString() {
        return "Characteristic{name = "+
                getCharacteristicName()+"}";
    }
}
