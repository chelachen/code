package com.jifan.pssmis.dao.sys;

import java.util.List;

import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.sys.ModuleBO;
import com.jifan.pssmis.model.vo.sys.ModuleVO;

public interface IModuleDAO extends IBaseDAO<ModuleBO,String> {

	public List<ModuleBO> findModuleByParam(ModuleVO param);
}
