package com.jifan.pssmis.dao.sys;

import java.util.List;

import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionBO;
import com.jifan.pssmis.model.vo.sys.ModuleFunctionVO;

public interface IModuleFunctionDAO extends IBaseDAO<ModuleFunctionBO,String> {

	public List<ModuleFunctionBO> findModuleFunctionByParam(ModuleFunctionVO param);
}
