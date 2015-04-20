package com.epam.star.dao;

import com.epam.star.entity.Position;

public interface PositionDao extends Dao<Position> {
    public Position findByPositionName(String name);
}
