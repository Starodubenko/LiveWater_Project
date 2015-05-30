package com.epam.star.entity;

public class Characteristic extends AbstractEntity {
    private String CharacteristicName;

    public String getCharacteristicName() {
        return CharacteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        CharacteristicName = characteristicName;
    }

    @Override
    public String toString() {
        return "Characteristic{name = "+
                getCharacteristicName()+"}";
    }
}
