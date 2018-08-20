package com.jifan.pssmis.bs.cdm;
import java.util.List;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.model.bo.cdm.StockLogBO;
import com.jifan.pssmis.model.vo.cdm.StockLogQueryVO;
public interface IStockLogBS extends IBaseBS<StockLogBO,String>  {
        public List<StockLogBO> findStockLogByParam(StockLogQueryVO param);
        public String saveNotExist(StockLogBO entity) throws BizException;

}
