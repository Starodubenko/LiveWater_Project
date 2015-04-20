package com.epam.star.action;

import java.sql.SQLException;
import java.text.ParseException;

public class ActionException extends RuntimeException{
    private int detail;

    public ActionException(int detail) {
        this.detail = detail;
    }

    public ActionException(SQLException e) {
        super(e);
    }

    public ActionException(ParseException e) {
    }

    public ActionException(Exception e) {

    }

    @Override
    public String toString() {
        return "ActionException{" +
                "detail=" + detail +
                '}';
    }
}
