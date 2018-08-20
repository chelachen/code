package com.jifan.pssmis.dao.cdm.impl;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.cdm.IStockDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.cdm.StockBO;
import com.jifan.pssmis.model.vo.cdm.StockQueryVO;
@Repository
public class StockDAOImpl extends BaseDAOImpl<StockBO,String> implements IStockDAO {
        public List<StockBO> findStockByParam(StockQueryVO param) {
                String hql = "from " + StockBO.class.getName() + " where 1=1";
                List list = new ArrayList();
                        if (param.getMateriaID() !=null && !param.getMateriaID().equals("")){
                        hql = hql + " and materiaID = ? ";
                        list.add(param.getMateriaID());
                }
                        if (param.getWarehouseID() !=null && !param.getWarehouseID().equals("")){
                        hql = hql + " and warehouseID = ? ";
                        list.add(param.getWarehouseID());
                }
                        if (param.getAmount() !=null && !param.getAmount().equals("")){
                        hql = hql + " and amount = ? ";
                        list.add(param.getAmount());
                }
                Object[] objects = DAOUtil.getObjectsByList(list);
                List<StockBO> retultList = this.getHibernateTemplate().find(hql, objects);
                return retultList;
        }

}
