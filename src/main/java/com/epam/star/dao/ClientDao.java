package com.epam.star.dao;

import com.epam.star.entity.Client;

import java.sql.SQLException;

public interface ClientDao extends Dao<Client> {

    public Client findByLogin(String name) throws SQLException;

    public Client findByName(String name) throws SQLException;

    public Client findBySurname(String surName);

    public Client findByAddress(String address);

    public Client findByTelephone(String telephone);

    public Client findByMobilephone(String telephone);

    public Client findByCredentials(String login, String password);

    public boolean alreadyExist(String login);
}
