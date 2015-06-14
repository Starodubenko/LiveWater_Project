package com.epam.star.dao.H2dao;

import com.epam.star.dao.ArticleDao;
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
 * Created by Rody on 18.04.2015.
 */
public class H2ArticleDao extends AbstractH2Dao implements ArticleDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);

    private static final String TABLE = "ARTICLES";
    private static final String IMPORTANT_FILTER = "";
    private static final String ADD_ARTICLE = "INSERT INTO ARTICLES VALUES (?, ?, ?, ?, ?)";
    private static final String FULL_DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE ID = ?";
    private static final String DELETE_ARTICLE = "UPDATE ARTICLES SET DELETED = ? WHERE ID = ?";
    private static final String UPDATE_ARTICLE = "UPDATE ARTICLES SET ID = ?, TITLE = ?, NEWS_DATE = ?, CONTENT = ?, DELETED = ? WHERE ID = ?";


    protected H2ArticleDao(Connection conn, DaoManager daoManager) {
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
    public Article findById(int ID) {
        String sql = "select * from ARTICLES where id = " + ID;

        Article client = null;
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next())
                    client = getEntityFromResultSet(resultSet);
            }
            LOGGER.info("Article found by ID{}", client);
        } catch (Exception e) {
            LOGGER.error("Error of Article finding by ID{}", e);
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public Article insert(Article article) {
        String status = "Article was not added";

        try (PreparedStatement prstm = conn.prepareStatement(ADD_ARTICLE)) {
            prstm.setString(1, null);
            prstm.setString(2, article.getTitle());
            prstm.setString(3, article.getContent());
            prstm.setBoolean(4, article.isDeleted());
            prstm.setDate(5, new Date(article.getNewsDate().getTime()));
            prstm.execute();

            LOGGER.info("Article added successfully{}", article);
        } catch (Exception e) {
            LOGGER.error("Error of Article adding{}", e);
            throw new DaoException(e);
        }
        return article;
    }

    @Override
    public String deleteEntity(int ID) {
        String status = "Article was not deleted";

        try (PreparedStatement prstm = conn.prepareStatement(DELETE_ARTICLE)) {
            prstm.setBoolean(1, true);
            prstm.setInt(2, ID);
            prstm.execute();
            status = "Article deleted successfully";
            LOGGER.info("Article marked as deleted successfully{}", ID);
        } catch (Exception e) {
            LOGGER.error("Error of Article marking as deleted{}", e);
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String updateEntity(Article article) {
        String status = "Article do not updated";

        try (PreparedStatement prstm = conn.prepareStatement(UPDATE_ARTICLE)) {
            prstm.setInt(1, article.getId());
            prstm.setString(2, article.getTitle());
            prstm.setDate(3, new Date(article.getNewsDate().getTime()));
            prstm.setString(4, article.getContent());
            prstm.setBoolean(5, article.isDeleted());
            prstm.setInt(6, article.getId());
            prstm.executeUpdate();
            status = "Article updated successfully";
            LOGGER.info("Article updated successfully{}", article);
        } catch (Exception e) {
            LOGGER.error("Error of Article updating{}", e);
            throw new DaoException(e);
        }
        return status;
    }


    @Override
    public Article getEntityFromResultSet(ResultSet resultSet) throws DaoException, UnsupportedEncodingException {
        Article article = new Article();
        try {
            article.setId(resultSet.getInt("id"));
            article.setTitle(resultSet.getString("title"));
            article.setNewsDate(resultSet.getDate("news_date"));
            article.setContent(resultSet.getString("content"));
            LOGGER.info("Article created from resultset successfully{}", article);
        } catch (Exception e) {
            LOGGER.error("Error of Article creating from resultset{}", e);
            throw new DaoException(e);
        }
        return article;
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLES";
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                while (resultSet.next())
                    articles.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All Articles found successfully{}", articles);
        } catch (Exception e) {
            LOGGER.error("Error of Articles finding", e);
            throw new DaoException(e);
        }
        return articles;
    }

    @Override
    public List<Article> findLastTenArticles() {
        String sql = "SELECT * FROM ARTICLES WHERE DELETED = FALSE AND NEWS_DATE <= sysdate order by NEWS_DATE DESC, ID DESC LIMIT 10 OFFSET 0";
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                while (resultSet.next())
                    articles.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All Articles found successfully{}", articles);
        } catch (Exception e) {
            LOGGER.error("Error of Articles finding", e);
            throw new DaoException(e);
        }
        return articles;
    }

    @Override
    public List<Article> findLastTenArticlesForAdmin() {
        String sql = "SELECT * FROM ARTICLES order by NEWS_DATE DESC, ID DESC LIMIT 10 OFFSET 0";
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement prstm = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                while (resultSet.next())
                    articles.add(getEntityFromResultSet(resultSet));
            }
            LOGGER.info("All Articles found successfully{}", articles);
        } catch (Exception e) {
            LOGGER.error("Error of Articles finding", e);
            throw new DaoException(e);
        }
        return articles;
    }
}
