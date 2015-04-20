package com.epam.star.dao.H2dao;

import com.epam.star.dao.MappedDao;
import com.epam.star.dao.PayCardStatusDao;
import com.epam.star.entity.StatusPayCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@MappedDao("StatusPayCard")
public class H2PayCardStatusDao extends AbstractH2Dao implements PayCardStatusDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_STATUS_PAY_CARD = "INSERT INTO status_card VALUES (?, ?, ?)";
    private static final String FULL_DELETE_STATUS_PAY_CARD = "DELETE FROM STATUS_CARD WHERE ID = ?";
    private static final String DELETE_STATUS_PAY_CARD = "UPDATE STATUS_CARD SET DELETED = ? WHERE ID = ?";
    private static final String UPDATE_STATUS_PAY_CARD = "UPDATE STATUS_CARD SET ID = ?, STATUS_NAME = ?, DELETED = ? WHERE ID = ?";

    private static final String NECESSARY_COLUMNS =
            " STATUS_CARD.ID, STATUS_CARD.STATUS_NAME ";

    private static final String ADDITIONAL_COLUMNS =
            "";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM STATUS_CARD";

    private static final String ID_FIELD = " STATUS_CARD.ID, ";

    private static final String ORDER_BY = " ORDER BY STATUS_NAME";
    private static final String TABLE = "STATUS_CARD";
    private static final String IMPORTANT_FILTER = "";


    protected H2PayCardStatusDao() {
        super(null, null);
    }

    protected H2PayCardStatusDao(Connection conn, DaoManager daoManager) {
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
    public StatusPayCard findByStatusName(String name) throws DaoException {
        String sql = "SELECT * FROM status_card WHERE status_name = " + "'" + name + "'";
        StatusPayCard statusPayCard = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    statusPayCard = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Status pay card found by status name successfully{}", statusPayCard);
        } catch (Exception e) {
            LOGGER.error("Error of Status pay card finding by status name{}", e);
            throw new DaoException(e);
        }
        return statusPayCard;
    }

    @Override
    public List<StatusPayCard> findAll() {
        String sql = "SELECT * FROM STATUS_CARD";
        List<StatusPayCard> statusPayCards = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next())
                    statusPayCards.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All Statuses pay cards found successfully{}", statusPayCards);
        } catch (Exception e) {
            LOGGER.error("Error of Statuses pay cards finding", e);
            throw new DaoException(e);
        }
        return statusPayCards;
    }

    @Override
    public StatusPayCard findById(int ID) throws DaoException {
        String sql = "SELECT * FROM status_card WHERE id = " + ID;
        StatusPayCard statusPayCard = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    statusPayCard = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Pay card found by ID successfully{}", statusPayCard);
        } catch (Exception e) {
            LOGGER.error("Error of Pay card finding by ID{}", e);
            throw new DaoException(e);
        }
        return statusPayCard;
    }

    @Override
    public StatusPayCard insert(StatusPayCard statusPayCard) throws DaoException {

        try (PreparedStatement prstm = conn.prepareStatement(ADD_STATUS_PAY_CARD)){
            prstm.setString(1, null);
            prstm.setString(2, statusPayCard.getStatusName());
            prstm.setBoolean(3, statusPayCard.isDeleted());
            prstm.execute();

            ResultSet generatedKeys = prstm.getGeneratedKeys();
            generatedKeys.next();
            statusPayCard.setId(generatedKeys.getInt("SCOPE_IDENTITY()"));
            LOGGER.info("Pay card status added successfully{}", statusPayCard);
        } catch (Exception e) {
            LOGGER.error("Error of Pay card status adding{}", e);
            throw new DaoException(e);
        }
        return statusPayCard;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        String status = "Status pay card not marked as deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_STATUS_PAY_CARD)){
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.executeUpdate();
            status = "Status pay card marked as deleted";
            LOGGER.info("Pay card status marked as deleted successfully{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Pay card status marking as deleted{}", e);
            throw new DaoException(e);
        }

        return status;
    }

    @Override
    public String updateEntity(StatusPayCard statusPayCard) throws DaoException {

        try (PreparedStatement prstm = conn.prepareStatement(UPDATE_STATUS_PAY_CARD)){
            prstm.setInt(1, statusPayCard.getId());
            prstm.setString(2, statusPayCard.getStatusName());
            prstm.setBoolean(3, statusPayCard.isDeleted());
            prstm.setInt(4, statusPayCard.getId());
            prstm.executeUpdate();
            LOGGER.info("Pay card status updated successfully{}", statusPayCard);
        } catch (Exception e) {
            LOGGER.error("Error of Pay card status updating{}", e);
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public StatusPayCard getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        StatusPayCard statusPayCard = new StatusPayCard();
        try {
            statusPayCard.setId(resultSet.getInt("id"));
            statusPayCard.setStatusName(resultSet.getString("status_name"));
            statusPayCard.setDeleted(resultSet.getBoolean("deleted"));
            LOGGER.info("Pay card status created from result set successfully{}", statusPayCard);
        } catch (Exception e) {
            LOGGER.error("Error of Pay card status creating from result set{}", e);
            throw new DaoException(e);
        }
        return statusPayCard;
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
