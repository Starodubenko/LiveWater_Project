package com.epam.star.dao;

import com.epam.star.entity.Contact;

import java.util.List;

public interface ContactDao extends Dao<Contact> {
    public List<Contact> getContacts();
}
