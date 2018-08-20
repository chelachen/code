package com.jifan.pssmis.bs.sys;

import java.util.List;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.SystemConfigBO;
import com.jifan.pssmis.model.vo.sys.SystemConfigVO;

public interface ISystemConfigBS extends IBaseBS<SystemConfigBO,String> {

	 public String saveNotExist(SystemConfigBO entity) throws BizException;
	public List<SystemConfigBO> findSystemConfigByParam(SystemConfigVO param);
	
}
