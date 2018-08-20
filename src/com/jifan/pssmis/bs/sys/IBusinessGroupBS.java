package com.jifan.pssmis.bs.sys;

import java.util.List;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.BusinessGroupBO;
import com.jifan.pssmis.model.vo.sys.BusinessGroupQueryVO;

public interface IBusinessGroupBS extends IBaseBS<BusinessGroupBO, String> {
	public List<BusinessGroupBO> findBusinessGroupByParam(
			BusinessGroupQueryVO param);

	public String saveNotExist(BusinessGroupBO entity) throws BizException;


}
