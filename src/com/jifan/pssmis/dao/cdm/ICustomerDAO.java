package com.jifan.pssmis.dao.cdm;
import java.util.List;

import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.cdm.CustomerBO;
import com.jifan.pssmis.model.vo.cdm.CustomerQueryVO;
public interface ICustomerDAO extends IBaseDAO<CustomerBO,String>{
        public List<CustomerBO> findcustomerByParam(CustomerQueryVO param);

}
