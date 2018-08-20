package com.jifan.pssmis.dao.cdm.impl;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.cdm.IStockLogDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.cdm.StockLogBO;
import com.jifan.pssmis.model.vo.cdm.StockLogQueryVO;
@Repository
public class StockLogDAOImpl extends BaseDAOImpl<StockLogBO,String> implements IStockLogDAO {
        public List<StockLogBO> findStockLogByParam(StockLogQueryVO param) {
                String hql = "from " + StockLogBO.class.getName() + " where 1=1";
                List list = new ArrayList();
                        if (param.getMateriaID() !=null && !param.getMateriaID().equals("")){
                        hql = hql + " and materiaID = ? ";
                        list.add(param.getMateriaID());
                }
                Object[] objects = DAOUtil.getObjectsByList(list);
                List<StockLogBO> retultList = this.getHibernateTemplate().find(hql, objects);
                return retultList;
        }

}
