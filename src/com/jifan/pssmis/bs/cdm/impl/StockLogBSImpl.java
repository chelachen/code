package com.jifan.pssmis.bs.cdm.impl;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.cdm.IStockLogBS;
import com.jifan.pssmis.dao.cdm.IStockLogDAO;
import com.jifan.pssmis.dao.cdm.impl.StockLogDAOImpl;
import com.jifan.pssmis.model.bo.cdm.StockLogBO;
import com.jifan.pssmis.model.vo.cdm.StockLogQueryVO;
@Service("stockLogBS")
public class StockLogBSImpl extends BaseBSImpl<StockLogBO,String> implements IStockLogBS{
        @Resource
        private IStockLogDAO stockLogDAO;
        
        @Resource
        public void setBaseDAO(IStockLogDAO stockLogDAO) {
                super.setBaseDAO(stockLogDAO);
        }
        public List<StockLogBO> findStockLogByParam(StockLogQueryVO param) {
                return this.stockLogDAO.findStockLogByParam(param);
        }
        public String saveNotExist(StockLogBO entity) throws BizException{
               return super.save(entity);
        }

        public IStockLogDAO getStockLogDAO() {
                return stockLogDAO;
        }

        public void setStockLogDAO(IStockLogDAO stockLogDAO) {
                this.stockLogDAO = stockLogDAO;
        }

}
