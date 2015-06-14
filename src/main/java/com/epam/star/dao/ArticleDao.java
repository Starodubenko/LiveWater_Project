package com.epam.star.dao;

import com.epam.star.entity.Article;

import java.util.List;

/**
 * Created by Rody on 18.04.2015.
 */
public interface ArticleDao extends Dao<Article> {
    List<Article> findLastTenArticles();

    List<Article> findLastTenArticlesForAdmin();
}
