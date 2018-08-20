package com.jifan.pssmis.dao.sys;
import java.util.List;

import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.sys.BusinessGroupBO;
import com.jifan.pssmis.model.vo.sys.BusinessGroupQueryVO;
public interface IBusinessGroupDAO extends IBaseDAO<BusinessGroupBO,String>{
        public List<BusinessGroupBO> findBusinessGroupByParam(BusinessGroupQueryVO param);
  
}
