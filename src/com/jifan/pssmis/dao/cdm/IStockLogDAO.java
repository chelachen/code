package com.jifan.pssmis.dao.cdm;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.cdm.StockLogBO;
import com.jifan.pssmis.model.vo.cdm.StockLogQueryVO;
public interface IStockLogDAO extends IBaseDAO<StockLogBO,String>{
        public List<StockLogBO> findStockLogByParam(StockLogQueryVO param);

}
