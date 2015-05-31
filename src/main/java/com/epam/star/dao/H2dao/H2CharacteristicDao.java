package com.epam.star.dao.H2dao;

import com.epam.star.dao.CharactericticDao;
import com.epam.star.entity.Characteristic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class H2CharacteristicDao extends AbstractH2Dao implements CharactericticDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_CHARACTERISTIC = "INSERT INTO CHARACTERISTICS VALUES (?, ?, ?)";
    private static final String FULL_DELETE_CARACTERISTIC = "DELETE FROM GOODS WHERE ID = ?";
    private static final String DELETE_CARACTERISTIC = "UPDATE CHARACTERISTICS SET DELETED = ? WHERE ID = ?";
    private static final String UPDATE_CHARACTERISTIC = "UPDATE CHARACTERISTICS SET ID = ?, CHARACTERISTIC_NAME = ?, DELETED = ? WHERE ID = ?";

    private static final String TABLE = "GOODS_CHARACTERICTICS";
    private static final String IMPORTANT_FILTER = "";

    protected H2CharacteristicDao(Connection conn, DaoManager daoManager) {
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
    public Characteristic findById(int ID) {
        String sql = "SELECT * FROM CHARACTERISTICS WHERE id = " + ID;
        Characteristic characteristic = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    characteristic = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Characteristic found by ID successfully{}", characteristic);
        } catch (Exception e) {
            LOGGER.error("Error of Characteristic finding by ID{}", e);
            throw new DaoException(e);
        }
        return characteristic;
    }

    @Override
    public Characteristic findByName(String name) {
        String sql = "SELECT * FROM CHARACTERISTICS WHERE CHARACTERISTIC_NAME = '" + name + "'";
        Characteristic characteristic = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    characteristic = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Characteristic found by ID successfully{}", characteristic);
        } catch (Exception e) {
            LOGGER.error("Error of Characteristic finding by ID{}", e);
            throw new DaoException(e);
        }
        return characteristic;
    }

    @Override
    public Characteristic insert(Characteristic entity) {
        try (PreparedStatement prstm = conn.prepareStatement(ADD_CHARACTERISTIC)){
            prstm.setString(1, null);
            prstm.setString(2, entity.getCharacteristicName());
            prstm.setBoolean(3, entity.isDeleted());
            prstm.execute();

            ResultSet generatedKeys = prstm.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getInt("SCOPE_IDENTITY()"));
            LOGGER.info("Characteristic added successfully{}", entity);
        } catch (Exception e) {
            LOGGER.error("Error of Characteristic adding{}", e);
            throw new DaoException(e);
        }
        return entity;
    }

    @Override
    public String deleteEntity(int ID) {
        String status = "Characteristic do not deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_CARACTERISTIC)){
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.execute();
            status = "Characteristic deleted successfully ";
            LOGGER.info("Characteristic marked as deleted successfully{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Characteristic marking as deleted{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String updateEntity(Characteristic entity) {
        String status = "Characteristic do not updated";

        try (PreparedStatement prstm = conn.prepareStatement(UPDATE_CHARACTERISTIC)){
            prstm.setInt(1, entity.getId());
            prstm.setString(2, entity.getCharacteristicName());
            prstm.setBoolean(3, entity.isDeleted());
            prstm.setInt(4, entity.getId());
            prstm.executeUpdate();
            status = "Characteristic updated successfully";
            LOGGER.info("Characteristic updated successfully{}", entity);
        } catch (Exception e) {
            LOGGER.error("Error of Characteristic updating{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public Characteristic getEntityFromResultSet(ResultSet resultSet) throws DaoException, UnsupportedEncodingException {
        Characteristic goodsCharacteristic = new Characteristic();
        try {
            goodsCharacteristic.setId(resultSet.getInt("id"));
            goodsCharacteristic.setCharacteristicName(UTIL_DAO.getString(resultSet.getString("characteristic_name")));
            goodsCharacteristic.setDeleted(resultSet.getBoolean("deleted"));
            LOGGER.info("Characteristic created from result set successfully{}", goodsCharacteristic);
        } catch (Exception e) {
            LOGGER.error("Error of characteristics creating from result set{}", e);
            throw new DaoException(e);
        }
        return goodsCharacteristic;
    }

    @Override
    public List<Characteristic> findAll() {
        String sql = "SELECT * FROM CHARACTERISTICS";
        List<Characteristic> characteristics = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next())
                    characteristics.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All characteristics found successfully{}", characteristics);
        } catch (Exception e) {
            LOGGER.error("Error of characteristics finding", e);
            throw new DaoException(e);
        }
        return characteristics;
    }
}
