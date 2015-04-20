package com.epam.star.dao.H2dao;

import com.epam.star.dao.DiscountDao;
import com.epam.star.dao.MappedDao;
import com.epam.star.entity.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@MappedDao("Discount")
public class H2DiscountDao extends AbstractH2Dao implements DiscountDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_STATUS = "INSERT INTO DISCOUNT VALUES (?, ?, ?, ?)";
    private static final String FULL_DELETE_STATUS = "DELETE FROM DISCOUNT WHERE ID = ?";
    private static final String DELETE_STATUS = "UPDATE DISCOUNT SET  DISCOUNT.DELETED = ? WHERE DISCOUNT.ID = ?";
    private static final String UPDATE_STATUS = "UPDATE DISCOUNT SET DISCOUNT.ID = ?, DISCOUNT.NAME = ?," +
            " DISCOUNT.DISCOUNT_PERCENTAGE = ?, DISCOUNT.DELETED = ? WHERE DISCOUNT.ID = ?";

    private static final String NECESSARY_COLUMNS =
            " DISCOUNT.ID, DISCOUNT.NAME, DISCOUNT.PERCENTAGE ";

    private static final String ADDITIONAL_COLUMNS =
            " ,DISCOUNT.DELETED ";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM DISCOUNT";

    private static final String ID_FIELD = " DISCOUNT.ID, ";

    private static final String ORDER_BY = " ORDER BY NAME ";
    private static final String TABLE = "DISCOUNT";
    private static final String IMPORTANT_FILTER = "";


    protected H2DiscountDao() {
        super(null, null);
    }

    protected H2DiscountDao(Connection conn, DaoManager daoManager) {
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
    public Discount findByName(String name) throws DaoException {
        String sql = "SELECT * FROM DISCOUNT WHERE DISCOUNT.NAME = " + "'" + name + "'";
        Discount discount = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    discount = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Discount found by name successfully{}", discount);
        } catch (Exception e) {
            LOGGER.error("Error of Discount finding by name{}", e);
            throw new DaoException(e);
        }
        return discount;
    }

    @Override
    public Discount findByPercentage(Integer percentage) {
        String sql = "SELECT * FROM DISCOUNT WHERE DISCOUNT.DISCOUNT_PERCENTAGE = " + percentage;
        Discount discount = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    discount = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Discount found by ID successfully{}", discount);
        } catch (Exception e) {
            LOGGER.error("Error of Discount finding by ID{}", e);
            throw new DaoException(e);
        }
        return discount;
    }

    @Override
    public Discount findById(int ID) throws DaoException {
        String sql = "SELECT * FROM DISCOUNT WHERE ID = " + ID;
        Discount discount = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    discount = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Discount found by ID successfully{}", discount);
        } catch (Exception e) {
            LOGGER.error("Error of Discount finding by ID{}", e);
            throw new DaoException(e);
        }
        return discount;
    }

    @Override
    public Discount insert(Discount discount) {

        try (PreparedStatement prstm = conn.prepareStatement(ADD_STATUS)){
            prstm.setString(1, null);
            prstm.setString(2, discount.getName());
            prstm.setInt(3, discount.getPercentage());
            prstm.setBoolean(4, discount.isDeleted());
            prstm.execute();

            ResultSet generatedKeys = prstm.getGeneratedKeys();
            generatedKeys.next();
            discount.setId(generatedKeys.getInt("SCOPE_IDENTITY()"));
            LOGGER.info("Discount inserted successfully{}", discount);
        } catch (Exception e) {
            LOGGER.error("Error of Discount inserting{}", e);
            throw new DaoException(e);
        }
        return discount;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        String status = "Status do not deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_STATUS)){
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.execute();
            status = "Status deleted successfully ";
            LOGGER.info("Discount marked as deleted successfully{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Discount marking as deleted{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String updateEntity(Discount discount) {

        try (PreparedStatement prstm = conn.prepareStatement(UPDATE_STATUS)){
            prstm.setInt(1, discount.getId());
            prstm.setString(2, discount.getName());
            prstm.setInt(3, discount.getPercentage());
            prstm.setBoolean(4, discount.isDeleted());
            prstm.setInt(5, discount.getId());
            prstm.executeUpdate();
            LOGGER.info("Discount updated successfully{}", discount);
        } catch (Exception e) {
            LOGGER.error("Error of Discount updating{}", e);
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Discount getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Discount discount = new Discount();
        try {
            discount.setId(resultSet.getInt("id"));
            discount.setName(resultSet.getString("name"));
            discount.setPercentage(resultSet.getInt("discount_percentage"));
            discount.setDeleted(resultSet.getBoolean("deleted"));
            LOGGER.info("Discount creating from result set was successfully{}", discount);
        } catch (Exception e) {
            LOGGER.error("Error of Discount creating from result set{}", e);
            throw new DaoException(e);
        }
        return discount;
    }

    @Override
    public List<Discount> findAll() {
        String sql = "SELECT * FROM DISCOUNT";
        List<Discount> discounts = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next())
                    discounts.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All discounts were found{}", discounts);
        } catch (Exception e) {
            LOGGER.error("Error of Discounts finding{}", e);
            throw new DaoException(e);
        }
        return discounts;
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
