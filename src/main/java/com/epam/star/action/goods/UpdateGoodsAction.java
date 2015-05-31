package com.epam.star.action.goods;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.*;
import com.epam.star.dao.util.EntityFromParameters.GetEntityUpdate;
import com.epam.star.entity.Goods;
import com.epam.star.entity.GoodsCharacteristic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@MappedAction("POST/updateGoods")
public class UpdateGoodsAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateGoodsAction.class);
    private ActionResult message = new ActionResult("message");
    private GetEntityUpdate entityForUpdate = new GetEntityUpdate();

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2GoodsDao goodsDao = daoManager.getGoodsDao();

        Goods entity = goodsDao.findById(Integer.valueOf(request.getParameter("id")));
        Goods newEntity = (Goods) entityForUpdate.getByEntityName(request, daoManager, "goods", entity);
        newEntity.setCharacteristics(getCharacteristicsFromView(request,daoManager));

        if (entity == null) LOGGER.info("Goods for update is null, {}", entity);

        if (newEntity != null) {
            daoManager.beginTransaction();
            try {
                goodsDao.updateEntity(newEntity);

                daoManager.commit();
                LOGGER.info("Goods updated successful, {}", newEntity);
                request.setAttribute("message", "goods.update.successful");
            } catch (Exception e) {
                daoManager.rollback();
                request.setAttribute("message", "goods.update.error");
            } finally {
                daoManager.closeConnection();
            }
        }
        return message;
    }

    private List<GoodsCharacteristic> getCharacteristicsFromView(HttpServletRequest request, DaoManager daoManager){
        List<GoodsCharacteristic> characteristics = new ArrayList<>();

        H2CharacteristicDao characteristicDao = daoManager.getCharacteristicDao();
        H2GoodsCharacteristicsDao goodsCharacteristicDao = daoManager.getGoodsCharacteristicDao();

        Map<String, String[]> parameterMap = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (entry.getKey().contains("Char")){
                GoodsCharacteristic goodsCharacteristic = new GoodsCharacteristic();
                goodsCharacteristic.setCharacteristic(characteristicDao.findByName(entry.getKey().substring(entry.getKey().indexOf("Char")+4)));
                goodsCharacteristic.setCaracteristicDescription(entry.getValue()[0]);
                characteristics.add(goodsCharacteristic);
            }
        }

        return null;
    }
}
