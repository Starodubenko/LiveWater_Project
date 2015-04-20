package com.epam.star.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static final int MAX_POOL_SIZE = 10000;
    private static int CURRENT_POOL_SIZE;
    private static List<Connection> connectionList = new ArrayList<>(MAX_POOL_SIZE);

    public ConnectionPool() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnectionFromPool(String url, String userName, String password) throws SQLException {

        if (connectionList.isEmpty()) {
            if (CURRENT_POOL_SIZE >= MAX_POOL_SIZE) {
                System.out.println("Maximum pool size is reaced. Can not create connection");
            }
            CURRENT_POOL_SIZE++;
            return DriverManager.getConnection(url, userName, password);
        } else {
            CURRENT_POOL_SIZE--;
            return connectionList.remove(0);
        }
    }

    public static synchronized void addConnectionToPool(Connection conn) {
        connectionList.add(conn);
    }
}
