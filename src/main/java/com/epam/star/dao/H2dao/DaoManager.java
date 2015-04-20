package com.epam.star.dao.H2dao;

import com.epam.star.dao.ArticleDao;
import com.epam.star.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DaoManager implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private final Connection connection;
    private static Map<String, Dao> daosMap = new HashMap<>();

    public DaoManager(Connection connection, Map<String, Dao> daosMap) {
        this.connection = connection;
        this.daosMap = daosMap;
    }

    public Connection getConnection() {
        return connection;
    }

    public void beginTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void closeConnection() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Dao getDao(String name) {

        Dao dao = daosMap.get(name);

        if (dao != null) {
            dao.setConn(connection);
            dao.setDaoManager(this);
        }
        return dao;
    }

    public H2ClientDao getClientDao() {
        return new H2ClientDao(connection, this);
    }

    public H2ContactDao getContactDao() {

        return new H2ContactDao(connection, this);
    }

    public H2EmployeeDao getEmployeeDao() {
        return new H2EmployeeDao(connection, this);
    }

    public H2PeriodDao getPeriodDao() {
        return new H2PeriodDao(connection, this);
    }

    public H2StatusDao getStatusDao() {
        return new H2StatusDao(connection, this);
    }

    public H2GoodsDao getGoodsDao() {
        return new H2GoodsDao(connection, this);
    }

    public H2PayCardDao getPayCardDao() {
        return new H2PayCardDao(connection, this);
    }

    public H2PayCardStatusDao getPayCardStatusDao() {
        return new H2PayCardStatusDao(connection, this);
    }

    public H2PositionDao getPositionDao() {
        return new H2PositionDao(connection, this);
    }

    public H2ImageDao getImageDao() {
        return new H2ImageDao(connection, this);
    }

    public H2DiscountDao getDiscountDao() {
        return new H2DiscountDao(connection, this);
    }

    public H2OrderedGoodsDao getOrderedGoodsDao() {
        return new H2OrderedGoodsDao(connection, this);
    }

    public H2OrderDao2 getOrderDao2() {
        return new H2OrderDao2(connection, this);
    }

    public H2ArticleDao getArticleDao() {
        return new H2ArticleDao(connection,this);
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
