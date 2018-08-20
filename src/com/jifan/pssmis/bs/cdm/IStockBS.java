package com.jifan.pssmis.bs.cdm;
import java.util.List;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.model.bo.cdm.StockBO;
import com.jifan.pssmis.model.vo.cdm.StockQueryVO;
public interface IStockBS extends IBaseBS<StockBO,String>  {
        public List<StockBO> findStockByParam(StockQueryVO param);
        public String saveNotExist(StockBO entity) throws BizException;

}
