package com.epam.star.entity;

@MappedEntityForAdmin("Position")
public class Position extends AbstractEntity {
    private String positionName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (positionName != null) {
            if (!(position.positionName.equals(positionName)))
                return false;
        } else {
            if (!(position.positionName == null))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        if (positionName != null) return positionName.hashCode();
        else return 0;
    }

    public String getPositionName() {

        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
