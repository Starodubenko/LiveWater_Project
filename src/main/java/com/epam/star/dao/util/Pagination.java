package com.epam.star.dao.util;

import com.epam.star.dao.Dao;
import com.epam.star.dao.H2dao.AbstractH2Dao;
import com.epam.star.dao.H2dao.DaoException;
import com.epam.star.entity.AbstractEntity;
import com.epam.star.util.PropertiesManager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class Pagination<T extends AbstractEntity, E extends AbstractH2Dao, K extends Dao> {
    private static PropertiesManager jdbcProperties;

    public static final int DEFAULT_PAGE_NUMBER;
    public static final int DEFAULT_ROWS_COUNT;

    private static final UtilDao utilDao = new UtilDao();

    static {
        try {
            jdbcProperties = new PropertiesManager("pagination.properties");
        } catch (IOException e) {
            throw new DaoException(e);
        }
        DEFAULT_PAGE_NUMBER = jdbcProperties.getIntProperty("default.page.number");
        DEFAULT_ROWS_COUNT = jdbcProperties.getIntProperty("default.rows.count");
    }

    public PaginatedList<T> paginationEntity(HttpServletRequest request, K genericDao, Map<String, String> fields) throws DaoException {

        int rowsCount = DEFAULT_ROWS_COUNT;
        int pageNumber = DEFAULT_PAGE_NUMBER;

        String orderBy = utilDao.getString("orderBy", request);
        if (orderBy != null && (orderBy.contains("date") || orderBy.contains("time"))){
            String orderByType = orderBy.substring(orderBy.indexOf(" "), orderBy.length());
            orderBy = orderBy + ", " + genericDao.getTableName() + ".id" + orderByType;
        }


        if (utilDao.getIntValue("page", request) != null)
            pageNumber = utilDao.getIntValue("page", request);
        if (utilDao.getIntValue("rows", request) != null)
            rowsCount = utilDao.getIntValue("rows", request);


        return  genericDao.findRange(pageNumber, rowsCount, fields, "", orderBy);
    }
}
