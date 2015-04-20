package com.epam.star.dao.util;

import com.epam.star.dao.H2dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UtilDao {
    public Integer getIntValue(String fieldName, HttpServletRequest request) {
        String pageString = request.getParameter(fieldName);
        if (pageString != null && pageString.matches("[\\d]{1,9}")) return Integer.valueOf(pageString);
        else {
            pageString = (String) request.getAttribute(fieldName);
            if (pageString != null && pageString.matches("[\\d]{1,9}")) return Integer.valueOf(pageString);
            if (pageString == null) {
                pageString = (String) request.getSession().getAttribute(fieldName);
                if (pageString != null && pageString.matches("[\\d]{1,9}")) return Integer.valueOf(pageString);
            }
        }
        return null;
    }

    public Integer getIntValue(String value) {
        if (value != null && value.matches("[\\d]{1,9}")) return Integer.valueOf(value);
        return null;
    }

    public Date getDateValue(String fieldName, HttpServletRequest request) {
        String dateString = request.getParameter(fieldName);
        if (dateString != null && dateString.matches("([0-2]\\d|3[01])[\\.\\-](0\\d|1[012])[\\.\\-](\\d{4})"))
            try {
                return new Date(new SimpleDateFormat("dd.MM.yyyy").parse(dateString).getTime());
            } catch (ParseException e) {
                throw new DaoException(e);
            }
        return null;
    }

    public Date getDateValue(String dateString) {
        List<String> dateformats = new ArrayList<>();
        dateformats.add("dd.MM.yyyy");
        dateformats.add("dd-MM-yyyy");
        if (dateString != null && dateString.matches("([0-2]\\d|3[01])[\\.\\-](0\\d|1[012])[\\.\\-](\\d{4})"))
            for (String dateformat : dateformats) {
                try {
                    return new Date(new SimpleDateFormat(dateformat).parse(dateString).getTime());
                } catch (ParseException e) {
                    if (dateformat.equals(dateformats.get(dateformats.size() - 1)))
                        throw new DaoException(e);
                }
            }
        return null;
    }

    public Time getTimeValue(String timeString) {
        if (timeString != null && timeString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]|:[0-5][0-9]:[0-5][0-9])$")) {
            if (timeString.length() == 5) timeString = timeString + ":00";
            return Time.valueOf(timeString);
        }
        return null;
    }

    public String getString(String fieldName, HttpServletRequest request) {
        String string = request.getParameter(fieldName);
        if (string == null) string = (String) request.getAttribute(fieldName);
        if (string == null) string = (String) request.getSession().getAttribute(fieldName);
        return string;
    }

    public String getString(String string) {
        return string.trim();
    }

}
