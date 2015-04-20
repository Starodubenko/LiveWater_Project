package com.epam.star.entity;

@MappedEntityForAdmin("Status")
public class Status extends AbstractEntity {
    String statusName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Status)) return false;

        Status status = (Status) o;

        if (statusName != null) {
            if (!statusName.equals(status.statusName)) return false;
        } else {
            if (status.statusName != null) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return statusName != null ? statusName.hashCode() : 0;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
