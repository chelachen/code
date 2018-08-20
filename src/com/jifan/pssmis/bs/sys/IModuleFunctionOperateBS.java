package com.jifan.pssmis.bs.sys;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionOperateBO;

public interface IModuleFunctionOperateBS extends IBaseBS<ModuleFunctionOperateBO,String>{
	public String saveNotExist(ModuleFunctionOperateBO entity) throws BizException;
	public void deleteModuleFunctionOperate(ModuleFunctionOperateBO entity) throws BizException;
	public void updateModuleFunctionOperate(ModuleFunctionOperateBO entity) throws BizException;
	
}
