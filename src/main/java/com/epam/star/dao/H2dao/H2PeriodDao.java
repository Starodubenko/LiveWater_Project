package com.epam.star.dao.H2dao;

import com.epam.star.dao.MappedDao;
import com.epam.star.dao.PeriodDao;
import com.epam.star.entity.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@MappedDao("Period")
public class H2PeriodDao extends AbstractH2Dao implements PeriodDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_PERIOD = "INSERT INTO period VALUES (?, ?, ?, ?)";
    private static final String FULL_DELETE_PERIOD = "DELETE FROM PERIOD WHERE ID = ?";
    private static final String DELETE_PERIOD = "UPDATE period SET DELETED = ? WHERE id = ?";
    private static final String UPDATE_PERIOD = "UPDATE period SET id = ?, period = ?, DESCRIBE = ?, DELETED = ? WHERE id = ?";

    private static final String NECESSARY_COLUMNS =
            " PERIOD.ID, PERIOD.PERIOD ";

    private static final String ADDITIONAL_COLUMNS =
            " ,PERIOD.DELETED ";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM PERIOD";

    private static final String ID_FIELD = " PERIOD.ID, ";

    private static final String ORDER_BY = " ORDER BY PERIOD";
    private static final String TABLE = "PERIOD";
    private static final String IMPORTANT_FILTER = "";


    protected H2PeriodDao() {
        super(null, null);
    }

    protected H2PeriodDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    protected String getImportantFilter() {
        return IMPORTANT_FILTER;
    }

    @Override
    public String getTableName() {
        return TABLE;
    }

    public List<Period> getAllPeriods() {
        String sql = "SELECT * FROM PERIOD";
        List<Period> periods = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next())
                    periods.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All Periods found successfully{}", periods);
        } catch (Exception e) {
            LOGGER.error("Error of Periods finding", e);
            throw new DaoException(e);
        }
        return periods;
    }

    @Override
    public Period findByPeriod(Time period) throws DaoException {

        String sql = "SELECT * FROM period WHERE period = " + "'" + period + "'";
        Period periodResult = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    periodResult = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Period found by period successfully{}", periodResult);
        } catch (Exception e) {
            LOGGER.error("Error of Period finding by period{}", e);
            throw new DaoException(e);
        }
        return periodResult;
    }

    @Override
    public Period findById(int ID) throws DaoException {
        String sql = "SELECT * FROM period WHERE id = " + ID;
        Period period = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    period = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Period found by ID successfully{}", period);
        } catch (Exception e) {
            LOGGER.error("Error of Period finding by ID{}", e);
            throw new DaoException(e);
        }
        return period;
    }

    @Override
    public Period insert(Period period) throws DaoException {

        try (PreparedStatement prstm = conn.prepareStatement(ADD_PERIOD)){
            prstm.setString(1, null);
            prstm.setTime(2, period.getPeriod());
            prstm.setString(3, period.getDescribe());
            prstm.setBoolean(4, period.isDeleted());
            prstm.execute();

            ResultSet generatedKeys = prstm.getGeneratedKeys();
            generatedKeys.next();
            period.setId(generatedKeys.getInt("SCOPE_IDENTITY()"));
            LOGGER.info("Period added successfully{}", period);
        } catch (Exception e) {
            LOGGER.error("Error of Period adding{}", e);
            throw new DaoException(e);
        }
        return period;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        String status = "Period not marked as deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_PERIOD)){
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.executeUpdate();
            status = "Period marked as deleted";
            LOGGER.info("Period marked as deleted successfully{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Period marking as deleted{}", e);
            throw new DaoException(e);
        }

        return status;
    }

    @Override
    public String updateEntity(Period period) throws DaoException {
        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(UPDATE_PERIOD);
            prstm.setInt(1, period.getId());
            prstm.setTime(2, period.getPeriod());
            prstm.setString(3, period.getDescribe());
            prstm.setBoolean(4, period.isDeleted());
            prstm.setInt(5, period.getId());
            prstm.executeUpdate();
            LOGGER.info("Period updated successfully{}", period);
        } catch (Exception e) {
            LOGGER.error("Error of Period updating{}", e);
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return null;
    }

    @Override
    public Period getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Period period = new Period();
        try {
            period.setId(resultSet.getInt("id"));
            period.setPeriod(resultSet.getTime("period"));
            period.setDescribe(resultSet.getString("describe"));
            period.setDeleted(resultSet.getBoolean("deleted"));
            LOGGER.info("Period created from result set successfully{}", period.getPeriod());
        } catch (Exception e) {
            LOGGER.error("Error of Period creating from result set{}", e);
            throw new DaoException(e);
        }
        return period;
    }

    @Override
    public List<Period> findAll() {
        String sql = "SELECT * FROM PERIOD";
        List<Period> periods = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next())
                    periods.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All Periods found successfully{}", periods);
        } catch (Exception e) {
            LOGGER.error("Error of Periods finding", e);
            throw new DaoException(e);
        }
        return periods;
    }

    @Override
    public String getFindByParameters(Boolean needAditionalColumns) {

        String columns = NECESSARY_COLUMNS;

        if (needAditionalColumns == true){
            columns = columns + ADDITIONAL_COLUMNS;
        }

        String result = String.format(FIND_BY_PARAMETERS_WITHOUT_COLUMNS,columns);

        result = String.format(result+"%s", ORDER_BY);
        result = String.format(result+"%s", LIMIT_OFFSET);

        return result;
    }

    @Override
    public String getFindByParametersWithoutColumns() {
        return FIND_BY_PARAMETERS_WITHOUT_COLUMNS;
    }

    @Override
    public String getNecessaryColumns() {
        return NECESSARY_COLUMNS;
    }

    @Override
    public String getAdditionalColumns() {
        return ADDITIONAL_COLUMNS;
    }

    @Override
    public String getIdField() {
        return ID_FIELD;
    }

    @Override
    public String getOrderBy() {
        return ORDER_BY;
    }
}
