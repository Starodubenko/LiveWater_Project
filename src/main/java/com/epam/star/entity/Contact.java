package com.epam.star.entity;

@MappedEntityForAdmin("Contact")
public class Contact extends AbstractEntity {
    private String owner;
    private String telephone;
    private String part;

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
