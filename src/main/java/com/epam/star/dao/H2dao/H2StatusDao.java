package com.epam.star.dao.H2dao;

import com.epam.star.dao.MappedDao;
import com.epam.star.dao.StatusDao;
import com.epam.star.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@MappedDao("Status")
public class H2StatusDao extends AbstractH2Dao implements StatusDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_STATUS = "INSERT INTO STATUS VALUES (?, ?, ?)";
    private static final String FULL_DELETE_STATUS = "DELETE FROM STATUS WHERE ID = ?";
    private static final String DELETE_STATUS = "UPDATE STATUS SET DELETED = ? WHERE ID = ?";
    private static final String UPDATE_STATUS = "UPDATE STATUS SET ID = ?, STATUS_NAME = ?, DELETED = ? WHERE ID = ?";

    private static final String NECESSARY_COLUMNS =
            " STATUS.ID, STATUS.STATUS_NAME ";

    private static final String ADDITIONAL_COLUMNS =
            " ,STATUS.DELETED ";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM STATUS";

    private static final String ID_FIELD = " STATUS.ID, ";

    private static final String ORDER_BY = " ORDER BY STATUS_NAME";
    private static final String TABLE = "STATUS";
    private static final String IMPORTANT_FILTER = "";


    protected H2StatusDao() {
        super(null, null);
    }

    protected H2StatusDao(Connection conn, DaoManager daoManager) {
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

    @Override
    public Status findByStatusName(String name) throws DaoException {
        String sql = "SELECT * FROM status WHERE status_name = " + "'" + name + "'";
        Status status = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    status = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Status found by status name successfully{}", status);
        } catch (Exception e) {
            LOGGER.error("Error of Status finding by status name{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public Status findById(int ID) throws DaoException {
        String sql = "SELECT * FROM status WHERE id = " + ID;
        Status status = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    status = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Status found by ID successfully{}", status);
        } catch (Exception e) {
            LOGGER.error("Error of Status finding by ID{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public Status insert(Status status) throws DaoException {

        try (PreparedStatement prstm = conn.prepareStatement(ADD_STATUS)){
            prstm.setString(1, null);
            prstm.setString(2, status.getStatusName());
            prstm.setBoolean(3, status.isDeleted());
            prstm.execute();

            ResultSet generatedKeys = prstm.getGeneratedKeys();
            generatedKeys.next();
            status.setId(generatedKeys.getInt("SCOPE_IDENTITY()"));
            LOGGER.info("Status added successfully{}", status);
        } catch (Exception e) {
            LOGGER.error("Error of Status adding{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        String status = "Status not marked as deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_STATUS)){
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.executeUpdate();
            status = "Status marked as deleted";
            LOGGER.info("Status marked as deleted{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Status marking as deleted{}", e);
            throw new DaoException(e);
        }

        return status;
    }

    @Override
    public String updateEntity(Status status) throws DaoException {

        try (PreparedStatement prstm = conn.prepareStatement(UPDATE_STATUS)){
            prstm.setInt(1, status.getId());
            prstm.setString(2, status.getStatusName());
            prstm.setBoolean(3, status.isDeleted());
            prstm.setInt(4, status.getId());
            prstm.executeUpdate();
            LOGGER.info("Status updated successfully{}", status);
        } catch (Exception e) {
            LOGGER.error("Error of Status updating{}", e);
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Status getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Status status = new Status();
        try {
            status.setId(resultSet.getInt("id"));
            status.setStatusName(resultSet.getString("status_name"));
            status.setDeleted(resultSet.getBoolean("deleted"));
            LOGGER.info("Status created from result set successfully{}", status);
        } catch (Exception e) {
            LOGGER.error("Error of Status creating from result set{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public List<Status> findAll() {
        String sql = "SELECT * FROM STATUS";
        List<Status> statuses = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next())
                    statuses.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All Statuses found successfully{}", statuses);
        } catch (Exception e) {
            LOGGER.error("Error of Statuses finding", e);
            throw new DaoException(e);
        }
        return statuses;
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
