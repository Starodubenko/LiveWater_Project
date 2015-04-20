package com.epam.star.dao.H2dao;

import com.epam.star.dao.ImageDao;
import com.epam.star.dao.MappedDao;
import com.epam.star.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@MappedDao("Image")
public class H2ImageDao extends AbstractH2Dao implements ImageDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ImageDao.class);

    private static final String ID = "id";
    private static final String FILENAME = "filename";
    private static final String CONTENT = "content";
    private static final String DELETED = "deleted";

    private static final String FIND_BY_FILENAME = "SELECT * FROM image WHERE filename = ?";
    private static final String GET_LAST_ELEMENT = "SELECT * FROM image ORDER BY id DESC LIMIT 1";
    private static final String FIND_BY_ID = "SELECT * FROM image WHERE id = ?";
    private static final String INSERT_IMAGE = "INSERT INTO image VALUES (?, ?, ?, ?)";
    private static final String FULL_DELETE_IMAGE = "delete from image where ID = ?";
    private static final String DELETE_IMAGE = "UPDATE IMAGE SET DELETED = ? WHERE ID = ?";

    private static final String NECESSARY_COLUMNS =
            " IMAGE.ID, IMAGE.FILENAME, IMAGE.CONTENT";

    private static final String ADDITIONAL_COLUMNS =
            " ,IMAGE.DELETED ";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM IMAGE";

    private static final String ID_FIELD = " IMAGE.ID, ";

    private static final String ORDER_BY = " ORDER BY FILENAME ";
    private static final String TABLE = "IMAGE";
    private static final String IMPORTANT_FILTER = "";


    protected H2ImageDao() {
        super(null, null);
    }

    protected H2ImageDao(Connection conn, DaoManager daoManager) {
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

    @Override
    public Image getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Image image = null;
        try {
            image = new Image();
            image.setId(resultSet.getInt(ID));
            image.setFilename(resultSet.getString(FILENAME));
            image.setContent(resultSet.getBytes(CONTENT));
            image.setDeleted(resultSet.getBoolean(DELETED));
            LOGGER.info("Image created from result set successfully{}", image);
        } catch (Exception e) {
            LOGGER.error("Error of Image creating from result set{}", e);
            throw new DaoException(e);
        }
        return image;
    }

    @Override
    public List<Image> findAll() {
        String sql = "SELECT * FROM IMAGE";
        List<Image> images = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)){
            try(ResultSet resultSet = prstm.executeQuery()){
                while (resultSet.next())
                    images.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All images found successfully{}", images);
        } catch (Exception e) {
            LOGGER.error("Error of images finding", e);
            throw new DaoException(e);
        }
        return images;
    }

    @Override
    public Image findByFilename(String filename) throws DaoException {
        Image image = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_FILENAME)) {
            preparedStatement.setString(1, filename);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    image =  getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Image found by file name successfully{}", image);
        } catch (Exception e) {
            LOGGER.error("Error of Image finding by file name{}", e);
            throw new DaoException(e);
        }
        return image;
    }

    public Image findLastAddedImage() throws DaoException {
        Image image = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement(GET_LAST_ELEMENT)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    image =  getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Last image found successfully{}", image);
        } catch (Exception e) {
            LOGGER.error("Error of last image finding{}", e);
            throw new DaoException(e);
        }
        return image;
    }

    @Override
    public Image findById(int ID) {

        Image image = null;
        try (PreparedStatement prstm = conn.prepareStatement(FIND_BY_ID)) {
            prstm.setInt(1, ID);
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    image = getEntityFromResultSet(resultSet);
                }
            }
            LOGGER.info("Image found by ID successfully{}", image);
        } catch (Exception e) {
            LOGGER.error("Error of Image finding by ID{}", e);
            throw new DaoException(e);
        }
        return image;
    }

    @Override
    public Image insert(Image image) {

        try (PreparedStatement prstm = conn.prepareStatement(INSERT_IMAGE)){
            prstm.setString(1, null);
            prstm.setString(2, image.getFilename());
            prstm.setBytes(3, image.getContent());
            prstm.setBoolean(4, image.isDeleted());
            prstm.execute();

            ResultSet generatedKeys = prstm.getGeneratedKeys();
            generatedKeys.next();
            image.setId(generatedKeys.getInt("SCOPE_IDENTITY()"));
            LOGGER.info("Image added successfully{}", image);
        } catch (Exception e) {
            LOGGER.error("Error of Image adding{}", e);
            throw new DaoException(e);
        }
        return image;
    }

    @Override
    public String deleteEntity(int ID) {
        String status = "Image do not deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_IMAGE)){
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.execute();
            status = "Image successfully deleted";
            LOGGER.info("Image marked as deleted successfully{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Image marking as deleted{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String updateEntity(Image image) {
        return null;
    }

}
