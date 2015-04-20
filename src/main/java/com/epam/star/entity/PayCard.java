package com.epam.star.entity;

import java.math.BigDecimal;

@MappedEntityForAdmin("PayCard")
public class PayCard extends AbstractEntity {

    private String serialNumber;
    private String secretNumber;
    private BigDecimal balance;
    private StatusPayCard statusPayCard;

    public StatusPayCard getStatusPayCard() {
        return statusPayCard;
    }

    public void setStatusPayCard(StatusPayCard statusPayCard) {
        this.statusPayCard = statusPayCard;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(String secretNumber) {
        this.secretNumber = secretNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
