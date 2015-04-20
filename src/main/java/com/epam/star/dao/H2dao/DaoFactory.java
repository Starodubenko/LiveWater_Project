package com.epam.star.dao.H2dao;

import com.epam.star.dao.Dao;
import com.epam.star.dao.MappedDao;
import com.epam.star.util.PropertiesManager;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DaoFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DaoFactory.class);
    private final BoneCP connectionPool;
    private static PropertiesManager jdbcProperties;
    private static Map<String, Dao> daosMap = new HashMap<>();

    private DaoFactory() throws DaoException {

        try {
            jdbcProperties = new PropertiesManager("connection.properties");
        } catch (IOException e) {
            throw new DaoException(e);
        }

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new DaoException(e);
        }

        BoneCPConfig config = new BoneCPConfig();

        config.setJdbcUrl(jdbcProperties.getProperty("jdbc.url"));
        config.setUsername(jdbcProperties.getProperty("user.name"));
        config.setPassword(jdbcProperties.getProperty("password"));
        config.setMaxConnectionsPerPartition(20);

        try {
            connectionPool = new BoneCP(config);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        Reflections reflections = new Reflections(DaoFactory.class.getPackage().getName());
        Set<Class<?>> daos = reflections.getTypesAnnotatedWith(MappedDao.class);

        for (Class<?> daoClass : daos) {
            MappedDao mappedDao = daoClass.getAnnotation(MappedDao.class);

            Object dao = null;
            try {
                dao = daoClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error(e.toString());
            }
            Dao abstractDao = (Dao) dao;
            daosMap.put(mappedDao.value().toLowerCase(), abstractDao);
        }
    }

    public void destroy(){
        connectionPool.shutdown();
    }

    public static DaoFactory getInstance() {
        return InstanceHolder.instance;
    }

    public DaoManager getDaoManager() throws DaoException {
        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return new DaoManager(connection, daosMap);
    }

    private static class InstanceHolder {
        private static DaoFactory instance = new DaoFactory();
    }

}

