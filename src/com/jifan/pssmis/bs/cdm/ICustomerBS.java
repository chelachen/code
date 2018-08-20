package com.jifan.pssmis.bs.cdm;
import java.util.List;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.model.bo.cdm.CustomerBO;
import com.jifan.pssmis.model.vo.cdm.CustomerQueryVO;
public interface ICustomerBS extends IBaseBS<CustomerBO,String>  {
        public List<CustomerBO> findCustomerByParam(CustomerQueryVO param);
        public String saveNotExist(CustomerBO entity) throws BizException;
        public void updateNotExist(CustomerBO entity) throws BizException;

}
