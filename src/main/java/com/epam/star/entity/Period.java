package com.epam.star.entity;

import java.sql.Time;

@MappedEntityForAdmin("Period")
public class Period extends AbstractEntity {

    private Time period;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    private String describe;

    public Time getPeriod() {
        return period;
    }

    public void setPeriod(Time period) {
        this.period = period;
    }

}
