package com.jifan.pssmis.dao.cdm;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.cdm.StockBO;
import com.jifan.pssmis.model.vo.cdm.StockQueryVO;
public interface IStockDAO extends IBaseDAO<StockBO,String>{
        public List<StockBO> findStockByParam(StockQueryVO param);

}
