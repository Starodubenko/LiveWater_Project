package com.epam.star.entity;

import java.math.BigDecimal;

@MappedEntityForAdmin("Client")
public class Client extends AbstractEntity {

    private Integer avatar;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String telephone;
    private String mobilephone;
    private Position role;
    private BigDecimal virtualBalance;
    private Discount discount;

    public Client() {
    }

    public Client(int id, String address, Integer avatar, Discount discount, String firstName, String lastName, String login, String middleName, String mobilephone, String password, Position role, String telephone, BigDecimal virtualBalance) {
        super(id);
        this.address = address;
        this.avatar = avatar;
        this.discount = discount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.middleName = middleName;
        this.mobilephone = mobilephone;
        this.password = password;
        this.role = role;
        this.telephone = telephone;
        this.virtualBalance = virtualBalance;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Position getRole() {
        return role;
    }

    public void setRole(Position role) {
        this.role = role;
    }

    public BigDecimal getVirtualBalance() {
        return virtualBalance;
    }

    public void setVirtualBalance(BigDecimal virtualBalance) {
        this.virtualBalance = virtualBalance;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
