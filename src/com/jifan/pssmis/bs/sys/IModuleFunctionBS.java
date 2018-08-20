package com.jifan.pssmis.bs.sys;

import java.util.List;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionBO;
import com.jifan.pssmis.model.vo.sys.ModuleFunctionVO;

public interface IModuleFunctionBS extends IBaseBS<ModuleFunctionBO,String> {

	public List<ModuleFunctionBO> findModuleFunctionByParam(ModuleFunctionVO param);
	
	public String saveNotExist(ModuleFunctionBO entity) throws BizException;
}
