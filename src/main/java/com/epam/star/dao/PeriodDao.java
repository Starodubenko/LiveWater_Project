package com.epam.star.dao;

import com.epam.star.entity.Period;

import java.sql.Time;

public interface PeriodDao extends Dao<Period> {
    public Period findByPeriod(Time period);
}
