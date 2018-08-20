package com.jifan.pssmis.dao.sys.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.IModuleDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.sys.ModuleBO;
import com.jifan.pssmis.model.vo.sys.ModuleVO;


@SuppressWarnings("serial")
@Repository
public class ModuleDAOImpl extends BaseDAOImpl<ModuleBO,String> implements IModuleDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ModuleBO> findModuleByParam(ModuleVO param) {
		String hql = "from " + ModuleBO.class.getName() + " where 1=1";
        List list = new ArrayList();
        
		if (param.getModuleName() != null
				&& !param.getModuleName().equals("")) {
			hql = hql + " and moduleName like '%' || ? || '%' ";
			list.add(param.getModuleName());
		}
		if (param.getModuleCode() != null
				&& !param.getModuleCode().equals("")) {
			hql = hql + " and moduleCode = ? ";
			list.add(param.getModuleCode());
		}
		if (param.getSysNodeID() != null
				&& !param.getSysNodeID().equals("")) {
			hql = hql + " and sysNodeID = ? ";
			list.add(param.getSysNodeID());
		}
		
        Object[] module = DAOUtil.getObjectsByList(list);
        List<ModuleBO> moduleList = this.getHibernateTemplate().find(hql, module);
        return moduleList;
	}

}
