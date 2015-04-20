package com.epam.star.dao.H2dao;

import com.epam.star.dao.MappedDao;
import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@MappedDao("Position")
public class H2PositionDao extends AbstractH2Dao implements PositionDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2PositionDao.class);
    private static final String ADD_POSITION = "INSERT INTO  POSITIONS VALUES (?, ?, ?)";
    private static final String FULL_DELETE_POSITION = "DELETE FROM POSITIONS WHERE ID = ?";
    private static final String DELETE_POSITION = "UPDATE POSITIONS SET DELETED = ? WHERE ID = ?";
    private static final String UPDATE_PERIOD = "UPDATE POSITIONS SET ID = ?, POSITION_NAME = ?, DELETED = ? WHERE ID = ?";

    private static final String NECESSARY_COLUMNS =
            " POSITIONS.ID, POSITIONS.POSITION_NAME ";

    private static final String ADDITIONAL_COLUMNS =
            " ,POSITIONS.DELETED ";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM POSITIONS";

    private static final String ID_FIELD = " POSITIONS.ID, ";

    private static final String ORDER_BY = " ORDER BY POSITION_NAME";
    private static final String TABLE = "POSITIONS";
    private static final String IMPORTANT_FILTER = "";


    protected H2PositionDao() {
        super(null, null);
    }

    protected H2PositionDao(Connection conn, DaoManager daoManager) {
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
    public Position findByPositionName(String name) throws DaoException {
        String sql = "SELECT * FROM positions WHERE position_name = " + "'" + name + "'";
        Position position = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    position = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Period found by Position name successfully{}", position);
        } catch (Exception e) {
            LOGGER.error("Error of Period finding by Position name{}", e);
            throw new DaoException(e);
        }
        return position;
    }

    @Override
    public Position findById(int ID) throws DaoException {
        String sql = "SELECT * FROM positions WHERE id = " + ID;
        Position position = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    position = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Period found by ID successfully{}", position);
        } catch (Exception e) {
            LOGGER.error("Error of Period finding by ID{}", e);
            throw new DaoException(e);
        }
        return position;
    }

    @Override
    public Position insert(Position position) throws DaoException {

        try (PreparedStatement prstm = conn.prepareStatement(ADD_POSITION)){
            prstm.setString(1, null);
            prstm.setString(2, position.getPositionName());
            prstm.setBoolean(3, position.isDeleted());
            prstm.execute();

            ResultSet generatedKeys = prstm.getGeneratedKeys();
            generatedKeys.next();
            position.setId(generatedKeys.getInt("SCOPE_IDENTITY()"));
            LOGGER.info("Position added successfully{}", position);
        } catch (Exception e) {
            LOGGER.error("Error of Position adding{}", e);
            throw new DaoException(e);
        }
        return position;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        String status = "Position not marked as deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_POSITION)){
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.executeUpdate();
            status = "Position marked as deleted";
            LOGGER.info("Position marked as deleted successfully{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Position marking as deleted{}", e);
            throw new DaoException(e);
        }

        return status;
    }

    @Override
    public String updateEntity(Position position) throws DaoException {

        try (PreparedStatement prstm = conn.prepareStatement(UPDATE_PERIOD)){
            prstm.setInt(1, position.getId());
            prstm.setString(2, position.getPositionName());
            prstm.setBoolean(3, position.isDeleted());
            prstm.setInt(4, position.getId());
            prstm.executeUpdate();
            LOGGER.info("Position updated successfully{}", position);
        } catch (Exception e) {
            LOGGER.error("Error of Position updating{}", e);
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Position getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Position position = new Position();
        try {
            position.setId(resultSet.getInt("id"));
            position.setPositionName(resultSet.getString("position_name"));
            position.setDeleted(resultSet.getBoolean("deleted"));
            LOGGER.info("Position created from result set successfully{}", position);
        } catch (Exception e) {
            LOGGER.error("Error of Position creating from result set{}", e);
            throw new DaoException(e);
        }
        return position;
    }

    @Override
    public List<Position> findAll() {
        String sql = "SELECT * FROM positions";
        List<Position> positions = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next())
                    positions.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All Positions found successfully{}", positions);
        } catch (Exception e) {
            LOGGER.error("Error of Positions finding", e);
            throw new DaoException(e);
        }
        return positions;
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
