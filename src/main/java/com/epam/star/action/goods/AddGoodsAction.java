package com.epam.star.action.goods;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.dao.H2dao.H2ImageDao;
import com.epam.star.dao.util.EntityFromParameters.GetEntity;
import com.epam.star.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("POST/addGoodsToDataBase")
public class AddGoodsAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddGoodsAction.class);
    private ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2GoodsDao goodsDao = daoManager.getGoodsDao();
        H2ImageDao imageDao = daoManager.getImageDao();

        Goods goods = (Goods) GetEntity.getByEntityName(request,daoManager,"goods");
        if (goods == null) LOGGER.info("Goods for add is null, {}", goods);

        if (goods != null) {
            daoManager.beginTransaction();
            try {
                goods.setImage(imageDao.insert(goods.getImage()));
                goodsDao.insert(goods);

                daoManager.commit();
                LOGGER.info("Goods added successful, {}", goods);
                request.setAttribute("message", "goods.add.successful");
            } catch (Exception e) {
                daoManager.rollback();
                request.setAttribute("message", "goods.add.error");
            } finally {
                daoManager.closeConnection();
            }
        }
        return message;
    }
}
