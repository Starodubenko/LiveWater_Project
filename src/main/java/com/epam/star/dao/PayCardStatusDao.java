package com.epam.star.dao;

import com.epam.star.entity.StatusPayCard;

import java.util.List;

public interface PayCardStatusDao extends Dao<StatusPayCard>{
    public StatusPayCard findByStatusName(String name);
    public List<StatusPayCard> findAll();
}
