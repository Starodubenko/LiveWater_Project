package com.epam.star.dao.H2dao;

import com.epam.star.dao.GoodsDao;
import com.epam.star.dao.MappedDao;
import com.epam.star.entity.Goods;
import com.epam.star.entity.GoodsCharacteristic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@MappedDao("Goods")
public class H2GoodsDao extends AbstractH2Dao implements GoodsDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);

    private static final String ADD_GOODS = "INSERT INTO GOODS VALUES (?, ?, ?, ?, ?)";
    private static final String FULL_DELETE_GOODS = "DELETE FROM GOODS WHERE ID = ?";
    private static final String DELETE_GOODS = "UPDATE GOODS SET DELETED = ? WHERE ID = ?";
    private static final String UPDATE_GOODS = "UPDATE GOODS SET ID = ?, GOODS_NAME = ?, PRICE = ?, IMAGE = ?, DELETED = ? WHERE ID = ?";


    private static final String TABLE = "GOODS";
    private static final String IMPORTANT_FILTER = "";


    protected H2GoodsDao() {
        super(null, null);
    }

    protected H2GoodsDao(Connection conn, DaoManager daoManager) {
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

    public List<Goods> getAllGoods() {
        List<Goods> result = new ArrayList<>();
        String sql = "SELECT * FROM GOODS";

        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next()) {
                    Goods foundGoods = getEntityFromResultSet(resultSet);
                    foundGoods.setCharacteristics(getGoodsCharacteristics(foundGoods.getId()));
                    result.add(foundGoods);
                }
            }
            LOGGER.info("All employees found successfully{}", result);
        } catch (Exception e) {
            LOGGER.error("Error of employees finding", e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Goods findByGoodsName(String name) throws DaoException {
        String sql = "SELECT * FROM goods WHERE goods_name = " + "'" + name + "'";
        Goods goods = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    goods = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Goods found by name successfully{}", goods);
        } catch (Exception e) {
            LOGGER.error("Error of Goods finding by name{}", e);
            throw new DaoException(e);
        }
        return goods;
    }

    public List<Goods> findByGoodsNameRange(String name) throws DaoException {
        String sql = "SELECT * FROM goods WHERE lower(goods_name) LIKE lower('%" + name + "%')";
        List<Goods> goods = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                while (resultSet.next()) {
                    goods.add(getEntityFromResultSet(resultSet));
                }
            }
            LOGGER.info("Goods found by name successfully{}", goods);
        } catch (Exception e) {
            LOGGER.error("Error of Goods finding by name{}", e);
            throw new DaoException(e);
        }
        return goods;
    }

    @Override
    public Goods findById(int ID) throws DaoException {
        String sql = "SELECT * FROM goods WHERE id = " + ID;
        Goods goods = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    goods = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Goods found by ID successfully{}", goods);
        } catch (Exception e) {
            LOGGER.error("Error of Goods finding by ID{}", e);
            throw new DaoException(e);
        }
        return goods;
    }

    @Override
    public Goods insert(Goods goods) {

        try (PreparedStatement prstm = conn.prepareStatement(ADD_GOODS)){
            prstm.setString(1, null);
            prstm.setString(2, goods.getGoodsName());
            prstm.setBigDecimal(3, goods.getPrice());
            prstm.setInt(4, goods.getImage().getId());
            prstm.setBoolean(5, goods.isDeleted());
            prstm.execute();

            ResultSet generatedKeys = prstm.getGeneratedKeys();
            generatedKeys.next();
            goods.setId(generatedKeys.getInt("SCOPE_IDENTITY()"));
            LOGGER.info("Goods added successfully{}", goods);
        } catch (Exception e) {
            LOGGER.error("Error of Goods adding{}", e);
            throw new DaoException(e);
        }
        return goods;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        String status = "Status do not deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_GOODS)){
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.execute();
            status = "Goods deleted successfully ";
            LOGGER.info("Goods marked as deleted successfully{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Goods marking as deleted{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String updateEntity(Goods goods) {
        String status = "Goods do not updated";

        try (PreparedStatement prstm = conn.prepareStatement(UPDATE_GOODS)){
            prstm.setInt(1, goods.getId());
            prstm.setString(2, goods.getGoodsName());
            prstm.setBigDecimal(3, goods.getPrice());
            prstm.setInt(4, goods.getImage().getId());
            prstm.setBoolean(5, goods.isDeleted());
            prstm.setInt(6, goods.getId());
            prstm.executeUpdate();
            status = "Goods updated successfully";
            LOGGER.info("Goods updated successfully{}", goods);
        } catch (Exception e) {
            LOGGER.error("Error of Goods updating{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public Goods getEntityFromResultSet(ResultSet resultSet) throws DaoException {

        H2ImageDao imageDao = daoManager.getImageDao();
//        H2GoodsCharacteristicsDao goodsCharacteristicDao = daoManager.getGoodsCharacteristicDao();

        Goods goods = new Goods();
        try {
            goods.setId(resultSet.getInt("id"));
            goods.setCharacteristics(null);
            goods.setGoodsName(UTIL_DAO.getString(resultSet.getString("goods_name")));
            goods.setPrice(resultSet.getBigDecimal("price"));
            goods.setImage(imageDao.findById(resultSet.getInt("image")));
            goods.setDeleted(resultSet.getBoolean("deleted"));
            LOGGER.info("Goods created from result set successfully{}", goods);
        } catch (Exception e) {
            LOGGER.error("Error of Goods creating from result set{}", e);
            throw new DaoException(e);
        }
        return goods;
    }

    public List<GoodsCharacteristic> getGoodsCharacteristics(int id){
        H2GoodsCharacteristicsDao goodsCharacteristicDao = daoManager.getGoodsCharacteristicDao();
        return goodsCharacteristicDao.findByGoodsId(id);
    }

    @Override
    public List<Goods> findAll() {
        String sql = "SELECT * FROM GOODS";
        List<Goods> goods = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next()) {
                    Goods foundGoods = getEntityFromResultSet(resultSet);
                    foundGoods.setCharacteristics(getGoodsCharacteristics(foundGoods.getId()));
                    goods.add(foundGoods);
                }
            }
            LOGGER.info("All goods found successfully{}", goods);
        } catch (Exception e) {
            LOGGER.error("Error of goods finding", e);
            throw new DaoException(e);
        }
        return goods;
    }
}
