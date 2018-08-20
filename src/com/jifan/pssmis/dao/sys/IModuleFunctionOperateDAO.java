package com.jifan.pssmis.dao.sys;

import java.util.List;

import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionOperateBO;
import com.jifan.pssmis.model.vo.sys.ModuleFunctionOperateVO;

public interface IModuleFunctionOperateDAO extends IBaseDAO<ModuleFunctionOperateBO,String>{
	public List<ModuleFunctionOperateVO> ISModuleFunctionOperate(ModuleFunctionOperateBO bo)throws BizException;
	public void updateModuleFunctionOperate(ModuleFunctionOperateBO bo);
}
