package com.epam.star.dao.util;

import com.epam.star.dao.H2dao.AbstractH2Dao;
import com.epam.star.dao.H2dao.DaoException;
import com.epam.star.entity.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class Searcher<T extends AbstractEntity, E extends AbstractH2Dao> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Searcher.class);
    private static final UtilDao utilDao = new UtilDao();
    private Connection conn;

    public static Map sortByValue(Map unsortMap) {
        List list = new LinkedList(unsortMap.entrySet());

        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                int i = ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
                return i * -1;
            }
        });

        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    private List<Object> parseSearchString(String searchString) {
        List<Object> findingValues = new ArrayList<>();
        String[] value = searchString.split(" ");

        Object val = null;

        for (String s : value) {
            val = utilDao.getIntValue(s);
            if (val != null) findingValues.add(s);
            if (val == null) val = utilDao.getDateValue(s);
            if (val == null) val = utilDao.getTimeValue(s);
            if (val == null) val = s;
            findingValues.add(val);
        }
        return findingValues;
    }

    private String getTableNamesString(List<String> query) {

        StringBuilder tableNames = new StringBuilder();

        for (String name : query) {
            if (!name.equals("ID")) {
                tableNames.append(", ");
                tableNames.append(name);
            }
        }

        return tableNames.toString().substring(tableNames.toString().indexOf(" ") + 1);
    }

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

    private List<String> getNecessaryColumns(Object value, String findByParameters) {
        List<String> columns = new ArrayList<>();

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        ResultSetMetaData resultSetMetaData;
        try {
            findByParameters = String.format(findByParameters, "");// clear the %s in findByParameters

            prstm = conn.prepareStatement(findByParameters);
            prstm.setInt(1, 0);
            prstm.setInt(2, 0);
            resultSet = prstm.executeQuery();
            resultSetMetaData = resultSet.getMetaData();

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                String columnClassName = resultSetMetaData.getColumnClassName(i);
                String necessaryType = value.getClass().getName();
                if (necessaryType.equals(columnClassName)) {
                    columns.add(resultSetMetaData.getColumnName(i));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }

        return columns;
    }

    private int getFoundEntitiesCount(String queryString) {
        int count = 0;
        queryString = "SELECT count(*) " + queryString.toUpperCase().substring(queryString.indexOf("FROM"),queryString.indexOf("ORDER BY"));

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(queryString);
            resultSet = prstm.executeQuery();
            while (resultSet.next())
                count = resultSet.getInt("count(*)");
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return count;
    }

    public SearchResult find(String searchString, E genericDao, int rowsCount, int firstRow) {

        conn = genericDao.getConn();
        SearchResult result = new SearchResult();
        Map<Integer, Integer> entitiesProbability = new LinkedHashMap<>();

        if (searchString != null && !searchString.equals("")) {

            List<Object> values = parseSearchString(searchString);

            String findByParameters = genericDao.getFindByParameters(false);

            String queryString;
            for (Object value : values) {

                List<String> necessaryColumns = getNecessaryColumns(value, findByParameters);
                String tableNamesString = getTableNamesString(necessaryColumns);

                String findByParametersWithoutColumns = genericDao.getFindByParametersWithoutColumns();
                String idField;
                if (necessaryColumns.size() == 1 && necessaryColumns.get(0).equals("ID")) {
                    idField = genericDao.getIdField().replace(",", "");
                } else idField = genericDao.getIdField();
                findByParametersWithoutColumns = String.format(findByParametersWithoutColumns, idField + " %s "); //add compulsory field
                queryString = String.format(findByParametersWithoutColumns, tableNamesString);
                queryString = String.format(queryString + " %s", "%s " + genericDao.getOrderBy() +" "+ genericDao.getLimitOffset());


                StringBuilder afterWherePartString = new StringBuilder();
                for (String name : necessaryColumns) {
                    afterWherePartString.append(" or ");
                    if (!name.equals("ID")) afterWherePartString.append("lower(" + name + ")");
                    else afterWherePartString.append(genericDao.getIdField().replace(",", ""));
                    afterWherePartString.append(" like lower('%" + value + "%')");
                }

                queryString = String.format(queryString, " where " + afterWherePartString.toString().substring(afterWherePartString.toString().indexOf("or") + 3));

                PreparedStatement prstm = null;
                ResultSet resultSet = null;
                try {
                    prstm = conn.prepareStatement(queryString);
                    prstm.setInt(1, rowsCount);
                    prstm.setInt(2, firstRow);
                    resultSet = prstm.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        if (entitiesProbability.get(id) != null) {
                            entitiesProbability.replace(id, entitiesProbability.get(id) + 1);
                        } else {
                            entitiesProbability.put(id, 1);
                        }
                    }

                    result.setTotalFoundEntitiesCount(getFoundEntitiesCount(queryString));
                } catch (SQLException e) {
                    throw new DaoException(e);
                } finally {
                    closeStatement(prstm, resultSet);
                }
            }
        } else {

            String queryString = genericDao.getFindByParameters(false);
            queryString = String.format(queryString, ""); //clear %s

            PreparedStatement prstm = null;
            ResultSet resultSet = null;
            try {
                prstm = conn.prepareStatement(queryString);
                prstm.setInt(1, rowsCount);
                prstm.setInt(2, firstRow);
                resultSet = prstm.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    if (entitiesProbability.get(id) != null) {
                        entitiesProbability.replace(id, entitiesProbability.get(id) + 1);
                    } else {
                        entitiesProbability.put(id, 1);
                    }
                }
                result.setTotalFoundEntitiesCount(getFoundEntitiesCount(queryString));
            } catch (Exception e) {
                LOGGER.error("Error during searching the entity",e);
                throw new DaoException(e);
            } finally {
                closeStatement(prstm, resultSet);
            }
        }

//        result.setFoundEntitiesMap(sortByValue(entitiesProbability));
        result.setFoundEntitiesMap(entitiesProbability);
        return result;
    }
}
