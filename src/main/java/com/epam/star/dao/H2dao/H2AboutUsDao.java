package com.epam.star.dao.H2dao;

import com.epam.star.dao.AboutUsDao;
import com.epam.star.entity.AboutUs;
import com.epam.star.entity.AbstractEntity;
import com.epam.star.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rody on 14.06.2015.
 */
public class H2AboutUsDao extends AbstractH2Dao implements AboutUsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);

    private static final String TABLE = "ABOUT";
    private static final String IMPORTANT_FILTER = "";
    private static final String ADD_ARTICLE = "INSERT INTO ABOUT VALUES (?, ?)";
    private static final String UPDATE_ARTICLE = "UPDATE ABOUT SET ID = ?, TEXT = ? WHERE ID = ?";

    protected H2AboutUsDao(Connection conn, DaoManager daoManager) {
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
    public AboutUs findById(int ID) {
        String sql = "select * from ABOUT where id = " + ID;

        AboutUs aboutUs = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next())
                    aboutUs = getEntityFromResultSet(resultSet);
            }
            LOGGER.info("AboutUs found by ID{}", aboutUs);
        } catch (Exception e) {
            LOGGER.error("Error of AboutUs finding by ID{}", e);
            throw new DaoException(e);
        }
        return aboutUs;
    }

    @Override
    public AboutUs insert(AboutUs aboutUs) {
        String status = "AboutUs was not added";

        try (PreparedStatement prstm = conn.prepareStatement(ADD_ARTICLE)) {
            prstm.setString(1, null);
            prstm.setString(2, aboutUs.getText());
            prstm.execute();

            LOGGER.info("AboutUs added successfully{}", aboutUs);
        } catch (Exception e) {
            LOGGER.error("Error of AboutUs adding{}", e);
            throw new DaoException(e);
        }
        return aboutUs;
    }

    @Override
    public String deleteEntity(int ID) {
        return "Deleting AboutUs was disabled";
    }

    @Override
    public String updateEntity(AboutUs aboutUs) {
        String status = "AboutUs do not updated";

        try (PreparedStatement prstm = conn.prepareStatement(UPDATE_ARTICLE)) {
            prstm.setInt(1, aboutUs.getId());
            prstm.setString(2, aboutUs.getText());
            prstm.setInt(3, aboutUs.getId());
            prstm.executeUpdate();
            status = "AboutUs updated successfully";
            LOGGER.info("AboutUs updated successfully{}", aboutUs);
        } catch (Exception e) {
            LOGGER.error("Error of AboutUs updating{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public AboutUs getEntityFromResultSet(ResultSet resultSet) throws DaoException, UnsupportedEncodingException {
        AboutUs aboutUs = new AboutUs();
        try {
            aboutUs.setId(resultSet.getInt("id"));
            aboutUs.setText(resultSet.getString("text"));
            LOGGER.info("aboutUs created from resultset successfully{}", aboutUs);
        } catch (Exception e) {
            LOGGER.error("Error of aboutUs creating from resultset{}", e);
            throw new DaoException(e);
        }
        return aboutUs;
    }

    @Override
    public List<AboutUs> findAll() {
        String sql = "SELECT * FROM ABOUT";
        List<AboutUs> aboutUs = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                while (resultSet.next())
                    aboutUs.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All Articles found successfully{}", aboutUs);
        } catch (Exception e) {
            LOGGER.error("Error of Articles finding", e);
            throw new DaoException(e);
        }
        return aboutUs;
    }
}
