package com.jifan.pssmis.bs.cdm.impl;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.cdm.IStockBS;
import com.jifan.pssmis.dao.cdm.IStockDAO;
import com.jifan.pssmis.dao.cdm.impl.StockDAOImpl;
import com.jifan.pssmis.model.bo.cdm.StockBO;
import com.jifan.pssmis.model.vo.cdm.StockQueryVO;
@Service
public class StockBSImpl extends BaseBSImpl<StockBO,String> implements IStockBS{
        @Resource
        private IStockDAO stockDAO;
        
        @Resource
        public void setBaseDAO(IStockDAO stockDAO) {
                super.setBaseDAO(stockDAO);
        }
        public List<StockBO> findStockByParam(StockQueryVO param) {
                return this.stockDAO.findStockByParam(param);
        }
        public String saveNotExist(StockBO entity) throws BizException{
              return null;
        }

        public IStockDAO getStockDAO() {
                return stockDAO;
        }

        public void setStockDAO(IStockDAO stockDAO) {
                this.stockDAO = stockDAO;
        }

}
