package com.epam.star.dao;

import com.epam.star.entity.Order2;

import java.util.List;

public interface Order2Dao extends Dao<Order2> {
    List<Order2> findAllByClientIdToday(int id);

    List<Order2> findAllByClientIdLastDays(int id);

    List<Order2> findAllByClientId(int clientId);

    int getClientOrdersCount(int id);

//    void findCart(T user);
}
