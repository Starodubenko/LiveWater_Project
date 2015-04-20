package com.epam.star.entity;

@MappedEntityForAdmin("Discount")
public class Discount extends AbstractEntity {

    private String name;
    private int percentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
