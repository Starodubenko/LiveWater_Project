package com.epam.star.dao.H2dao;

import com.epam.star.dao.GoodsCharacteristicsDao;
import com.epam.star.entity.Characteristic;
import com.epam.star.entity.Goods;
import com.epam.star.entity.GoodsCharacteristic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class H2GoodsCharacteristicsDao extends AbstractH2Dao implements GoodsCharacteristicsDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_GOODS_CHARACTERISTIC = "INSERT INTO GOODS_CHARACTERISTICS VALUES (?, ?, ?, ?)";
    private static final String FULL_DELETE_GOODS = "DELETE FROM GOODS_CHARACTERISTICS WHERE ID = ?";
    private static final String DELETE_GOODS_CHARACTERISTIC = "UPDATE GOODS_CHARACTERISTICS SET DELETED = ? WHERE ID = ?";
    private static final String UPDATE_GOODS_CHARACTERISTIC = "UPDATE GOODS_CHARACTERISTICS SET ID = ?, GOODS_ID = ?, CHARACTERISTIC_ID = ?, DESCRIPTION = ? WHERE ID = ?";

    private static final String TABLE = "GOODS_CHARACTERICTICS";
    private static final String IMPORTANT_FILTER = "";

    protected H2GoodsCharacteristicsDao(Connection conn, DaoManager daoManager) {
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
    public GoodsCharacteristic findById(int ID) {
        String sql = "SELECT * FROM GOODS_CHARACTERISTICS WHERE id = " + ID;
        GoodsCharacteristic goodsCharacteristic = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    goodsCharacteristic = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Goods characteristic found by ID successfully{}", goodsCharacteristic);
        } catch (Exception e) {
            LOGGER.error("Error of Goods characteristic finding by ID{}", e);
            throw new DaoException(e);
        }
        return goodsCharacteristic;
    }

    @Override
    public GoodsCharacteristic insert(GoodsCharacteristic entity) {
        try (PreparedStatement prstm = conn.prepareStatement(ADD_GOODS_CHARACTERISTIC)){
            prstm.setString(1, null);
            prstm.setInt(2, entity.getGoods().getId());
            prstm.setInt(3, entity.getCharacteristic().getId());
            prstm.setString(4, entity.getCaracteristicDescription());
            prstm.execute();

            ResultSet generatedKeys = prstm.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getInt("SCOPE_IDENTITY()"));
            LOGGER.info("Goods added successfully{}", entity);
        } catch (Exception e) {
            LOGGER.error("Error of Goods adding{}", e);
            throw new DaoException(e);
        }
        return entity;
    }

    @Override
    public String deleteEntity(int ID) {
        String status = "Goods characteristic do not deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_GOODS_CHARACTERISTIC)){
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.execute();
            status = "Goods characteristic deleted successfully ";
            LOGGER.info("Goods characteristic marked as deleted successfully{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Goods characteristic marking as deleted{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String updateEntity(GoodsCharacteristic entity) {
        String status = "Goods characteristic do not updated";

        try (PreparedStatement prstm = conn.prepareStatement(UPDATE_GOODS_CHARACTERISTIC)){
            prstm.setInt(1, entity.getId());
            prstm.setInt(2, entity.getGoods().getId());
            prstm.setInt(3, entity.getCharacteristic().getId());
            prstm.setString(4, entity.getCaracteristicDescription());
            prstm.setInt(5, entity.getId());
            prstm.executeUpdate();
            status = "Goods characteristic updated successfully";
            LOGGER.info("Goods characteristic updated successfully{}", entity);
        } catch (Exception e) {
            LOGGER.error("Error of Goods characteristic updating{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public GoodsCharacteristic getEntityFromResultSet(ResultSet resultSet) throws DaoException, UnsupportedEncodingException {

        H2CharacteristicDao characteristicDao = daoManager.getCharacteristicDao();
        H2GoodsDao goodsDao = daoManager.getGoodsDao();
        GoodsCharacteristic goodsCharacteristic = new GoodsCharacteristic();

        try {
            Characteristic charactericticById = characteristicDao.findById(resultSet.getInt("characterictic_id"));
            Goods goodsById = goodsDao.findById(resultSet.getInt("goods_id"));

            goodsCharacteristic.setId(resultSet.getInt("id"));
            goodsCharacteristic.setCaracteristicDescription(UTIL_DAO.getString(resultSet.getString("description")));
            goodsCharacteristic.setCharacteristic(charactericticById);
            goodsCharacteristic.setGoods(goodsById);
            goodsCharacteristic.setDeleted(resultSet.getBoolean("deleted"));
            LOGGER.info("GoodsCharacteristic created from result set successfully{}", goodsCharacteristic);
        } catch (Exception e) {
            LOGGER.error("Error of GoodsCharacteristic creating from result set{}", e);
            throw new DaoException(e);
        }
        return goodsCharacteristic;
    }

    @Override
    public List findAll() {
        String sql = "SELECT * FROM GOODS_CHARACTERISTICS";
        List<GoodsCharacteristic> goodsCharacteristics = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next())
                    goodsCharacteristics.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All goodsCharacteristics found successfully{}", goodsCharacteristics);
        } catch (Exception e) {
            LOGGER.error("Error of goodsCharacteristics finding", e);
            throw new DaoException(e);
        }
        return goodsCharacteristics;
    }
}
