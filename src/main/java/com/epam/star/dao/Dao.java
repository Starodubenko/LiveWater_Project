package com.epam.star.dao;

import com.epam.star.dao.util.PaginatedList;
import com.epam.star.entity.AbstractEntity;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface Dao<T extends AbstractEntity> {

    public T findById(int ID);

    public T insert(T entity);

    public String deleteEntity(int ID);

    public String updateEntity(T entity);

    public List<T> findAll();

    public PaginatedList<T> findRangee(int pageNumber, int rowsCount, Map<String,String> fields, String entityName, String orderBy);

    void setConn(Connection connection);
    void setDaoManager(com.epam.star.dao.H2dao.DaoManager daoManager);

    public String getTableName();
}
