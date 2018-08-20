package com.jifan.pssmis.dao.sys;

import java.util.List;

import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.sys.SystemConfigBO;
import com.jifan.pssmis.model.vo.sys.SystemConfigVO;

public interface ISystemConfigDAO extends IBaseDAO<SystemConfigBO,String> {

	
	public List<SystemConfigBO> findSystemConfigByParam(SystemConfigVO param);
	
}
