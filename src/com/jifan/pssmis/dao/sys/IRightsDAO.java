package com.jifan.pssmis.dao.sys;
import java.util.List;

import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.sys.RightsBO;
import com.jifan.pssmis.model.vo.sys.RightsQueryVO;
public interface IRightsDAO extends IBaseDAO<RightsBO,String>{
        public List<RightsBO> findRightsByParam(RightsQueryVO param);
        @SuppressWarnings("unchecked")
		public List findByParam(List<String> param);

}
