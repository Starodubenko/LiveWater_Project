package com.epam.star.dao.H2dao;

import com.epam.star.dao.Dao;
import com.epam.star.dao.util.PaginatedList;
import com.epam.star.dao.util.SearchResult;
import com.epam.star.dao.util.Searcher;
import com.epam.star.dao.util.UtilDao;
import com.epam.star.entity.AbstractEntity;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public abstract class AbstractH2Dao<T extends AbstractEntity, E extends AbstractH2Dao, K extends Dao> {
    protected static final UtilDao UTIL_DAO = new UtilDao();
    protected static final String LIMIT_OFFSET = " LIMIT ? OFFSET ? ";
    private static final Searcher SEARCHER = new Searcher();
    protected Connection conn;
    protected DaoManager daoManager;

    protected AbstractH2Dao(Connection conn, DaoManager daoManager) {
        this.conn = conn;
        this.daoManager = daoManager;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public DaoManager getDaoManager() {
        return daoManager;
    }

    public void setDaoManager(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PaginatedList<T> findRange(int pageNumber, int rowsCount, Map<String, String> fields, String entityName, String orderBy) {
        PaginatedList<T> result = new PaginatedList<>();

        String filterFields = getFilterRow(fields, getTableName());

        if (fields != null) {
            fields.remove("deleted");
            if (fields.get(orderBy) == null && orderBy != null && !orderBy.equals(""))
                fields.put(orderBy.trim().substring(0, orderBy.indexOf(" ")), "");//after get filter row, i put one more column there - orderBy
        }

        String joins = getJoinByFieldName(fields, getTableName());//and check all necessary columns for ref columns

        if (orderBy != null && !orderBy.equals("") && filterFields != null) orderBy = "ORDER BY " + orderBy;
        else orderBy = "";
        if (filterFields == null) filterFields = "";

        String pattern = "SELECT * FROM %s %s %s %s LIMIT ? OFFSET ?";

        if (!getImportantFilter().equals("")) {
            if (filterFields.equals(""))
                filterFields = filterFields + " WHERE " + getImportantFilter();
            else filterFields = filterFields + " AND " + getImportantFilter();
        }

        String sql = String.format(pattern, getTableName(), joins, filterFields, orderBy);

        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            prstm.setInt(1, rowsCount);
            prstm.setInt(2, rowsCount * pageNumber - rowsCount);
            try (ResultSet resultSet = prstm.executeQuery()) {
                while (resultSet.next())
                    result.add(getEntityFromResultSet(resultSet));
            }
            result.setTotalRowsCount(getCount(filterFields));
            result.setPageNumber(pageNumber);
            result.setRowsPerPage(rowsCount);
        } catch (Exception e) {
            throw new DaoException(e);
        }

        return result;
    }

    protected abstract String getImportantFilter();

    private String getJoinByFieldName(Map<String, String> fields, String fKTable) {

        if (fields != null) {
            Set<String> foundTables = new HashSet<>();

            String tableNamesString = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = ?";
            String backAddressTableString = "SELECT FKTABLE_NAME, PKTABLE_NAME, FKCOLUMN_NAME FROM INFORMATION_SCHEMA.CROSS_REFERENCES WHERE PKTABLE_NAME = ?";

            StringBuilder resultJoins = new StringBuilder();
            String fKTablePKColumn = "";
            String refTable = "";


            try (PreparedStatement prstm = conn.prepareStatement(tableNamesString)) {
                for (Map.Entry<String, String> field : fields.entrySet()) {
                    prstm.setString(1, field.getKey().toUpperCase());
                    try (ResultSet resultSet = prstm.executeQuery()) {
                        while (resultSet != null && resultSet.next())
                            foundTables.add(resultSet.getString("table_name"));
                    }
                }
            } catch (Exception e) {
                throw new DaoException(e);
            }

            try (PreparedStatement prstm = conn.prepareStatement(backAddressTableString)) {
                for (String table : foundTables) {
                    prstm.setString(1, table.toUpperCase());
                    try (ResultSet resultSet = prstm.executeQuery()) {
                        while (resultSet != null && resultSet.next())
                            if (resultSet.getString("FKTABLE_NAME").equals(fKTable)) {
                                refTable = resultSet.getString("PKTABLE_NAME");
                                fKTablePKColumn = resultSet.getString("FKCOLUMN_NAME");
                            }
                    }
                }
            } catch (Exception e) {
                throw new DaoException(e);
            }

            if (!refTable.equals("")) {
                resultJoins.append("INNER JOIN ")
                        .append(refTable)
                        .append(" ON ")
                        .append(refTable)
                        .append(".ID = ")
                        .append(fKTable)
                        .append(".")
                        .append(fKTablePKColumn);


                return resultJoins.toString();
            }
        }
        return "";
    }

    private String getFilterRow(Map<String, String> fields, String tableName) {

        StringBuilder sb = new StringBuilder("");
        if (fields != null)
            for (Map.Entry<String, String> field : fields.entrySet()) {
                if (sb.length() < 1) sb.append("where ");
                else sb.append(" and ");
                sb.append("lower(")
                        .append(tableName)
                        .append(".")
                        .append(field.getKey())
                        .append(")")
                        .append(" like ")
                        .append("lower(")
                        .append("'%")
                        .append(field.getValue())
                        .append("%'")
                        .append(")");
            }
        return sb.toString();
    }

    protected int getCount(String filterFields) {
        int count = 0;
        String queryString = "SELECT count(*) FROM " + getTableName() + " " + filterFields;

        try (PreparedStatement prstm = conn.prepareStatement(queryString)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return count;
    }

    public abstract String getTableName();

    public abstract T findById(int i);

//    public abstract String getFindByParameters(Boolean needAditionalColumns);
//
//    public abstract String getFindByParametersWithoutColumns();
//
//    public abstract String getNecessaryColumns();
//
//    public abstract String getAdditionalColumns();
//
//    public abstract String getIdField();
//
//    public abstract String getOrderBy();

    public String getLimitOffset() {
        return LIMIT_OFFSET;
    }

    public abstract T getEntityFromResultSet(ResultSet resultSet) throws DaoException, UnsupportedEncodingException;

    protected void closeStatement(PreparedStatement prstm, ResultSet resultSet) {
        if (prstm != null) {
            try {
                prstm.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    public abstract List<T> findAll();
}
