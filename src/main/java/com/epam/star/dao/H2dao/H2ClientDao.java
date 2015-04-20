package com.epam.star.dao.H2dao;

import com.epam.star.dao.ClientDao;
import com.epam.star.dao.MappedDao;
import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@MappedDao("Client")
public class H2ClientDao extends AbstractH2Dao implements ClientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_CLIENT = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    private static final String RANGE_CLIENT = "SELECT * FROM users LIMIT ? OFFSET ?";
    private static final String UPDATE_CLIENT = "UPDATE USERS SET ID = ?, LOGIN = ?, PASSWORD = ?, FIRSTNAME = ?, " +
            "LASTNAME = ?, MIDDLENAME = ?, ADDRESS = ?, TELEPHONE = ?, MOBILEPHONE = ?," +
            "POSITION_ID = ?, VIRTUAL_BALANCE = ?, AVATAR = ?, DISCOUNT = ?, DELETED = ? WHERE ID = ?";
    private static final String DELETE_CLIENT = "UPDATE USERS SET DELETED = ? WHERE ID = ?";


    private static final String NECESSARY_COLUMNS =
            " USERS.ID, USERS.LOGIN, USERS.PASSWORD, USERS.FIRSTNAME, USERS.LASTNAME, " +
                    "USERS.MIDDLENAME, USERS.ADDRESS, USERS.TELEPHONE, USERS.MOBILEPHONE, " +
                    "POSITIONS.POSITION_NAME";

    private static final String ADDITIONAL_COLUMNS =
            " ,USERS.VIRTUAL_BALANCE, USERS.AVATAR, USERS.DISCOUNT, USERS.DELETED";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s" +
                    " FROM USERS" +
                    " INNER JOIN POSITIONS" +
                    " ON USERS.POSITION_ID = POSITIONS.ID";

    private static final String ID_FIELD = " USERS.ID, ";

    private static final String ORDER_BY = " ORDER BY LASTNAME ";
    private static final String TABLE = "USERS";
    private static final String IMPORTANT_FILTER = "";

    protected H2ClientDao() {
        super(null, null);
    }

    protected H2ClientDao(Connection conn, DaoManager daoManager) {
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
    public Client findByLogin(String login) throws DaoException {
        String sql = "select * from USERS where login = " + "'" + login + "'";

        Client client = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next())
                    client = getEntityFromResultSet(resultSet);
            }
            LOGGER.info("Client found by login{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client finding by login{}", e);
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public Client findByName(String name) throws DaoException {
        String sql = "select * from USERS where firstname = " + "'" + name + "'";

        Client client = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next())
                    client = getEntityFromResultSet(resultSet);
            }
            LOGGER.info("Client found by name{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client finding by name{}", e);
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public Client findBySurname(String surName) throws DaoException {
        String sql = "select * from USERS where surname = " + "'" + surName + "'";

        Client client = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next())
                    client = getEntityFromResultSet(resultSet);
            }
            LOGGER.info("Client found by surname{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client finding by surname{}", e);
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public Client findByAddress(String address) throws DaoException {
        String sql = "select * from USERS where address= " + "'" + address + "'";

        Client client = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next())
                    client = getEntityFromResultSet(resultSet);
            }
            LOGGER.info("Client found by address{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client finding by address{}", e);
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public Client findByTelephone(String telephone) throws DaoException {
        String sql = "select * from USERS where telephone = " + "'" + telephone + "'";

        Client client = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next())
                    client = getEntityFromResultSet(resultSet);
            }
            LOGGER.info("Client found by telephone{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client finding by telephone{}", e);
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public Client findByMobilephone(String mobilephone) throws DaoException {
        String sql = "select * from USERS where surname = " + "'" + mobilephone + "'";

        Client client = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next())
                    client = getEntityFromResultSet(resultSet);
            }
            LOGGER.info("Client found by mobilephone{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client finding by mobilephone{}", e);
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public Client findByCredentials(String login, String password) throws DaoException {
        String sql = "SELECT *" +
                " FROM USERS" +
                " where POSITION_ID = 11 and Lower(LOGIN) = " + "Lower('" + login + "')" + " and PASSWORD = " + "'" + password + "'";

        Client client = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next())
                    client = getEntityFromResultSet(resultSet);
            }
            LOGGER.info("Client found by credentials{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client finding by credentials{}", e);
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public boolean alreadyExist(String login) {

        String sql = "SELECT LOGIN FROM USERS where Lower(LOGIN) = " + "Lower('" + login + "')";
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                LOGGER.info("Client is already exist{}", resultSet.next());
                return resultSet.next();
            }
        } catch (Exception e) {
            LOGGER.error("Error of Client finding by exist{}", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Client findById(int ID) throws DaoException {
        String sql = "select * from users where id = " + ID;

        Client client = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next())
                    client = getEntityFromResultSet(resultSet);
            }
            LOGGER.info("Client found by ID{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client finding by ID{}", e);
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public Client insert(Client client) {
        String status = "Client do not added";

        try (PreparedStatement prstm = conn.prepareStatement(ADD_CLIENT)) {
            prstm.setString(1, null);
            prstm.setString(2, client.getLogin());
            prstm.setString(3, client.getPassword());
            prstm.setString(4, client.getFirstName());
            prstm.setString(5, client.getLastName());
            prstm.setString(6, client.getMiddleName());
            prstm.setString(7, client.getAddress());
            prstm.setString(8, client.getTelephone());
            prstm.setString(9, client.getMobilephone());
            prstm.setString(10, null);
            prstm.setString(11, null);
            prstm.setString(12, null);
            prstm.setString(13, null);
            prstm.setInt(14, client.getRole().getId());
            prstm.setBigDecimal(15, client.getVirtualBalance());
            prstm.setInt(16, 0);
            prstm.setInt(17, client.getDiscount().getId());
            prstm.setBoolean(18, client.isDeleted());
            prstm.execute();

            ResultSet generatedKeys = prstm.getGeneratedKeys();
            generatedKeys.next();
            client.setId(generatedKeys.getInt("SCOPE_IDENTITY()"));
            LOGGER.info("Client added successfully{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client adding{}", e);
            throw new DaoException(e);
        }
        return client;
    }


    @Override
    public String deleteEntity(int ID) throws DaoException {
        String status = "Client do not deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_CLIENT)) {
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.execute();
            status = "Client deleted successfully";
            LOGGER.info("Client marked as deleted successfully{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Client marking as deleted{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String updateEntity(Client client) {
        String status = "Client do not updated";

        try (PreparedStatement prstm = conn.prepareStatement(UPDATE_CLIENT)) {
            prstm.setInt(1, client.getId());
            prstm.setString(2, client.getLogin());
            prstm.setString(3, client.getPassword());
            prstm.setString(4, client.getFirstName());
            prstm.setString(5, client.getLastName());
            prstm.setString(6, client.getMiddleName());
            prstm.setString(7, client.getAddress());
            prstm.setString(8, client.getTelephone());
            prstm.setString(9, client.getMobilephone());
            prstm.setInt(10, client.getRole().getId());
            prstm.setBigDecimal(11, client.getVirtualBalance());
            prstm.setInt(12, client.getAvatar().intValue());
            prstm.setInt(13, client.getDiscount().getId());
            prstm.setBoolean(14, client.isDeleted());
            prstm.setInt(15, client.getId());
            prstm.executeUpdate();
            status = "Client updated successfully";
            LOGGER.info("Client updated successfully{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client updating{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String getFindByParameters(Boolean needAditionalColumns) {

        String columns = NECESSARY_COLUMNS;

        if (needAditionalColumns == true) {
            columns = columns + ADDITIONAL_COLUMNS;
        }

        String result = String.format(FIND_BY_PARAMETERS_WITHOUT_COLUMNS, columns);

        result = String.format(result + "%s", ORDER_BY);
        result = String.format(result + "%s", "%s" + LIMIT_OFFSET);

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

    @Override
    public Client getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        PositionDao positionDao = daoManager.getPositionDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();

        Client client = new Client();
        try {
            client.setId(resultSet.getInt("id"));
            client.setAvatar(resultSet.getInt("avatar"));
            client.setLogin(resultSet.getString("login"));
            client.setPassword(resultSet.getString("password"));
            client.setFirstName(resultSet.getString("firstname"));
            client.setLastName(resultSet.getString("lastname"));
            client.setMiddleName(resultSet.getString("middlename"));
            client.setAddress(resultSet.getString("address"));
            client.setTelephone(resultSet.getString("telephone"));
            client.setMobilephone(resultSet.getString("mobilephone"));
            client.setRole(positionDao.findById(resultSet.getInt("position_id")));
            client.setVirtualBalance(new BigDecimal(resultSet.getInt("virtual_balance")));
            client.setDiscount(discountDao.findById(resultSet.getInt("discount")));
            client.setDeleted(resultSet.getBoolean("deleted"));
            LOGGER.info("Client created from resultset successfully{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Client creating from resultset{}", e);
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        String sql = "SELECT * FROM USERS";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                while (resultSet.next())
                    clients.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All clients found successfully{}", clients);
        } catch (Exception e) {
            LOGGER.error("Error of Clients finding", e);
            throw new DaoException(e);
        }
        return clients;
    }

}
