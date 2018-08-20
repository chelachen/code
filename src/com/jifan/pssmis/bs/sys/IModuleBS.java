package com.jifan.pssmis.bs.sys;

import java.util.List;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.ModuleBO;
import com.jifan.pssmis.model.vo.sys.ModuleVO;

public interface IModuleBS extends IBaseBS<ModuleBO,String> {

	public List<ModuleBO> findModuleByParam(ModuleVO param);
	
	public String saveNotExist(ModuleBO entity) throws BizException;

	public void updateNotExist(ModuleBO entity) throws BizException; 
	
	void SaveList(List<ModuleBO> moduleBOlist);

}
