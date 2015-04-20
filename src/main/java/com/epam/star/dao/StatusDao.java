package com.epam.star.dao;


import com.epam.star.entity.Status;

public interface StatusDao extends Dao<Status> {
    public Status findByStatusName(String name);
}
