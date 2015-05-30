package com.epam.star;


import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2CharacteristicDao;
import com.epam.star.dao.H2dao.H2GoodsCharacteristicsDao;
import com.epam.star.entity.Characteristic;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws SQLException, ParseException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2GoodsCharacteristicsDao gcd = daoManager.getGoodsCharacteristicDao();
        H2CharacteristicDao cd = daoManager.getCharacteristicDao();

//        System.out.println(cd.findById(1));

//        Characteristic c = new Characteristic();
//        c.setCharacteristicName("qwerty");
//        System.out.println(cd.insert(c));

//        System.out.println(cd.deleteEntity(8));

        Characteristic c = cd.findById(8);
        c.setDeleted(false);
        System.out.println(cd.updateEntity(c));
    }

}
