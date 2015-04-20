package com.epam.star.dao;

import com.epam.star.dao.H2dao.DaoException;
import com.epam.star.entity.Image;

public interface ImageDao extends Dao<Image> {
    Image findByFilename(String filename) throws DaoException;
}
