package com.epam.star.dao;

import com.epam.star.entity.PayCard;

public interface PayCardDao extends Dao<PayCard> {
    public PayCard findBySerialNumber(String serNum);
    public PayCard findBySecretNumber(String secNum);
    public PayCard findByStatus(String status);
}
